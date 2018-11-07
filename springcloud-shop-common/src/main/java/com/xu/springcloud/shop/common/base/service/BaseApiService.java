package com.xu.springcloud.shop.common.base.service;

import com.xu.springcloud.shop.common.base.constants.BaseConstants;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐亮亮
 * @date 2018/10/31 13:02
 * @Description: 操作状态通用基本信息服务类
 */
@Service(value = "baseApiService")
public class BaseApiService {

	/**
	 * @date 2018/10/31 13:06
	 * @Description: 查询操作响应的状态信息
	 */
	public Map<String, Object> setResultData(Object data) {
		return setResult(BaseConstants.HTTP_200_CODE.getConstantValue(), BaseConstants.HTTP_200_CODE.getConstantName(), data);
	}
	/**
	 * @date 2018/10/31 14:36
	 * @Description: 自定义查询操作响应的状态描述信息
	 */
	public Map<String, Object> setResultData(Object data, String msg) {
		return setResult(BaseConstants.HTTP_200_CODE.getConstantValue(), msg, data);
	}
	/**
	 * @date 2018/10/31 13:07
	 * @Description: 后台出现错误时响应的状态信息
	 */
	public Map<String, Object> setResultError() {
		return setResult(BaseConstants.HTTP_500_CODE.getConstantValue(), BaseConstants.HTTP_500_CODE.getConstantName(), null);
	}
	/**
	 * @date 2018/10/31 13:07
	 * @Description: 自定义后台错误描述信息
	 */
	public Map<String, Object> setResultError(String msg) {
		return setResult(BaseConstants.HTTP_500_CODE.getConstantValue(), msg, null);
	}

	/**
	 * @date 2018/10/31 14:22
	 * @Description: 操作成功返回结果数据信息
	 */
	public Map<String, Object> setResultSuccessData(Object data) {
		return setResult(BaseConstants.HTTP_200_CODE.getConstantValue(), BaseConstants.HTTP_SUCCESS.getConstantName(), data);
	}
	/**
	 * @date 2018/10/31 14:35
	 * @Description: 自定义成功返回结果数据描述信息
	 */
	public Map<String, Object> setResultSuccessData(Object data, String msg) {
		return setResult(BaseConstants.HTTP_200_CODE.getConstantValue(), msg, data);
	}
	/**
	 * @date 2018/10/31 14:23
	 * @Description:  添加或更新操作响应的状态信息
	 */
	public Map<String, Object> setResultSuccess() {
		return setResult(BaseConstants.HTTP_200_CODE.getConstantValue(), BaseConstants.HTTP_SUCCESS.getConstantName(), null);
	}
	/**
	 * @date 2018/10/31 14:28
	 * @Description: 自定义添加或更新成功描述信息
	 */
	public Map<String, Object> setResultSuccess(String msg) {
		return setResult(BaseConstants.HTTP_200_CODE.getConstantValue(), msg, null);
	}

	/**
	 * @date 2018/10/31 14:30
	 * @Description: 返回属性字段错误信息且描述
	 */
	public Map<String, Object> setResultParameterError() {
		return setResult(BaseConstants.HTTP_400_CODE.getConstantValue(),BaseConstants.HTTP_FAIL.getConstantName() , null);
	}
	/**
	 * @date 2018/10/31 14:27
	 * @Description: 自定义属性字段错误描述信息
	 */
	public Map<String, Object> setResultParameterError(String msg) {
		return setResult(BaseConstants.HTTP_400_CODE.getConstantValue(), msg, null);
	}

	/**
	 * @date 2018/10/31 14:19
	 * @Description: 自定义消息方法
	 */
	public Map<String, Object> setResult(Integer code, String msg, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseConstants.HTTP_CODE.getConstantName(), code);
		result.put(BaseConstants.HTTP_MESSAGE.getConstantName(), msg);
		if (data != null)
			result.put(BaseConstants.HTTP_DATA.getConstantName(), data);
		return result;
	}

}
