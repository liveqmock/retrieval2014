package com.sxjun.retrieval.controller.index.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sxjun.retrieval.common.DictUtils;
import com.sxjun.retrieval.common.SQLUtil;
import com.sxjun.retrieval.controller.proxy.ServiceProxy;
import com.sxjun.retrieval.controller.service.CommonService;
import com.sxjun.retrieval.pojo.Database;
import com.sxjun.retrieval.pojo.RDatabaseIndex;

import frame.retrieval.engine.RetrievalType;
import frame.retrieval.engine.RetrievalType.RDatabaseType;
import frame.base.core.util.DateTime;
import frame.base.core.util.JdbcUtil;
import frame.retrieval.engine.context.RetrievalApplicationContext;
import frame.retrieval.engine.facade.ICreateIndexAllItem;
import frame.retrieval.engine.index.doc.database.RDatabaseIndexAllItem;

public class DatabaseIndexAllItem1Impl extends DatabaseIndexAllItemCommon implements ICreateIndexAllItem{
	
	private List<RDatabaseIndex> rDatabaseIndexList;
	private CommonService<RDatabaseIndex> commonService = new ServiceProxy<RDatabaseIndex>().getproxy();
	
	public DatabaseIndexAllItem1Impl(){
		rDatabaseIndexList = commonService.getObjs(RDatabaseIndex.class);
	}
	
	@Override
	public List<RDatabaseIndexAllItem> deal(RetrievalApplicationContext retrievalApplicationContext) {
		
		List <RDatabaseIndexAllItem> l = new ArrayList<RDatabaseIndexAllItem>();
		for(RDatabaseIndex rdI:rDatabaseIndexList){
			if("0".endsWith(rdI.getIsError())&&"1".endsWith(rdI.getIsInit())&&"0".endsWith(rdI.getIsOn())){
				rdI.setIsInit("2");
				commonService.put(RDatabaseIndex.class, rdI.getId(), rdI);
				String nowTime = new DateTime().getNowDateTime();
				String sql = getIndexTriggerSql(rdI,nowTime,false);
				l.add(create(retrievalApplicationContext,rdI,sql,nowTime));
			}
		}
		return l;
	}

	
	/**
	 * 删除触发器表
	 */
	@Override
	public void afterDeal(Object databaseIndexAllItem) {
		Map<String,Object> transObj = (Map<String, Object>) ((RDatabaseIndexAllItem)databaseIndexAllItem).getTransObject();
		String nowTime = (String) transObj.get("nowTime");
		//RDatabaseIndex rdI = commonService.get(RDatabaseIndex.class, transObj.get("id"));
		RDatabaseIndex rdI = (RDatabaseIndex) transObj.get("rdI");
		//删除索引
		if("1".endsWith(rdI.getIndexOperatorType())){
			judgeAndDelIndexRecord(rdI,nowTime);
		}
		delAllTrigRecord(rdI,nowTime,true);
		rdI.setIsInit("1");
		commonService.put(RDatabaseIndex.class, rdI.getId(), rdI);
	}

}