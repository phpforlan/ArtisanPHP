package com.weimob.demo.facade;

import com.weimob.demo.model.request.ModelIn;
import com.weimob.demo.model.response.ModelOut;
import com.weimob.demo.service.impl.DemoServiceImpl;


/**
 * 业务及异常归集层
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */
public class DemoFacadeService {
	
	private DemoServiceImpl demoServiceImpl;
	
	public ModelOut demoService(ModelIn modelIn) throws Exception {
		return demoServiceImpl.demoService(modelIn);
	}

	/*   get and set  */
	public void setDemoServiceImpl(DemoServiceImpl demoServiceImpl) {
		this.demoServiceImpl = demoServiceImpl;
	}
	
}
