package org.netsharp.wx.pa.response;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.ReflectManager;
import org.netsharp.wx.pa.base.INGraphicReplyService;
import org.netsharp.wx.pa.base.INTextReplyService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;

public class PublicAccountReply {
    private static Log logger = LogFactory.getLog(PublicAccountReply.class.getSimpleName());

    private final  HashMap<String, NReply> replies              = new HashMap<String, NReply>();
    private        List<IWeixinSubscriber> subscribers          = null;
    private static IPublicAccountService   publicAccountService = ServiceFactory.create(IPublicAccountService.class);

    private String        originalId;
    private PublicAccount publicAccount;

    public final NReply getReply(String key) {

        NReply reply = replies.get(key);

        if (reply == null) {
            logger.debug("根据关键字查询回复,关键字：" + key + ",回复：没有得到");
        } else {
            logger.debug("根据关键字查询回复,关键字：" + key + ",回复：" + reply.getClass().getName());
        }

        return reply;
    }

    public final synchronized List<IWeixinSubscriber> getSubscribers() {

        if (this.subscribers == null) {
            this.subscribers = new ArrayList<IWeixinSubscriber>();

            List<NWeixinSubscriber> ns = publicAccountService.querySubscribers(this.publicAccount.getId());

            for (NWeixinSubscriber nsubscriber : ns) {
                IWeixinSubscriber subscriber = (IWeixinSubscriber) ReflectManager.newInstance(nsubscriber.getJavaType());

                if (subscriber != null) {
                    this.subscribers.add(subscriber);
                }
            }
        }

        return this.subscribers;
    }

    public final HashMap<String, NReply> getReplies() {
        return replies;
    }

    private void distroy() {
        this.replies.clear();

        if (this.subscribers != null) {
            this.subscribers.clear();
            this.subscribers = null;
        }
    }

    // 重新加载公众号数据是否完成的标识
    private boolean isReloaded = false;

    /**
     * @param originalId
     * @throws BusinessException
     * @
     */
    public final void reload(String originalId) throws BusinessException, NetsharpException {
        isReloaded = false;
        synchronized (replies) {
            if (this.isReloaded == true) {
                return;
            }
            this.distroy();

            PublicAccount wcp = publicAccountService.byOriginalId(originalId);
            if (wcp == null) {
                throw new NetsharpException("公众号: [" + this.getOriginalId() + "]不存在！");
            }
            this.publicAccount = wcp;

            //
            INTextReplyService textReplyService = ServiceFactory.create(INTextReplyService.class);

            Oql oql = new Oql();
            {
                oql.setType(NTextReply.class);
                oql.setSelects("NTextReply.*");
                oql.setFilter("publicAccountId=?");
                oql.setParameters(new QueryParameters());
                oql.getParameters().add("@publicAccountId", wcp.getId(), Types.INTEGER);
            }

            List<NTextReply> trs = textReplyService.queryList(oql);

            for (NTextReply reply : trs) {
                try {
                    logger.info("add text reply by key " + reply.getKeyword());

                    replies.put(reply.getKeyword(), reply);
                } catch (IllegalArgumentException ae) {
                    throw new BusinessException("警告：素材（关键字[" + reply.getKeyword() + "]）已经存在");
                }
            }

            //
            INGraphicReplyService graphicService = ServiceFactory.create(INGraphicReplyService.class);

            oql.setType(NGraphicReply.class);
            oql.setSelects("NGraphicReply.*,NGraphicReply.Articles.*");

            List<NGraphicReply> grs = graphicService.queryList(oql);

            for (NGraphicReply reply : grs) {
                try {
                    logger.info("add graphic reply by key " + reply.getKeyword());
                    replies.put(reply.getKeyword(), reply);
                } catch (IllegalArgumentException ae) {
                    throw new BusinessException("警告：素材（关键字[" + reply.getKeyword() + "]）已经存在");
                }
            }

            this.isReloaded = true;
        }
    }

    public final String getOriginalId() {
        return originalId;
    }

    public final void setOriginalId(String value) {
        originalId = value;
    }

    public final PublicAccount getPublicAccount() {
        return publicAccount;
    }
}