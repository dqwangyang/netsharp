package org.netsharp.wx.pa.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.INTextReplyService;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.PublicAccount;

@Service
public class NTextReplyService extends PersistableService<NTextReply> implements INTextReplyService {

    public NTextReplyService() {
        super();
        this.type = NTextReply.class;
    }

    @Override
    public List<NTextReply> getTextReplies(PublicAccount publicAccount) {
        return getTextReplies(publicAccount.getId());
    }

    @Override
    public List<NTextReply> getTextReplies(Integer publicAccountId) {
        Oql oql = new Oql();
        oql.setType(NTextReply.class);
        oql.setSelects("NTextReply.*");
        oql.setFilter("publicAccountId=?");
        oql.setParameters(new QueryParameters());
        oql.getParameters().add("@publicAccountId", publicAccountId, Types.INTEGER);

        return this.queryList(oql);
    }
}