package com.weimob.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.weimob.demo.model.request.ModelIn;
import com.weimob.demo.model.response.ModelOut;


/**
 * service实现类
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */

public class DemoServiceImpl {
	
	//private static Logger logger = Logger.getLogger(DemoServiceImpl.class);
	
	public ModelOut demoService(ModelIn modelIn) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:sss");
		ModelOut modelOut = new ModelOut();
		modelOut.setName("this is provider servere, time:" + sdf.format(new Date()));
		System.out.println("################the server rpc is ok!################");
		return modelOut;
	}
}
