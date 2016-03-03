package com.weimob.demo.consumer.service.impl;

import com.weimob.demo.consumer.service.TestService;
import com.weimob.demo.model.ModelErrorVo;
import com.weimob.demo.model.request.ModelIn;
import com.weimob.demo.model.response.ModelOut;
import com.weimob.demo.service.DemoService;
import com.weimob.soa.common.response.SoaResponse;


/**
 * 测试接口实现类
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */

public class TestServiceImpl implements TestService{
	
	//private static Logger logger = Logger.getLogger(TestServiceImpl.class);
	private DemoService demoService;

	@Override
	public SoaResponse<ModelOut, ModelErrorVo> testMethod(ModelIn modelIn) {
		System.out.println("#############################client begin###############");
		SoaResponse<ModelOut, ModelErrorVo> response = demoService.demoService(modelIn);
		System.out.println("#############################client end#################");
		return response;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}
	
	
}
