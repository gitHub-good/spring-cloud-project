
package com.xu.springcloud.shop.common.base.myabtis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 
 * @classDesc: 功能描述:(BaseDao)
 */
public interface BaseDao {

	/**
	 * @methodDesc: 功能描述:(增加持久化对象)
	 */
	@InsertProvider(type = BaseProvider.class, method = "save")
	void save(@Param("object") Object object, @Param("table") String table);

	/**
	 * @methodDesc: 功能描述:(修改持久化对象)
	 */
	@UpdateProvider(type = BaseProvider.class, method = "update")
	void update(@Param("oj") Object oj, @Param("table") String table, @Param("idKey") String idKey);

}
