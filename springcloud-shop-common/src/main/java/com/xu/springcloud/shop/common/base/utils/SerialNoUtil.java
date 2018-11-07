package com.xu.springcloud.shop.common.base.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 序号工具类
 */
public final class SerialNoUtil {

	private SerialNoUtil() {

	}
	// 数据表编号
	private static Map<String, String> tableSeqenceMap = new HashMap<String, String>() {
		{
			/*
			 * 表ID的编码前缀
			 */
			put("tbl_default_create_sys","SYS-TBL");
			put("tbl_member_user", "XLL-MU");
			put("tbl_user","XLL_US");
			put("tbl_auth_user","AUTH");
		}
	};

	/**
	 * 获取表的主键值
	 * 
	 *            表名
	 * @return 表的主键值
	 */
	public static String getTabPkSn() {

		final Object[] tblNameKeys = tableSeqenceMap.values().toArray();
		final String tblSeq = tblNameKeys[RandomUtils
				.generateRangeNumber(tblNameKeys.length)].toString();
		return tblSeq + getPKSn(10, 5);
	}

	/**
	 * @date 2018/10/31 17:44
	 * @Description: 设置生成ID和ID位数， 默认为32位
	 */
	public static String getTabPkSn(String tableName,Integer number) {
		if(number == null){
			number = 32;
		}
		String tableSeqence = tableSeqenceMap.get(tableName.toLowerCase());
		if (tableSeqence == null) {
			throw new RuntimeException();
		}
		tableSeqence += getPKSn(8, 32);
		return tableSeqence.substring(0, number);
	}
	/**
	 * @date 2018/10/31 17:43
	 * @Description: 默认自动生成ID 32位值
	 */
	public static String getTabPkSn(String tableName) {
		String tableSeqence = tableSeqenceMap.get(tableName.toLowerCase());
		if (tableSeqence == null) {
			throw new RuntimeException();
		}
		return tableSeqence += getPKSn(8, 32);
	}
	public static String getOrderPKSn() {
		return getPKSn(12, 4);
	}

	public static String getPKSn(int subLen, int randomLen) {

		String milliseconds = String.valueOf(new Date().getTime());
		return milliseconds.substring(milliseconds.length() - subLen)
				+ RandomUtils.generateNumberString(randomLen);
	}

	public static void main(String[] args){
		String userId = SerialNoUtil.getTabPkSn("tbl_member_user",16);
		System.out.println(userId);
	}
}
