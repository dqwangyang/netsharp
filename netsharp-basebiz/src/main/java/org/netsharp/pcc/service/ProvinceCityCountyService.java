package org.netsharp.pcc.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.pcc.base.IProvinceCityCountyService;
import org.netsharp.pcc.entity.ProvinceCityCounty;
import org.netsharp.service.PersistableService;

@Service
public class ProvinceCityCountyService extends PersistableService<ProvinceCityCounty> implements IProvinceCityCountyService {

	public ProvinceCityCountyService() {
		super();
		this.type = ProvinceCityCounty.class;
	}

	@Override
	public List<ProvinceCityCounty> queryCityByProvinceId(Integer provinceId) {

		Oql oql = new Oql();
		{
			oql.setType(ProvinceCityCounty.class);
			oql.setSelects("id,name");
			oql.setFilter("parentId =?");
			oql.getParameters().add("parentId", provinceId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public List<ProvinceCityCounty> queryProvince() {

		Oql oql = new Oql();
		{
			oql.setType(ProvinceCityCounty.class);
			oql.setSelects("id,name");
			oql.setFilter("parentId is null");
		}
		return this.queryList(oql);
	}

	public List<ProvinceCityCounty> queryPcc(Integer parentId) {

		Oql oql = new Oql();
		{
			oql.setType(ProvinceCityCounty.class);
			oql.setSelects("id,code,name");
			oql.setFilter("parentId is null");
		}
		if(parentId != null){
			
			oql.setFilter("parentId =?");
			oql.getParameters().add("parentId", parentId, Types.INTEGER);
		}
		return this.queryList(oql);
	}
}
