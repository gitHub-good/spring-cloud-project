package com.xu.springcloud.shop.common.base.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 徐亮亮
 * @date 2018/10/31 0:20
 * @Description: Date时间工具类
 */
@Slf4j
public class DateUtils {

	private static SimpleDateFormat simpleDateFormat;
	/**
	 * Date类型转为指定格式的String类型
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String DateToString(Date source, String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(source);
	}

	/**
	 * 
	 * unix时间戳转为指定格式的String类型
	 * 
	 * 
	 * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
	 * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String timeStampToString(long source, String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date(source * 1000);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将日期转换为时间戳(unix时间戳,单位秒)
	 * 
	 * @param date
	 * @return
	 */
	public static long dateToTimeStamp(Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp.getTime() / 1000;

	}

	/**
	 * 
	 * 字符串转换为对应日期(可能会报错异常)
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String source, String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(source);
		} catch (ParseException e) {
			log.error("字符串转换日期异常", e);
		}
		return date;
	}

	/**
	 * 获得当前时间对应的指定格式
	 * 
	 * @param pattern
	 * @return
	 */
	public static String currentFormatDate(String pattern) {
		simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());

	}

	/**
	 * 获得当前unix时间戳(单位秒)
	 * 
	 * @return 当前unix时间戳
	 */
	public static long currentTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(获取当前系统时间戳)
	 * @param: @return
	 */
	public static Timestamp getTimestamp() {
          return new Timestamp(new Date().getTime());
	}

}