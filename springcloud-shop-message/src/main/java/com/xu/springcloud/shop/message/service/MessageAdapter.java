
package com.xu.springcloud.shop.message.service;

import com.alibaba.fastjson.JSONObject;
import com.xu.springcloud.shop.common.base.entity.MailBase;

/**
 * @author 徐亮亮
 * @date 2018/10/31 23:29
 * @Description: 适配器转发所有的消息内容
 */
public interface MessageAdapter {

	/**
	 * @date 2018/11/1 0:48
	 * @Description: 点对点分发消息
	 */
	void distributeOneToOneMessage(JSONObject jsonObject);

	/**
	 * @date 2018/11/1 1:04
	 * @Description: 发布订阅模式分发消息
	 */
	void distributeTopic(MailBase mailBase);

}
