package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Row;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.scrum.base.IIteratorStatisticsService;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.scrum.entity.IteratorStatistics;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.Support;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

/**
 * 迭代统计生成报表服务
 * @author MengLingqin
 * 2016-06-07 09:20
 */
@Service
public class IteratorStatisticsService extends PersistableService<IteratorStatistics> implements IIteratorStatisticsService {

	public IteratorStatisticsService(){
		super();
		this.type = IteratorStatistics.class;
	}
	
	/**
	 * 生成报表
	 */
	public void run() {
		// TODO Auto-generated method stub
		//删除报表
		this.deleteReport();
		//生成三级数据
		this.saveIoeListToDB();
		//生成二级数据，更新三级父ID
		this.saveIeListToDB();
		//生成一级数据，更新二级父ID
		this.saveIListToDB();
	}
	
	/**
	 * 删除迭代统计报表
	 */
	private void deleteReport(){
		
		String table =  MtableManager.getMtable(this.type).getTableName();
		String sql = " delete from " + table;
		this.pm.executeNonQuery(sql, null);
	}
	
	/**
	 * 获取最近两个迭代的Id集合
	 * @return
	 */
	private List<Long> getIterationIdList(){
		
		List<Long> iterationIdList = new ArrayList<Long>();
		
		String sql = "select id from it_iteration order by id desc limit 2";
		DataTable dt = this.pm.executeTable(sql, null);
		for(Row row : dt){
			iterationIdList.add(turnLong(row.get("id")));
		}
		
		return iterationIdList;
	}

	/**
	 * 统计项目相关的数据（三级数据）（迭代--部门--负责人）
	 * @return
	 */
	private DataTable queryIoeItemDataTable(List<Long> iterationIdList){
		
		String projectTable =  MtableManager.getMtable(Story.class).getTableName();
		String iterationTable =  MtableManager.getMtable(Iteration.class).getTableName();
		String employeeTable =  MtableManager.getMtable(Employee.class).getTableName();
		
		StringBuilder builder = new StringBuilder();
		builder.append("select ").append(StringManager.NewLine);
		builder.append("tt.*,").append(StringManager.NewLine);
		builder.append("ti.startTime,").append(StringManager.NewLine);
		builder.append("ti.endTime,").append(StringManager.NewLine);
		builder.append("spe.name").append(StringManager.NewLine);
		builder.append("from(").append(StringManager.NewLine);
		builder.append("select ").append(StringManager.NewLine);
		builder.append("tmp.ii,").append(StringManager.NewLine);
		builder.append("tmp.oi,").append(StringManager.NewLine);
		builder.append("tmp.ei,").append(StringManager.NewLine);
		builder.append("sum(tmp.itc) as sitc,").append(StringManager.NewLine);
		builder.append("sum(tmp.eh) as seh,").append(StringManager.NewLine);
		builder.append("sum(tmp.ah) as sah,").append(StringManager.NewLine);
		builder.append("sum(tmp.fitc) as sfitc,").append(StringManager.NewLine);
		builder.append("sum(tmp.fiet) as sfiet,").append(StringManager.NewLine);
		builder.append("sum(tmp.firt) as sfirt,").append(StringManager.NewLine);
		builder.append("sum(tmp.ufitc) as sufitc,").append(StringManager.NewLine);
		builder.append("sum(tmp.ufiet) as sufiet").append(StringManager.NewLine);
		builder.append("from(").append(StringManager.NewLine);
		builder.append("select ").append(StringManager.NewLine);
		builder.append("ip.iterationId as ii,").append(StringManager.NewLine);
		builder.append("ip.organization_id as oi,").append(StringManager.NewLine);
		builder.append("ip.ownerId ei,").append(StringManager.NewLine);
		builder.append("1 as itc,").append(StringManager.NewLine);
		builder.append("ifnull(ip.estimateHours, 0) as eh,").append(StringManager.NewLine);
		builder.append("ifnull(ip.actualHours, 0) as ah,").append(StringManager.NewLine);
		builder.append("(case when (ip.status='hibernate' or ip.status='process') then 0 else 1 end) as fitc,").append(StringManager.NewLine);
		builder.append("(case when (ip.status='hibernate' or ip.status='process') then 0 else ifnull(ip.estimateHours, 0) end) as fiet,").append(StringManager.NewLine);
		builder.append("(case when (ip.status='hibernate' or ip.status='process') then 0 else ifnull(ip.actualHours, 0) end) as firt,").append(StringManager.NewLine);
		builder.append("(case when (ip.status='hibernate' or ip.status='process') then 1 else 0 end) as ufitc,").append(StringManager.NewLine);
		builder.append("(case when (ip.status='hibernate' or ip.status='process') then ifnull(ip.estimateHours, 0) else 0 end) as ufiet").append(StringManager.NewLine);
		builder.append("from "+projectTable+" ip").append(StringManager.NewLine);
		builder.append("where ip.status!='stop' and ip.iterationId in (").append(StringManager.join(",", iterationIdList)).append(")").append(StringManager.NewLine);
		builder.append(") tmp ").append(StringManager.NewLine);
		builder.append("group by tmp.ii, tmp.oi, tmp.ei) tt ").append(StringManager.NewLine);
		builder.append("left join "+iterationTable+" ti on tt.ii=ti.id ").append(StringManager.NewLine);
		builder.append("left join "+employeeTable+" spe on tt.ei=spe.id").append(StringManager.NewLine);
		String sql = builder.toString();
		
		return this.pm.executeTable(sql, null);
	}
	
	/**
	 * 组织迭代数据集合（三级数据）（迭代--部门--负责人）
	 * @param ioeList
	 * @param itemRow
	 * @param supportRow
	 */
	private void organizeIoeList(List<IteratorStatistics> ioeList, Row itemRow, Row supportRow, Long itId){
		
		IteratorStatistics is = new IteratorStatistics();
		is.setEntityState(EntityState.New);
		if(itId==-1L){
			is.setName(itemRow.getString("name"));
			is.setIterationId(turnLong(itemRow.get("ii")));
			is.setOrganizationId(turnLong(itemRow.get("oi")));
			is.setEmployeeId(turnLong(itemRow.get("ei")));
			is.setItemTotalCount(turnInt(itemRow.get("sitc")));
			is.setItemEstimationTime(turnInt(itemRow.get("seh")));
			is.setItemRealTime(turnInt(itemRow.get("sah")));
			is.setFinishedItemTotalCount(turnInt(itemRow.get("sfitc")));
			is.setFinishedItemEstimationTime(turnInt(itemRow.get("sfiet")));
			is.setFinishedItemRealTime(turnInt(itemRow.get("sfirt")));
			is.setUnFinishedItemTotalCount(turnInt(itemRow.get("sufitc")));
			is.setUnFinishedItemEstimationTime(turnInt(itemRow.get("sufiet")));
		}else{
			is.setName(itemRow.getString("ename"));
			is.setIterationId(itId);
			is.setOrganizationId(turnLong(itemRow.get("oid")));
			is.setEmployeeId(turnLong(itemRow.get("eid")));
			is.setItemTotalCount(0);
			is.setItemEstimationTime(0);
			is.setItemRealTime(0);
			is.setFinishedItemTotalCount(0);
			is.setFinishedItemEstimationTime(0);
			is.setFinishedItemRealTime(0);
			is.setUnFinishedItemTotalCount(0);
			is.setUnFinishedItemEstimationTime(0);
		}
		if(supportRow==null){
			is.setSupportTotalCount(0);
			is.setSupportEstimationTime(0);
			is.setSupportRealTime(0);
			is.setFinishedSupportTotalCount(0);
			is.setFinishedSupportEstimationTime(0);
			is.setFinishedSupportRealTime(0);
			is.setUnfinishedSupportTotalCount(0);
			is.setUnfinishedSupportEstimationTime(0);
		}else{
			is.setSupportTotalCount(turnInt(supportRow.get("sstc")));
			is.setSupportEstimationTime(turnInt(supportRow.get("seh")));
			is.setSupportRealTime(turnInt(supportRow.get("sah")));
			is.setFinishedSupportTotalCount(turnInt(supportRow.get("sfstc")));
			is.setFinishedSupportEstimationTime(turnInt(supportRow.get("sfset")));
			is.setFinishedSupportRealTime(turnInt(supportRow.get("sfsrt")));
			is.setUnfinishedSupportTotalCount(turnInt(supportRow.get("sufstc")));
			is.setUnfinishedSupportEstimationTime(turnInt(supportRow.get("sufset")));
		}
		
		ioeList.add(is);
	}
	
	/**
	 * 持久化迭代数据集合（三级数据）（迭代--部门--负责人）
	 */
	private void saveIoeListToDB(){
		
		List<IteratorStatistics> ioeList = new ArrayList<IteratorStatistics>();
		String supportTable =  MtableManager.getMtable(Support.class).getTableName();
		
		StringBuilder builder1 = new StringBuilder();
		builder1.append("select ").append(StringManager.NewLine);
		builder1.append("tmp.ei,").append(StringManager.NewLine);
		builder1.append("sum(tmp.stc) as sstc,").append(StringManager.NewLine);
		builder1.append("sum(tmp.eh) as seh,").append(StringManager.NewLine);
		builder1.append("sum(tmp.ah) as sah,").append(StringManager.NewLine);
		builder1.append("sum(tmp.fstc) as sfstc,").append(StringManager.NewLine);
		builder1.append("sum(tmp.fset) as sfset,").append(StringManager.NewLine);
		builder1.append("sum(tmp.fsrt) as sfsrt,").append(StringManager.NewLine);
		builder1.append("sum(tmp.ufstc) as sufstc,").append(StringManager.NewLine);
		builder1.append("sum(tmp.ufset) as sufset").append(StringManager.NewLine);
		builder1.append("from(").append(StringManager.NewLine);
		builder1.append("select ").append(StringManager.NewLine);
		builder1.append("si.ownerId as ei,").append(StringManager.NewLine);
		builder1.append("1 as stc,").append(StringManager.NewLine);
		builder1.append("ifnull(si.estimateHours, 0) as eh,").append(StringManager.NewLine);
		builder1.append("ifnull(si.actualHours, 0) as ah,").append(StringManager.NewLine);
		builder1.append("(case when (si.status='hibernate' or si.status='process') then 0 else 1 end) as fstc,").append(StringManager.NewLine);
		builder1.append("(case when (si.status='hibernate' or si.status='process') then 0 else ifnull(si.estimateHours, 0) end) as fset,").append(StringManager.NewLine);
		builder1.append("(case when (si.status='hibernate' or si.status='process') then 0 else ifnull(si.actualHours, 0) end) as fsrt,").append(StringManager.NewLine);
		builder1.append("(case when (si.status='hibernate' or si.status='process') then 1 else 0 end) as ufstc,").append(StringManager.NewLine);
		builder1.append("(case when (si.status='hibernate' or si.status='process') then ifnull(si.estimateHours, 0) else 0 end) as ufset").append(StringManager.NewLine);
		builder1.append("from ").append(StringManager.NewLine);
		builder1.append(supportTable+" si ").append(StringManager.NewLine);
		String subSql1 = builder1.toString();
		
		String subSql2 = "";
		
		StringBuilder builder3 = new StringBuilder();
		builder3.append(") tmp ").append(StringManager.NewLine);
		builder3.append("group by tmp.ei").append(StringManager.NewLine);
		String subSql3 = builder3.toString();
		
		String sql = "";
		List<Long> iterationIdList = this.getIterationIdList();
		DataTable ioeItemDataTable = this.queryIoeItemDataTable(iterationIdList);
		DataTable eSupportDataTable = null;
		
		//存在统计数据的人员
		for(Row row : ioeItemDataTable){
			subSql2 = "where si.ownerId="+row.get("ei") + " and si.status!='stop'" + StringManager.NewLine;
			sql = subSql1 + subSql2 + subSql3;
			eSupportDataTable = this.pm.executeTable(sql, null);
			Row sr = null;
			for(Row r : eSupportDataTable){
				sr = r;
			}
			organizeIoeList(ioeList, row, sr, -1L);
		}
		//不存在统计数据的人员
		Map<String, List<Row>> notExsistMap = this.getNotExsistEmployee(iterationIdList, ioeItemDataTable);
		Set<Entry<String, List<Row>>> es = notExsistMap.entrySet();
		for(Entry<String, List<Row>> entry : es){
			Long ii = turnLong(entry.getKey());
			List<Row> notExsistList = entry.getValue();
			for(Row row: notExsistList){
				subSql2 = "where si.ownerId="+row.get("eid") + " and si.status!='stop'" + StringManager.NewLine;
				sql = subSql1 + subSql2 + subSql3;
				eSupportDataTable = this.pm.executeTable(sql, null);
				Row sr = null;
				for(Row r : eSupportDataTable){
					sr = r;
				}
				organizeIoeList(ioeList, row, sr, ii);
			}
		}
		//保存
		this.saves(ioeList);
	}
	
	/**
	 * 组织迭代数据集合（二级数据）（迭代--部门）
	 * @param ioeList
	 * @param itemRow
	 */
	private void organizeIoList(List<IteratorStatistics> ioList, Row row, Long itId){
		
		IteratorStatistics is = new IteratorStatistics();
		is.setEntityState(EntityState.New);
		if(itId==-1L){
			is.setName(row.getString("name"));
			is.setIterationId(turnLong(row.get("iteration_id")));
			is.setOrganizationId(turnLong(row.get("organization_id")));
			is.setItemTotalCount(turnInt(row.get("ritc")));
			is.setItemEstimationTime(turnInt(row.get("riet")));
			is.setItemRealTime(turnInt(row.get("rirt")));
			is.setFinishedItemTotalCount(turnInt(row.get("rfitc")));
			is.setFinishedItemEstimationTime(turnInt(row.get("rfiet")));
			is.setFinishedItemRealTime(turnInt(row.get("rfirt")));
			is.setUnFinishedItemTotalCount(turnInt(row.get("ruitc")));
			is.setUnFinishedItemEstimationTime(turnInt(row.get("ruiet")));
			is.setSupportTotalCount(turnInt(row.get("rstc")));
			is.setSupportEstimationTime(turnInt(row.get("rset")));
			is.setSupportRealTime(turnInt(row.get("rsrt")));
			is.setFinishedSupportTotalCount(turnInt(row.get("rfstc")));
			is.setFinishedSupportEstimationTime(turnInt(row.get("rfset")));
			is.setFinishedSupportRealTime(turnInt(row.get("rfsrt")));
			is.setUnfinishedSupportTotalCount(turnInt(row.get("rustc")));
			is.setUnfinishedSupportEstimationTime(turnInt(row.get("ruset")));
		}else{
			is.setName(row.getString("pname"));
			is.setIterationId(itId);
			is.setOrganizationId(turnLong(row.get("oid")));
			is.setItemTotalCount(0);
			is.setItemEstimationTime(0);
			is.setItemRealTime(0);
			is.setFinishedItemTotalCount(0);
			is.setFinishedItemEstimationTime(0);
			is.setFinishedItemRealTime(0);
			is.setUnFinishedItemTotalCount(0);
			is.setUnFinishedItemEstimationTime(0);
			is.setSupportTotalCount(0);
			is.setSupportEstimationTime(0);
			is.setSupportRealTime(0);
			is.setFinishedSupportTotalCount(0);
			is.setFinishedSupportEstimationTime(0);
			is.setFinishedSupportRealTime(0);
			is.setUnfinishedSupportTotalCount(0);
			is.setUnfinishedSupportEstimationTime(0);
		}
		
		ioList.add(is);
	}
	
	/**
	 * 更新迭代数据集合（三级数据）（迭代--部门--负责人）
	 * @param ioList
	 */
	private void updateIeoList(List<IteratorStatistics> ioList){
		
		String table =  MtableManager.getMtable(this.type).getTableName();
		String sql = "";
		for(IteratorStatistics is : ioList){
			sql = "update "+table+" set parent_id="+is.getId()+" where employee_id is not null and iteration_id="+is.getIterationId()+" and organization_id="+is.getOrganizationId();
			this.pm.executeNonQuery(sql, null);
		}
	}
	
	/**
	 * 持久化迭代数据集合（二级数据）（迭代--部门）
	 */
	private void saveIeListToDB(){
		
		List<IteratorStatistics> ioList = new ArrayList<IteratorStatistics>();
		String table =  MtableManager.getMtable(this.type).getTableName();
		String organizatinTable =  MtableManager.getMtable(Organization.class).getTableName();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select ").append(StringManager.NewLine);
		sb.append("ris.iteration_id,").append(StringManager.NewLine);
		sb.append("ris.organization_id,").append(StringManager.NewLine);
		sb.append("sum(ris.item_total_count) as ritc,").append(StringManager.NewLine);
		sb.append("sum(ris.item_estimation_time) as riet,").append(StringManager.NewLine);
		sb.append("sum(ris.item_real_time) as rirt,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_item_total_count) as rfitc,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_item_estimation_time) as rfiet,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_item_real_time) as rfirt,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_item_total_count) as ruitc,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_item_estimation_time) as ruiet,").append(StringManager.NewLine);
		sb.append("sum(ris.support_total_count) as rstc,").append(StringManager.NewLine);
		sb.append("sum(ris.support_estimation_time) as rset,").append(StringManager.NewLine);
		sb.append("sum(ris.support_real_time) as rsrt,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_support_total_count) as rfstc,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_support_estimation_time) as rfset,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_support_real_time) as rfsrt,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_support_total_count) as rustc,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_support_estimation_time) as ruset,").append(StringManager.NewLine);
		sb.append("oi.name").append(StringManager.NewLine);
		sb.append("from "+table+" ris ").append(StringManager.NewLine);
		sb.append("left join "+organizatinTable+" oi on ris.organization_id=oi.id ").append(StringManager.NewLine);
		sb.append("where ris.employee_id is not null and ris.organization_id is not null and ris.iteration_id is not null").append(StringManager.NewLine);
		sb.append("group by ris.iteration_id, ris.organization_id").append(StringManager.NewLine);
		String sql = sb.toString();
		//存在统计数据的部门
		DataTable dTable = this.pm.executeTable(sql, null);
		for(Row row : dTable){
			this.organizeIoList(ioList, row, -1L);
		}
		//不存在统计数据的部门
		List<Long> iterationIdList = this.getIterationIdList();
		DataTable dt = this.getOidAndEid("is null");
		for(Long ii : iterationIdList){
			for(Row row : dt){
				Long noid = turnLong(row.get("oid"));
				boolean f = false;
				for(Row nRow : dTable){
					Long itId = turnLong(nRow.get("iteration_id"));
					Long orgId = turnLong(nRow.get("organization_id"));
					if(ii.longValue()==itId.longValue() && noid.longValue()==orgId.longValue()){
						f = true;
						break;
					}
				}
				if(!f){
					this.organizeIoList(ioList, row, ii);
				}
			}
		}
		
		//先保存二级数据
		ioList = this.saves(ioList);
		//再更新三级数据
		this.updateIeoList(ioList);
	}
	
	/**
	 * 组织迭代数据集合（一级数据）（迭代）
	 * @param ioeList
	 * @param itemRow
	 */
	private void organizeIList(List<IteratorStatistics> iList, Row row){
		
		IteratorStatistics is = new IteratorStatistics();
		is.setEntityState(EntityState.New);
		is.setName(row.getString("name"));
		is.setIterationId(turnLong(row.get("iteration_id")));
		is.setItemTotalCount(turnInt(row.get("ritc")));
		is.setItemEstimationTime(turnInt(row.get("riet")));
		is.setItemRealTime(turnInt(row.get("rirt")));
		is.setFinishedItemTotalCount(turnInt(row.get("rfitc")));
		is.setFinishedItemEstimationTime(turnInt(row.get("rfiet")));
		is.setFinishedItemRealTime(turnInt(row.get("rfirt")));
		is.setUnFinishedItemTotalCount(turnInt(row.get("ruitc")));
		is.setUnFinishedItemEstimationTime(turnInt(row.get("ruiet")));
		is.setSupportTotalCount(turnInt(row.get("rstc")));
		is.setSupportEstimationTime(turnInt(row.get("rset")));
		is.setSupportRealTime(turnInt(row.get("rsrt")));
		is.setFinishedSupportTotalCount(turnInt(row.get("rfstc")));
		is.setFinishedSupportEstimationTime(turnInt(row.get("rfset")));
		is.setFinishedSupportRealTime(turnInt(row.get("rfsrt")));
		is.setUnfinishedSupportTotalCount(turnInt(row.get("rustc")));
		is.setUnfinishedSupportEstimationTime(turnInt(row.get("ruset")));
		
		iList.add(is);
	}
	
	/**
	 * 更新迭代数据集合（二级数据）（迭代--部门）
	 * @param iList
	 */
	private void updateIeList(List<IteratorStatistics> iList){
		
		String table =  MtableManager.getMtable(this.type).getTableName();
		String sql = "";
		for(IteratorStatistics is : iList){
			sql = "update "+table+" set parent_id="+is.getId()+" where employee_id is null and organization_id is not null and iteration_id="+is.getIterationId();
			this.pm.executeNonQuery(sql, null);
		}
	}
	
	/**
	 * 持久化迭代数据集合（一级数据）（迭代）
	 */
	private void saveIListToDB(){
		
		List<IteratorStatistics> iList = new ArrayList<IteratorStatistics>();
		String table =  MtableManager.getMtable(this.type).getTableName();
		String iterationTable =  MtableManager.getMtable(Iteration.class).getTableName();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select ").append(StringManager.NewLine);
		sb.append("ris.iteration_id,").append(StringManager.NewLine);
		sb.append("sum(ris.item_total_count) as ritc,").append(StringManager.NewLine);
		sb.append("sum(ris.item_estimation_time) as riet,").append(StringManager.NewLine);
		sb.append("sum(ris.item_real_time) as rirt,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_item_total_count) as rfitc,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_item_estimation_time) as rfiet,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_item_real_time) as rfirt,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_item_total_count) as ruitc,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_item_estimation_time) as ruiet,").append(StringManager.NewLine);
		sb.append("sum(ris.support_total_count) as rstc,").append(StringManager.NewLine);
		sb.append("sum(ris.support_estimation_time) as rset,").append(StringManager.NewLine);
		sb.append("sum(ris.support_real_time) as rsrt,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_support_total_count) as rfstc,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_support_estimation_time) as rfset,").append(StringManager.NewLine);
		sb.append("sum(ris.finished_support_real_time) as rfsrt,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_support_total_count) as rustc,").append(StringManager.NewLine);
		sb.append("sum(ris.unfinished_support_estimation_time) as ruset,").append(StringManager.NewLine);
		sb.append("ti.name").append(StringManager.NewLine);
		sb.append("from "+table+" ris ").append(StringManager.NewLine);
		sb.append("left join "+iterationTable+" ti on ris.iteration_id=ti.id ").append(StringManager.NewLine);
		sb.append("where ris.employee_id is null and ris.organization_id is not null and ris.iteration_id is not null").append(StringManager.NewLine);
		sb.append("group by ris.iteration_id").append(StringManager.NewLine);
		String sql = sb.toString();
		
		DataTable dTable = this.pm.executeTable(sql, null);
		for(Row row : dTable){
			this.organizeIList(iList, row);
		}
		//先保存一级数据
		iList = this.saves(iList);
		//再更新二级数据
		this.updateIeList(iList);
	}
	
	/**
	 * 获取部门及人员ID
	 * @param filter 只能是is null 或 is not null
	 * @return
	 */
	private DataTable getOidAndEid(String filter){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (").append(StringManager.NewLine);
		sb.append("select ").append(StringManager.NewLine);
		sb.append("po.id as oid, ").append(StringManager.NewLine);
		sb.append("po.name as pname, ").append(StringManager.NewLine);
		sb.append("spoe.employeeId as eid, ").append(StringManager.NewLine);
		sb.append("spe.name as ename ").append(StringManager.NewLine);
		sb.append("from sys_permission_organization spo ").append(StringManager.NewLine);
		sb.append("left join sys_permission_organization po on spo.parent_id=po.id ").append(StringManager.NewLine);
		sb.append("left join sys_permission_organization_employee spoe on spoe.organizationId=spo.id ").append(StringManager.NewLine);
		sb.append("left join sys_permission_employee spe on spoe.employeeId=spe.id ").append(StringManager.NewLine);
		sb.append("where po.parent_id=2  and spe.disabled=0 ").append(StringManager.NewLine);
		sb.append("union ").append(StringManager.NewLine);
		sb.append("select ").append(StringManager.NewLine);
		sb.append("spo.id as oid, ").append(StringManager.NewLine);
		sb.append("spo.name as pname, ").append(StringManager.NewLine);
		sb.append("spoe.employeeId as eid, ").append(StringManager.NewLine);
		sb.append("spe.name as ename ").append(StringManager.NewLine);
		sb.append("from sys_permission_organization spo ").append(StringManager.NewLine);
		sb.append("left join sys_permission_organization_employee spoe on spoe.organizationId=spo.id ").append(StringManager.NewLine);
		sb.append("left join sys_permission_employee spe on spoe.employeeId=spe.id ").append(StringManager.NewLine);
		sb.append("where spo.parent_id=2  and spe.disabled=0) t ").append(StringManager.NewLine);
		sb.append("where t.eid ").append(filter).append(StringManager.NewLine);
		String sql = sb.toString();
		
		return this.pm.executeTable(sql, null);
	}
	
	/**
	 * 获取没有统计数据的部门及人员
	 * @param ioeItemDataTable
	 * @return
	 */
	private Map<String, List<Row>> getNotExsistEmployee(List<Long> iterationIdList, DataTable ioeItemDataTable){
		
		Map<String, List<Row>> notExsistMap = new HashMap<String, List<Row>>();
		
		DataTable notExistEmployee = this.getOidAndEid("is not null");
		for(Long itId : iterationIdList){
			List<Row> notExsistList = new ArrayList<Row>();
			for(Row r : notExistEmployee){
				Long noid = turnLong(r.get("oid"));
				Long neid = turnLong(r.get("eid"));
				boolean f = false;
				for(Row ir : ioeItemDataTable){
					Long ii = turnLong(ir.get("ii"));
					Long oi = turnLong(ir.get("oi"));
					Long ei = turnLong(ir.get("ei"));
					if(itId.longValue()==ii.longValue() && noid.longValue()==oi.longValue() && neid.longValue()==ei.longValue()){
						f = true;
						break;
					}
				}
				if(!f){
					notExsistList.add(r);
				}
			}
			notExsistMap.put(turnString(itId), notExsistList);
		}
		
		return notExsistMap;
	}
	
	/**
	 * 判断是否为null
	 * @param strData
	 * @return boolean
	 */
	public static boolean isNull(Object strData) {
		if (strData == null || String.valueOf(strData).trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 转换为int类型
	 * @param obj
	 * @return int 空返回0
	 */
	public static int turnInt(Object obj) {
		int i = 0;
		if (isNull(obj)) {
			return i;
		}
		try {
			if (obj instanceof Double) {
				Double d = (Double) obj;
				return d.intValue();
			}
			if (obj instanceof Float) {
				Float f = (Float) obj;
				return f.intValue();
			}
			i = Integer.valueOf(obj.toString());
		} catch (Exception e) {
			// 类型转换异常
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 转换为long类型
	 * @param obj
	 * @return long 空返回-1
	 */
	public static long turnLong(Object obj) {
		long l = -1;
		if (isNull(obj)){
			return l;
		}

		try {
			l = Long.valueOf(obj.toString());
		} catch (Exception e) {
			// 类型转换异常
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * 转换为String类型
	 * @param obj
	 * @return String 空返回""
	 */
	public static String turnString(Object obj) {
		if (obj == null) {
			return "";
		}
		String str = String.valueOf(obj).trim();
		return str;
	}
}
