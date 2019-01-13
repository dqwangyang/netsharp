package org.netsharp.wx.pa.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;

@Service
public class PublicAccountService extends PersistableService<PublicAccount> implements IPublicAccountService {

    public PublicAccountService() {
        super();
        this.type = PublicAccount.class;
    }

    @Override
    public PublicAccount byOriginalId(String oid)  {

        Oql oql = new Oql();
        oql.setType(PublicAccount.class);
        oql.setSelects("PublicAccount.*");
        oql.setFilter("OriginalId=?");
        oql.setParameters(new QueryParameters());
        oql.getParameters().add("@OriginalId", oid, Types.VARCHAR);

        PublicAccount wcp = this.pm.queryFirst(oql);

        return wcp;
    }

    @Override
    public PublicAccount byAppId(String appId)  {
        Oql oql = new Oql();
        oql.setType(PublicAccount.class);
        oql.setSelects("PublicAccount.*");
        oql.setFilter("appId=?");
        oql.setParameters(new QueryParameters());
        oql.getParameters().add("@appId", appId, Types.VARCHAR);

        PublicAccount wcp = this.pm.queryFirst(oql);

        return wcp;
    }

    public List<NWeixinSubscriber> querySubscribers(Long publicAccountId) {

        IPersister<NWeixinSubscriber> pst = PersisterFactory.create();
        Oql oql = new Oql();
        {
            oql.setType(NWeixinSubscriber.class);
            oql.setSelects("NWeixinSubscriber.*");
            oql.setFilter("publicAccountId = ?");

            oql.getParameters().add("@publicAccountId", publicAccountId, Types.INTEGER);
        }

        List<NWeixinSubscriber> subscribers = pst.queryList(oql);

        return subscribers;
    }

}