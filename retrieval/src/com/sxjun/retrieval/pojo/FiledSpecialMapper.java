/**
 *  code generation
 */
package com.sxjun.retrieval.pojo;

import com.sxjun.system.pojo.BasePojo;

/**
 * 特殊字段映射Entity
 * @author sxjun
 * @version 2014-01-14
 */
public class FiledSpecialMapper extends BasePojo{
	/**
	 * 数据库字段
	 */
	private String sqlField; 
	/**
	 * 特殊处理类型
	 */
	private String SpecialType;
	/**
	 * 获取数据库字段
	 * @return
	 */
	public String getSqlField() {
		return sqlField;
	}
	/**
	 * 设置数据库字段
	 */
	public void setSqlField(String sqlField) {
		this.sqlField = sqlField;
	}
	/**
	 * 获取特殊处理类型
	 * @return
	 */
	public String getSpecialType() {
		return SpecialType;
	}
	/**
	 * 设置特殊处理类型
	 */
	public void setSpecialType(String specialType) {
		SpecialType = specialType;
	} 
	
}


