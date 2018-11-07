package com.xu.springcloud.shop.common.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Slf4j
@Service(value = "base_Redis")
public class BaseStringRedisService {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * @date 2018/11/2 17:06
	 * @Description: 往redis添加信息
	 */
	public void setString(String key, String value) {
		set(key, value, null);
	}

	public void setString(String key, String value, Long timeOut) {
		try{
			set(key, value, timeOut);
		}catch (Exception e){
			log.error(" 出现错误！！！ BaseStringRedisService.class setString()方法：",e);
		}
	}

	private void set(String key, Object value, Long timeOut) {
		if (value != null) {
			if (value instanceof String) {
				String setValue = (String) value;
				stringRedisTemplate.opsForValue().set(key, setValue);
			}
			// 封装其他类型
			// 设置有效期
			if (timeOut != null)
				stringRedisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
		}

	}

	/**
	 * @date 2018/11/2 17:06
	 * @Description: 使用key 查找redis信息
	 */
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * @date 2018/11/2 17:06
	 * @Description: 使用key 删除redis信息
	 */
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

}
