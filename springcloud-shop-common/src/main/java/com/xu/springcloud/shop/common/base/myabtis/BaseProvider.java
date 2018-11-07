
package com.xu.springcloud.shop.common.base.myabtis;

import com.xu.springcloud.shop.common.base.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author 徐亮亮
 * @date 2018/10/30 22:18
 * @Description: 通用的添加和修改提供方法封装
 */
@Slf4j
public class BaseProvider {

	public String save(Map<String, Object> para) {
		final Object oj = para.get("object");
		final String table = (String) para.get("table");
		String sql = new SQL() {
			{
				INSERT_INTO(table);
				String columns = ReflectionUtils.getInsertAllFieldsName(oj);
				String values = ReflectionUtils.getInsertAllFieldsValue(oj);
				VALUES(columns, values);
			}
		}.toString();
		log.info("使用封装SQL进行添加数据 SQL语句：", sql);
		return sql;
	}

	public String update(Map<String, Object> para) {
		final Object oj = para.get("oj");
		final String table = (String) para.get("table");
		final String idKey = (String) para.get("idKey");
		String sql = new SQL() {
			{
				UPDATE(table);
				SET(ReflectionUtils.getUpdateAllFields(oj));
				WHERE("id="+idKey + "");
			}
		}.toString();
		log.info("使用封装SQL进行修改数据 SQL语句：", sql);
		return sql;
	}

}
