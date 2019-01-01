package org.netsharp.wx.pa.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.INGraphicReplyService;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.PublicAccount;

@Service
public class NGraphicReplyService extends PersistableService<NGraphicReply> implements INGraphicReplyService {

    public NGraphicReplyService() {
        super();
        this.type = NGraphicReply.class;
    }

    @Override
    public List<NGraphicReply> getReplies(Integer publicAccountId) {
        Oql oql = new Oql();
        oql.setType(NGraphicReply.class);
        oql.setSelects("NGraphicReply.*,NGraphicReply.Articles.*");
        oql.setFilter("publicAccountId=?");
        oql.setParameters(new QueryParameters());
        oql.getParameters().add("@publicAccountId", publicAccountId, Types.INTEGER);

        return this.queryList(oql);
    }

    @Override
    public List<NGraphicReply> getReplies(PublicAccount publicAccount) {
        return null;
    }
}