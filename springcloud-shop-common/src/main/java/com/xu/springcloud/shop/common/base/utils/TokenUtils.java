
package com.xu.springcloud.shop.common.base.utils;

import java.util.UUID;
/**
 * @author 徐亮亮
 * @date 2018/11/2 16:48
 * @Description: token生成类
 */
public class TokenUtils {

	/**
	 * @date 2018/11/2 16:47
	 * @Description: 使用UUID生成Token
	 */
	public static String getToken(){
		return UUID.randomUUID().toString();
	}

}
