package org.netsharp.wx.pa.service;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.dic.FansStatus;
import org.netsharp.wx.pa.dic.PublicAccountType;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.api.oauth.OAuthRequest;
import org.netsharp.wx.sdk.mp.api.oauth.OAuthResponse;
import org.netsharp.wx.sdk.mp.api.oauth.OAuthRequest.OauthScope;
import org.netsharp.wx.sdk.mp.api.user.UserInfoRequest;
import org.netsharp.wx.sdk.mp.api.user.UserInfoResponse;

@Service
public class FansService extends PersistableService<Fans> implements IFansService {

	private static Log logger = LogFactory.getLog(FansService.class.getSimpleName());

	public FansService() {
		super();
		this.type = Fans.class;
	}

	@Override
	public Fans getInviterByUser(Integer userId) {
		Fans fans = getFansByUserId(userId);
		if (fans == null) {
			return null;
		}
		String code = fans.getInviteCode();
		if (code != null) {
			Integer inviterFid = Integer.parseInt(code);
			return byId(inviterFid);
		}
		return null;
	}

	@Override
	public Fans getInviter(Integer fansId) {
		Fans fans = byId(fansId);
		if (fans == null) {
			return null;
		}
		String code = fans.getInviteCode();
		if (code != null) {
			Integer inviterFid = Integer.parseInt(code);
			return byId(inviterFid);
		}
		return null;
	}

	/**
	 * 处理用户关注事件
	 *
	 * @param openId
	 * @param pa
	 * @param sceneId
	 * @param isSubscribeEvent
	 *            true表示是新用户的关注事件，false表示是扫码事件（可能是新用户，可能是老用户）
	 * @return
	 * @
	 */
	public Fans subscribe(String openId, PublicAccount pa, int sceneId, boolean isSubscribeEvent)  {
		sceneId = sceneId < 0 ? 0 : sceneId;
		Fans fans = getFans(openId, pa.getId());
		// 是否是新的粉丝用户
		boolean isNewFans = fans.getId() == null;
		if (pa.getPublicAccountType() == PublicAccountType.service) {
			fans = this.queryFansInfo(openId, pa);
		}
		setFansSubscribeInfo(fans, sceneId, isSubscribeEvent, isNewFans);
		fans = this.save(fans);

		return fans;
	}

	/**
	 * 此方法仅用于关注事件的粉丝信息处理<br/>
	 * 这里的关注事件，可能是新用户的关注事件，也可能是老用户（已经关注的）的扫码事件
	 *
	 * @param fans
	 * @param sceneId
	 *            当前关注或扫码事件的场景ID
	 * @param isSubscribeEvent
	 *            true表示是关注事件，false表示是SCAN扫码事件
	 * @param isNewFans
	 *            true表示新粉丝，false表示假粉丝
	 */
	private void setFansSubscribeInfo(Fans fans, int sceneId, boolean isSubscribeEvent, boolean isNewFans) {
		Date now = new Date();

		// 是否是已关注的用户
		boolean isSubscribedFans = fans.getStatus() == FansStatus.subscribe;

		if (!isSubscribeEvent) {
			fans.setLastScanDate(now);
		}
		if (sceneId > 0) {
			fans.setLastScanSceneId(sceneId);
			fans.setLastScanDate(now);
		}
		fans.setStatus(FansStatus.subscribe);
		// 对于新用户，一定是关注事件
		if (isNewFans) {
			fans.setSubscribeDate(now);
			fans.setSceneId(sceneId);
			fans.setLastSubscribeDate(now);
			fans.setLastSubscribeSceneId(sceneId);
		}
		// 对于老粉丝
		else {
			// 如果是已关注的用户
			if (isSubscribedFans) {
				// 对于已关注的用户，通常只能接收到scan事件；
				// 这里的处理逻辑，主要是为了处理以前的逻辑错误，以更正旧有的数据
				if (isSubscribeEvent) {
					fans.setLastSubscribeDate(now);
					fans.setLastSubscribeSceneId(sceneId);
				}
			}
			// 如果是未关注的用户,可能的粉丝来源，如oauth之类的
			else {
				fans.setSceneId(sceneId);
				fans.setSubscribeDate(now);
				fans.setLastSubscribeDate(now);
				fans.setLastSubscribeSceneId(sceneId);
			}
		}
	}

	public Fans unsubscribe(String openid, PublicAccount wcp)  {
		Fans fans = getFans(openid, wcp.getId());

		fans.setUnSubscribeDate(new Date());
		fans.setStatus(FansStatus.unsubscribe);

		super.save(fans);

		return fans;
	}

	private Fans getFans(String openid, Integer pid) {
		Fans fans = this.byOpenId(openid);
		if (fans == null) {
			fans = new Fans();
			fans.toNew();

			fans.setOpenId(openid);
			fans.setPublicAccountId(pid);
		} else {
			fans.toPersist();
		}
		return fans;
	}

	public Fans byOpenId(String openid)  {

		Oql oql = new Oql();
		{
			oql.setType(Fans.class);
			oql.setSelects("Fans.*");
			oql.setFilter("openid=?");
			oql.setParameters(new QueryParameters());
			oql.getParameters().add("@openid", openid, Types.VARCHAR);
		}

		Fans fans = this.queryFirst(oql);

		return fans;
	}

	/* OAuth的仅OpenId模式下，附加Fans */
	public Fans attachByOpenId(String code, String originalId) {
		// logger.warn("Fanservice.attachByOpenid.code:" + code);
		// logger.warn("Fanservice.attachByOpenid.originalId:" + originalId);

		IPublicAccountService wcService = ServiceFactory.create(IPublicAccountService.class);
		PublicAccount pa = wcService.byOriginalId(originalId);
		if (pa == null) {
			throw new NetsharpException("没有找到公众号，原始id：" + originalId);
		}

		// logger.warn("Fanservice.attachByOpenid.PublicAccount:" +
		// pa.toString());

		OAuthRequest oauth = new OAuthRequest();
		{
			oauth.setAppId(pa.getAppId());
			oauth.setAppSecret(pa.getAppSecret());
			oauth.setScope(OauthScope.snsapi_base);
			oauth.setCode(code);
		}

		Fans fans = null;
		try {
			OAuthResponse response = oauth.getResponse();
			String openId = response.getOpenid();
			fans = this.queryFansInfo(openId, pa);
		} catch (Exception ex) {
			logger.error("", ex);
		}

		return fans;
	}

	/*
	 * 调用微信接口（UserInfoRequest），根据openid查询粉丝基本信息 sceneId
	 * 粉丝关注公众号时，才会传递此参数；如果oauth场景则不需要传递(传递0即可)
	 */
	public Fans queryFansInfo(String openId, PublicAccount pa) {
		UserInfoRequest userRequest = new UserInfoRequest();
		userRequest.setOpenId(openId);
		//
		AccessToken ato = AccessTokenManage.getTokenByAppId(pa.getAppId());
		userRequest.setTokenInfo(ato);

		// logger.warn("开始查询用户信息,执行调用....");
		UserInfoResponse ur = userRequest.getResponse();

		Fans fans = getFans(openId, pa.getId());
		if (fans != null && ur != null) {
			fans.setNickname(ur.getNickname());
			fans.setCountry(ur.getCountry());
			fans.setProvince(ur.getProvince());
			fans.setSex(String.valueOf(ur.getSex()));
			fans.setCity(ur.getCity());
			fans.setHeadImgUrl(ur.getHeadimgurl());
			fans.setUnionid(ur.getUnionid());
			fans.setLanguage(ur.getLanguage());

			// 过滤用户昵称中的无效字符
			String nickname = fans.getNickname();
			nickname = nickname.replaceAll("\\\\x\\w\\w", "");
			fans.setNickname(nickname);

			fans = this.save(fans);
		}

		return fans;
	}

	public Integer getUserIdByFansId(Integer fid) {
		if (fid == null || fid < 1) {
			return null;
		}
		String cmdText = "select user_id from " + MtableManager.getMtable(type).getTableName() + " where id = " + fid;
		return (Integer) this.pm.executeScalar(cmdText, null);
	}

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Fans> getFansOfToday(Integer fromFansId) {
		String today = simpleDateFormat.format(new Date());

		String beginTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";

		return getFansByDate(beginTime, endTime, fromFansId);
	}

	@Override
	public List<Fans> getFansByDate(String fromDate, String toDate, Integer fromFansId) {
		Oql oql = new Oql();

		oql.setType(Fans.class);
		oql.setSelects("Fans.*");
		String filter = "createTime >= ? and createTime < ?";
		if (fromFansId > 0) {
			filter += " and id > " + fromFansId;
		}
		oql.setFilter(filter);
		oql.setOrderby("id asc");

		QueryParameters params = new QueryParameters();
		params.add("@1", fromDate, Types.VARCHAR);
		params.add("@2", toDate, Types.VARCHAR);

		oql.setParameters(params);

		List<Fans> fansList = this.pm.queryList(oql);
		if (fansList == null) {
			fansList = new ArrayList<Fans>();
		}

		return fansList;
	}

	@Override
	public List<Fans> getSubscriberOfToday(Integer fromFansId) {
		String today = simpleDateFormat.format(new Date());

		String beginTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";

		return getSubscriberOfToday(beginTime, endTime, fromFansId);
	}

	@Override
	public List<Fans> getSubscriberOfToday(String fromDate, String toDate, Integer fromFansId) {
		Oql oql = new Oql();

		oql.setType(Fans.class);
		oql.setSelects("Fans.*");
		String filter = "subscribeDate is not null and subscribeDate >= ? and subscribeDate < ?";
		if (fromFansId > 0) {
			filter += " and id > " + fromFansId;
		}
		oql.setFilter(filter);
		oql.setOrderby("id asc");

		QueryParameters params = new QueryParameters();
		params.add("@1", fromDate, Types.VARCHAR);
		params.add("@2", toDate, Types.VARCHAR);

		oql.setParameters(params);

		List<Fans> fansList = this.pm.queryList(oql);
		if (fansList == null) {
			fansList = new ArrayList<Fans>();
		}

		return fansList;
	}

	@Override
	public Integer updateFans(Fans fans) {
		if (fans == null || fans.getId() <= 0) {
			return 0;
		}
		fans.toPersist();
		boolean rs = this.pm.save(fans);
		return rs ? 1 : 0;
	}

	@Override
	public Fans getFansByUserId(Integer userId) {
		Oql oql = new Oql();

		oql.setType(Fans.class);
		oql.setSelects("Fans.*");
		String filter = "userId =" + userId;

		oql.setFilter(filter);
		oql.setOrderby("id desc");

		return this.pm.queryFirst(oql);
	}

	@Override
	public void bindUserToFans(Integer userId, Integer fansId) {
		Fans fans = byId(fansId);

		if (fans != null) {
			Integer __userId = fans.getUserId() == null ? 0 : fans.getUserId();
			/*
			 * 处理两种情况 ： 1. 粉丝没有绑定用户：执行绑定； 2. 粉丝之前绑定的用户与当前登录的用户不是同一个用户：切换绑定用户
			 */
			if (userId > 0 && __userId != userId) {
				fans.setUserId(userId);
				this.updateFans(fans);
			}
		}
	}
}