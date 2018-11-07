package com.xu.springcloud.shop.common.base.utils;

import com.xu.springcloud.shop.common.base.entity.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

/**
 * @author 徐亮亮
 * @date 2018/10/30 22:28
 * @Description: 功能描述:(Java反射工具类)
 */
@Slf4j
public class ReflectionUtils {
	/*拼接当前类的属性或属性与属性值*/
	private static String strSplice = null;
	/*拼接当前父类的属性或属性与属性值*/
	private static String strSuperSplice = null;
	private static StringBuffer appendStrBuf;
	/**
	 * 
	 * @methodDesc: 功能描述:(获取类的属性,拼接成字符串)
	 * 					获取添加sql的属性名称
	 */
	public static String getInsertAllFieldsName(Object oj) {
		try{
			if (oj == null) {
				return null;
			}
			Class cl = oj.getClass();
			// 获取当前所有的属性
			Field[] declaredFields = cl.getDeclaredFields();
			strSplice = appendByInsertAllFieldsName(declaredFields);
			Class superclass = cl.getSuperclass();
			Field[] superField = superclass.getDeclaredFields();
			strSuperSplice = appendByInsertAllFieldsName(superField);

		}catch (Exception e){
			log.error("### getInsertAllFieldsValue() ERROR:", e);
		}
		return strSplice+","+strSuperSplice;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取类的属性,拼接成字符串的值)
	 * 					获取添加sql的属性值
	 */
	public static String getInsertAllFieldsValue(Object oj) {
		try{
			if (oj == null) {
				return null;
			}
			Class cl = oj.getClass();
			// 获取所有的属性
			Field[] declaredFields = cl.getDeclaredFields();
			strSplice = appendByInsertAllFieldsValue(declaredFields, oj);
			Class superclass = cl.getSuperclass();
			Field[] superField = superclass.getDeclaredFields();
			strSuperSplice = appendByInsertAllFieldsValue(superField, oj);
		}catch (Exception e){
			log.error("### getInsertAllFieldsValue() ERROR:", e);
		}
//		return contentValue(strSplice, strSuperSplice);
		return strSplice+","+strSuperSplice;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取类的属性,拼接成字符串值)
	 * 					获取修改sql的属性名称
	 */
	public static String getUpdateAllFields(Object oj) {
		try{
			if (oj == null) {
				return null;
			}
			Class cl = oj.getClass();
			// 获取所有的属性
			Field[] declaredFields = cl.getDeclaredFields();
			strSplice = appendByUpdateFieldAndValue(declaredFields, oj);
			Field[] supDeclaredFields = cl.getSuperclass().getDeclaredFields();
			strSuperSplice = appendByUpdateFieldAndValue(supDeclaredFields, oj);
		}catch (Exception e){
			log.error("### getUpdateAllFields() ERROR:", e);
		}
		return contentValue(strSplice, strSuperSplice);
	}
	/**
	 * @author 徐亮亮
	 * @date 2018/10/31 2:19
	 * @Description: 该方法为判断返回的拼接内容值
	 */
	private static String contentValue(String strSplice, String strSuperSplice){
//		if(strSplice.isEmpty() && strSuperSplice.isEmpty()) return null;
		if(!strSplice.isEmpty() && strSuperSplice.isEmpty()){
//			strSplice = strSplice.substring(0, (strSplice.length()-1));
			return strSplice;
		}
		if(strSplice.isEmpty() && !strSuperSplice.isEmpty()){
//			strSuperSplice = strSuperSplice.substring(0, (strSuperSplice.length()-1));
			return strSuperSplice;
		}
		if(!strSplice.isEmpty() && !strSuperSplice.isEmpty()) {
			strSplice = strSplice.substring(0, (strSplice.length()-1));
			strSuperSplice = strSuperSplice.substring(0, (strSuperSplice.length()-1));
			return strSplice + "," + strSuperSplice;
		}
		return null;
	}
	/**
	 * @date 2018/10/30 23:16
	 * @Description: 获取更新sql的属性值 去除Id则说明Id不是由字符串构成
	 */
	private static String appendByUpdateFieldAndValue(Field[] declaredFields, Object oj) {
		appendStrBuf = new StringBuffer();
		int index = declaredFields.length ;//去除Id则减少一位
		try {
			for (int i = 0; i < declaredFields.length; i++) {
				String name = spliceName(declaredFields[i].getName());
				if (name.equals("id")) {
					continue;
				}
				declaredFields[i].setAccessible(true);
				Object value = declaredFields[i].get(oj);
				if (value == null) {
						--index;
						continue;
				}else {
					appendStrBuf.append(name + "=" + "'" + value + "'");
					if ((i < index)) {
						appendStrBuf.append(",");
					}
				}
			}
		} catch (Exception e) {
			log.error("### appendByUpdateFieldAndValue() ERROR:", e);
		}
		return appendStrBuf.toString();
	}

	/**
	 * @date 2018/10/30 22:34
	 * @Description: 拼接实体类所在属性字段值
	 */
	private static String appendByInsertAllFieldsValue(Field[] declaredFields, Object oj) {
		appendStrBuf = new StringBuffer();
		try {
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				field.setAccessible(true);
				Object value = field.get(oj);
				if (value != null) {
					appendStrBuf.append("'"+value+"'");
				} else {
					appendStrBuf.append(value);
				}
				if (i < declaredFields.length - 1) {
					appendStrBuf.append(",");
				}
			}
		} catch (Exception e) {
			log.error("###ERROR:appendByInsertAllFieldsValue 方法出现异常:", e);
		}
		return appendStrBuf.toString();
	}

	/**
	 * @date 2018/10/30 22:35
	 * @Description: 拼接实体类所在属性字段名称
	 */
	private static String appendByInsertAllFieldsName(Field[] declaredFields) {
		appendStrBuf = new StringBuffer();
		try{
			// 获取到子类的
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				String name = spliceName(field.getName());
				appendStrBuf.append(name);
				if (i < declaredFields.length - 1) {
					appendStrBuf.append(",");
				}
			}
		}catch (Exception e){
			log.error("###ERROR:appendByInsertAllFieldsName 方法出现异常:", e);
		}
		return appendStrBuf.toString();
	}

	/**
	 * @date 2018/10/31 17:05
	 * @Description: 设置实体类属性的驼峰命名与数据库表字段对应映射
	 */
	private static String spliceName(String name){
		String splice = "";
		char[] charArray = name.toCharArray();
		for (int k = 0; k < charArray.length; k++) {
			if(charArray[k] >= 'A' && charArray[k] <= 'Z'){
				splice += "_"+charArray[k];
			} else{
				splice += charArray[k];
			}
		}
		return splice.toLowerCase();
	}

	/**
	 * @author 徐亮亮
	 * @date 2018/10/31 0:20
	 * @Description: 测试
	 */
	public static void main(String[] agrs){

//		String str = "aaaaAbbbb";
//		System.out.println(spliceName(str));
		TestEntity testEntity = new TestEntity();
		testEntity.setUserName("张三");
		testEntity.setPassword("123456");
//		testEntity.setPhone("112244");
		testEntity.setEmail("112133546");
		String valueFiled  = ReflectionUtils.getInsertAllFieldsValue(testEntity);
		String field = ReflectionUtils.getInsertAllFieldsName(testEntity);
		String update = ReflectionUtils.getUpdateAllFields(testEntity);
		String updateSql = new SQL() {
			{
				UPDATE("tbl_user");
				SET(ReflectionUtils.getUpdateAllFields(testEntity));
				WHERE("id="+110 + "");
			}
		}.toString();
		String insertSql = new SQL(){
			{
				INSERT_INTO("tbl_user");
				VALUES(ReflectionUtils.getInsertAllFieldsName(testEntity), ReflectionUtils.getInsertAllFieldsValue(testEntity));
			}
		}.toString();
		System.out.println(updateSql);
		System.out.println(insertSql);
	}
}
