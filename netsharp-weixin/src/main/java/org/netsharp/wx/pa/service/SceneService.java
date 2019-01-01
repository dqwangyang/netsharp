package org.netsharp.wx.pa.service;

import java.sql.Types;
import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.ISceneService;
import org.netsharp.wx.pa.entity.Scene;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.api.accesstoken.PAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.PaConfiguration;
import org.netsharp.wx.sdk.mp.api.qrcode.QrCodeRequest;
import org.netsharp.wx.sdk.mp.api.qrcode.QrCodeResponse;

@Service
public class SceneService extends PersistableService<Scene> implements ISceneService {

    public SceneService() {
        super();
        this.type = Scene.class;
    }

    /**
     * @param sceneId       微信二维码场景值
     * @param expireSeconds 过期时间,以秒为单位
     * @return
     */
    public Scene getTimeLimitQrCode(int sceneId, int expireSeconds,String originalId) {
    	
        if (expireSeconds <= 0 || expireSeconds > MAX_EXPIRE_SECONDS) {
            expireSeconds = MAX_EXPIRE_SECONDS;
        }
        if (sceneId < MIN_QRCODE_SCENEID) {
            throw new RuntimeException("临时二维码场景值不能小于100000");
        }
        Scene scene = getByWxSceneId(sceneId);

        Long now = System.currentTimeMillis();
        // 如果临时二维码不存在，则创建
        if (scene == null) {
            return obtainWeixinQrCode(sceneId, "临时", expireSeconds,originalId);
        }
        // 如果临时二维码过期，则更新
        else if (now - scene.getCreateTime().getTime() > scene.getExpireSeconds() - 10) {
        	
            QrCodeResponse response = getWeixinQrCode(sceneId, expireSeconds,originalId);

            scene.setQRCodeUrl(response.getQrCodeUrl());
            scene.setJsonString(response.getJsonString());
            scene.setExpireSeconds(expireSeconds);
            scene.setUpdateTime(new Date());
            scene.toPersist();

            pm.save(scene);
        }

        return scene;
    }

    @Override
    public Scene getTimeLimitQrCodeByUserId(Integer userId,String originalId) {
    	
        String cmdText = "select max(weixin_scene_id) from " + MtableManager.getMtable(this.type).getTableName() + " where weixin_scene_id > " + MIN_USER_SCENEID;

        IDataAccess dao     = DataAccessFactory.create();
        Integer     sceneId = dao.executeInt(cmdText, null);
        if (sceneId == null) {
            sceneId = MIN_USER_SCENEID;
        } else {
            sceneId++;
        }
//        int sceneId = MIN_USER_SCENEID + userId;
        if (sceneId > MAX_USER_SCENEID) {
            throw new RuntimeException("用户可用的临时二维码已用完!");
        }
        return getTimeLimitQrCode(sceneId, MAX_EXPIRE_SECONDS,originalId);
    }

    @Override
    public Scene getTimeLimitQrCodeByOrderId(Integer orderId,String originalId) {
    	throw new NetsharpException("未实现次功能！");
//        int sceneId = MIN_ORDER_SCENEID + orderId;
//
//        return getTimeLimitQrCode(sceneId, MAX_EXPIRE_SECONDS);
    }

    /* 获取一个个性二维码场景值，已经持久化状态 */
    public Scene obtain(String memoto,String originalId) {
        // 将来考虑如果已经有停用的scene，可以从停用中取

        String cmdText = "select max(weixin_scene_id) from " + MtableManager.getMtable(this.type).getTableName() + " where weixin_scene_id < " + MIN_USER_SCENEID;

        IDataAccess dao     = DataAccessFactory.create();
        Integer     sceneId = dao.executeInt(cmdText, null);
        if (sceneId == null) {
            sceneId = 0;
        }

        sceneId++;

        Scene scene = this.obtainWeixinQrCode(sceneId, memoto, 0,originalId);

        return scene;
    }

    // 根据sceneId得到微信二维码
    private Scene obtainWeixinQrCode(Integer weixinSeneId, String memoto, int expireSeconds,String originalId) {
    	
        QrCodeResponse response = getWeixinQrCode(weixinSeneId, expireSeconds,originalId);

        Scene scene = new Scene();
        {
            scene.toNew();
            scene.setWeixinSceneId(weixinSeneId);
            scene.setQRCodeUrl(response.getQrCodeUrl());
            scene.setJsonString(response.getJsonString());
            scene.setExpireSeconds(expireSeconds);
            scene.setMemoto(memoto);
        }

        super.save(scene);

        return scene;
    }

    private QrCodeResponse getWeixinQrCode(int sceneId, int expireSeconds,String originalId) {
    	
    	PAccount pa = PaConfiguration.get(originalId);
    	
        AccessToken at = AccessTokenManage.getTokenByAppId( pa.getAppId() );

        QrCodeRequest request = new QrCodeRequest();
        request.setTokenInfo(at);

        request.setSenceId(sceneId);

        // 过期时间，为0表示是持久的二维码，否则是临时二维码
        if (expireSeconds > 0) {
            request.setExpireSeconds(expireSeconds);
        } else {
            request.setExpireSeconds(0);
        }

        QrCodeResponse response = request.getResponse();

        return response;
    }

    public void disable(Scene scene) {
        scene.setDisabled(true);
        scene.toPersist();

        super.save(scene);
    }

    @Override
    public Scene getByWxSceneId(int wxSceneId) {
        Oql oql = new Oql();
        oql.setType(Scene.class);
        oql.setSelects("*");
        oql.setFilter("weixin_scene_id=?");

        QueryParameters qps = new QueryParameters();
        qps.add("@", wxSceneId, Types.INTEGER);
        oql.setParameters(qps);

        return this.queryFirst(oql);
    }
}
