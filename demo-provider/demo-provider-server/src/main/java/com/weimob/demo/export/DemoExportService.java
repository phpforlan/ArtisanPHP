package com.weimob.demo.export;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.weimob.demo.facade.DemoFacadeService;
import com.weimob.demo.model.ModelErrorVo;
import com.weimob.demo.model.request.ModelIn;
import com.weimob.demo.model.response.ModelOut;
import com.weimob.demo.service.DemoService;
import com.weimob.soa.common.response.SoaResponse;

/**
 * 响应数据整理层service
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */

public class DemoExportService implements DemoService{

	private static Logger logger = Logger.getLogger(DemoExportService.class);
	private DemoFacadeService demoFacadeService;
	
	@Override
	public SoaResponse<ModelOut, ModelErrorVo> demoService(ModelIn modelIn) {
		SoaResponse<ModelOut, ModelErrorVo> response = new SoaResponse<ModelOut, ModelErrorVo>();
		try {
			ModelOut ModelOut = demoFacadeService.demoService(modelIn);
			response.setResponseVo(ModelOut);
		} catch (SQLException e) {
			logger.error("demoService:", e);
			response.setReturnCode("000002");
			response.setReturnMsg(e.getMessage());
		} catch (Exception e) {
			logger.error("demoService:", e);
			e.printStackTrace();
			response.setReturnCode("000001");
			response.setReturnMsg(e.getMessage());
		}
		return response;
	}

	
	/*   get and set  */
	public void setDemoFacadeService(DemoFacadeService demoFacadeService) {
		this.demoFacadeService = demoFacadeService;
	}


}
