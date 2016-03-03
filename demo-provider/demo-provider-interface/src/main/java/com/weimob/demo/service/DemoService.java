package com.weimob.demo.service;

import com.weimob.demo.model.ModelErrorVo;
import com.weimob.demo.model.request.ModelIn;
import com.weimob.demo.model.response.ModelOut;
import com.weimob.soa.common.response.SoaResponse;

/**
 * Service接口层
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */
public interface DemoService {
	
	/**
	 * 测试接口
	 * @param modelIn
	 * @return
	 */
	public SoaResponse<ModelOut, ModelErrorVo> demoService (ModelIn modelIn);

}
