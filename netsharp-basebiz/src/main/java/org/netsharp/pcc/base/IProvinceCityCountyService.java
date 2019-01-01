package org.netsharp.pcc.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.pcc.entity.ProvinceCityCounty;

public interface IProvinceCityCountyService extends IPersistableService<ProvinceCityCounty>{
	
	/**
	 * 查询省级别数据
	 * @return
	 */
	List<ProvinceCityCounty> queryProvince();
	
	/**
	 * 通过省份ID，查询市级别数据
	 * @param provinceId
	 * @return
	 */
	List<ProvinceCityCounty> queryCityByProvinceId(Integer provinceId);
	
	/**
	 * 根据parentId获取
	 * @param parentId
	 * @return
	 */
	List<ProvinceCityCounty> queryPcc(Integer parentId);
}