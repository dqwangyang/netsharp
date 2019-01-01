package org.netsharp.wx.pa.service;

import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.Service;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.IRedPacketService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.RedPacket;
import org.netsharp.wx.pa.entity.RedPacketUser;

/**
 * @author kxh
 */
@Service
public class RedPacketService extends PersistableService<RedPacket> implements IRedPacketService {
	
    private static IPersister<IPersistable> dao = PersisterFactory.create();
    static Log logger = LogFactory.getLog(RedPacketService.class.getSimpleName());

    public RedPacketService() {
        super();
        this.type = RedPacket.class;
    }

    @Override
    public RedPacket createRedPacket(RedPacket packet) {
        dao.save(packet);

        return packet;
    }

    @Override
    public int updateRedPacketStatus(RedPacket packet) {
        String sql = "update wx_redpacket set status=? where id=? and status != ?";

        QueryParameters params = new QueryParameters();
        params.add("@status", packet.getStatus(), Types.VARCHAR);
        params.add("@id", packet.getId(), Types.INTEGER);
        params.add("@status", packet.getStatus(), Types.VARCHAR);

        return dao.executeNonQuery(sql,params);
    }

    @Override
    public RedPacket getRedPacketById(Integer redPacketId) {
    	logger.warn("红包活动查询参数：" + redPacketId);
    	
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("RedPacket.*");
        oql.setFilter("id = ?");

        QueryParameters qps = new QueryParameters();
        qps.add("@id", redPacketId, Types.INTEGER);
        oql.setParameters(qps);

        return this.pm.queryFirst(oql);
    }

    @Override
    public RedPacketUser sendRedPacket(Integer redPacketId, Fans fans) {
        RedPacket redPacket = getRedPacketById(redPacketId);

        return sendRedPacket(redPacket, fans);
    }

    // TODO 发红包接口目前没有事务管理
    @Override
    public RedPacketUser sendRedPacket(RedPacket redPacket, Fans fans) {
    	throw new NetsharpException("not implements ");
//        // 红包活动状态，0表示正常，1表示活动提前结束（提示红包已领完），2表示红包已真正领完
//        if (!"0".equals(redPacket.getStatus())) {
//            throw new BusinessException("END");
//        }
//
//        if (fans == null) {
//            throw new BusinessException(String.format("参数 fans 不能为空"));
//        }
//        List<RedPacketUser> rpUsers = this.getRedPacketUserByFansId(fans.getId(), redPacket.getId());
//        if (rpUsers.size() > 0) {
//            logger.warn(String.format("用户[fans: %s]已领取过红包，红包活动ID:[%s]", fans.getId(), redPacket.getId()));
//            throw new BusinessException("您已参与过本次红包活动，每用户只能参与一次");
//        }
//
//        if (fans.getStatus() == FansStatus.unsubscribe) {
//            logger.warn(String.format("用户[fans: %s]没有关注[易快修]，不允许领取红包", fans.getId()));
//            throw new BusinessException("只有[易快修]的关注用户才可以领取红包");
//        }
//
//        // 调用微信发红包接口
//        RedPacketRequest request = new RedPacketRequest(redPacket, fans.getOpenId());
//        RedPacketResponse response = request.getResponse();
//        logger.warn(JsonManage.serialize2(response));
//
//        if (response.getResult_Code().equals(ResultCodeEnum.FAIL.getText())) {
//            if ("NOTENOUGH".equals(response.getError_code())) {
//                redPacket.setStatus("2"); // TODO 红包已经发完，活动结束，待重购
//                updateRedPacketStatus(redPacket);
//            }
//            throw new BusinessException(response.getError_code());
//        }
//
//        // 记录用户领取的红包信息
//        RedPacketUser redPacketUser = new RedPacketUser();
//        redPacketUser.toNew();
//
//        redPacketUser.setRedPacketId(redPacket.getId());
//        redPacketUser.setTotalAmount(Integer.parseInt(response.getTotal_amount()));
//        redPacketUser.setWxOpenId(response.getRe_openid());
//        redPacketUser.setFansId(fans.getId());
//        redPacketUser.setUserId(fans.getUserId());
//
//        dao.save(redPacketUser);
//
//        // 更新红包已发放的金额及活动的状态
//        redPacket.toPersist();
//        redPacket.setConsumeAmount(redPacket.getConsumeAmount() + redPacketUser.getTotalAmount());
//        // 通常这里的状态更新不会发生，因为当红包金额不够发放时，微信会返回失败的
//        if (redPacket.getConsumeAmount() >= redPacket.getAmount()) {
//            redPacket.setStatus("2");
//        }
//        dao.save(redPacket);
//
//        return redPacketUser;
    }

    public List<RedPacketUser> getRedPacketUserListByOpenId(String openId) {
        Oql oql = new Oql();
        oql.setType(RedPacketUser.class);
        oql.setSelects("RedPacketUser.*");
        oql.setFilter("wxOpenId = ?");

        QueryParameters qps = new QueryParameters();
        qps.add("@wxOpenId", openId, Types.VARCHAR);
        oql.setParameters(qps);

        return queryRedPacketUsers(oql);
    }

    @Override
    public List<RedPacketUser> getRedPacketUserListByFansId(Integer fansId) {
        Oql oql = new Oql();
        oql.setType(RedPacketUser.class);
        oql.setSelects("RedPacketUser.*");
        oql.setFilter("fansId = ?");

        QueryParameters qps = new QueryParameters();
        qps.add("@fansId", fansId, Types.INTEGER);
        oql.setParameters(qps);

        return queryRedPacketUsers(oql);
    }

	private List<RedPacketUser> queryRedPacketUsers(Oql oql) {
		IPersister<RedPacketUser> redpm = PersisterFactory.create();
        return redpm.queryList(oql);
    }

    @Override
    public List<RedPacketUser> getRedPacketUserListByUserId(Integer userId) {
        Oql oql = new Oql();
        oql.setType(RedPacketUser.class);
        oql.setSelects("RedPacketUser.*");
        oql.setFilter("userId = ?");

        QueryParameters qps = new QueryParameters();
        qps.add("@userId", userId, Types.INTEGER);
        oql.setParameters(qps);

        return queryRedPacketUsers(oql);
    }

    @Override
    public List<RedPacketUser> getRedPacketUserByFansId(Integer fansId, Integer redPacketId) {
        Oql oql = new Oql();
        oql.setType(RedPacketUser.class);
        oql.setSelects("RedPacketUser.*");
        oql.setFilter("fansId = ? and redPacketId=?");

        QueryParameters qps = new QueryParameters();
        qps.add("@fansId", fansId, Types.INTEGER);
        qps.add("@redPacketId", redPacketId, Types.INTEGER);

        oql.setParameters(qps);

        return queryRedPacketUsers(oql);
    }

    @Override
    public List<RedPacketUser> getRedPacketUserByOpenid(String openId, Integer redPacketId) {
        Oql oql = new Oql();
        oql.setType(RedPacketUser.class);
        oql.setSelects("RedPacketUser.*");
        oql.setFilter("openId = ? and redPacketId=?");

        QueryParameters qps = new QueryParameters();
        qps.add("@openId", openId, Types.VARCHAR);
        qps.add("@redPacketId", redPacketId, Types.INTEGER);

        oql.setParameters(qps);

        return queryRedPacketUsers(oql);
    }
}
