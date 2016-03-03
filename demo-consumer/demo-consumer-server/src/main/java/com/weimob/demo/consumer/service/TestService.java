package com.weimob.demo.consumer.service;

import com.weimob.demo.model.ModelErrorVo;
import com.weimob.demo.model.request.ModelIn;
import com.weimob.demo.model.response.ModelOut;
import com.weimob.soa.common.response.SoaResponse;

/**
 * 测试接口
 * @author wangdongdong 
 * @date 2016年2月16日
 *
 */

public interface TestService {
	
	/**
	 * 测试方法1
	 * @param modelIn
	 * @return
	 */
	public SoaResponse<ModelOut, ModelErrorVo> testMethod(ModelIn modelIn);

}
