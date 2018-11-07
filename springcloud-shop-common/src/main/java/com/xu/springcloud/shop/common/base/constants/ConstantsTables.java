
package com.xu.springcloud.shop.common.base.constants;

import lombok.Getter;

/**
 * @author 徐亮亮
 * @date 2018/10/30 20:46
 * @Description: 常量表名枚举类
 */
@Getter
public enum  ConstantsTables {
	/*会员表*/
	TABLE_MB_USER("tbl_member_user"),

	TABLE_USER("tbl_user");

	private String tableName;
	ConstantsTables(String tableName){
		this.tableName = tableName;
	}
}
