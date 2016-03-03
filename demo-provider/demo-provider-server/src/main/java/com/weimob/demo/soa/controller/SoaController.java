package com.weimob.demo.soa.controller;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.weimob.demo.soa.utils.InterfaceParametersUtils;
import com.weimob.demo.soa.utils.SpringBeanUtils;

@Controller
public class SoaController {

	/** http相关参数 **/
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String DEFAULT_CONTENT_TYPE_NAME = "content-typ";
	private static final String DEFAULT_CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

	@RequestMapping(value = "/service/test")
	public void service(HttpServletRequest request, HttpServletResponse response) {
		Object result = null;
		try {

			String serviceName = request.getParameter("serviceName");
			String methodName = request.getParameter("methodName");
			String paramterInput = request.getParameter("paramterInput").trim();

			// 每个接口均存在InvokeParamVo参数，加入到参数列表
			// 1、单个参数{}，构造成[]
			// 2/多参数[]，直接将InvokeParamVo加在第一个参数
			List<String> paramList = new ArrayList<String>();
			Object bean = SpringBeanUtils.getBean(serviceName);
			
			boolean isInvoked = false;
			// 必须用getInterfaces，这样才能取到方法参数的泛型，JSON参数才会争取
			for (Class<?> beanInterface : bean.getClass().getInterfaces()) {
				for (Method method : beanInterface.getMethods()) {
					try {
						if (methodName.equals(method.getName())) {
							Type[] types = method.getGenericParameterTypes();
							List<Object> params = new ArrayList<Object>();
							if (types.length == 1) {
								paramList.add(paramterInput);
							}
							if (types.length > 1) {// 超过2个参数，将JSON参数转为List<String>，后续再逐个转为对应的对象
								List<String> tmepList = JSON.parseArray(paramterInput, String.class);
								paramList.clear();
								paramList.addAll(tmepList);
							}
							if (types.length == paramList.size()) {// 参数数量必须相同
								for (int i = 0; i < types.length; i++) {
									params.add(JSON.parseObject(paramList.get(i), types[i]));
								}
								isInvoked = true;
								result = method.invoke(bean, params.toArray());
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						result = ExceptionUtils.getRootCauseStackTrace(e);
					}
					if (isInvoked) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			handleResponse(response, result);
		}
	}
	
	
	
	@RequestMapping(value = "/load/test")
	public void load(HttpServletRequest request, HttpServletResponse response) {
		Object result = null;
		try {
		String serviceName = request.getParameter("serviceName");
	    String methodName = request.getParameter("methodName");
	    
	    Object bean = SpringBeanUtils.getBean(serviceName);
	    //必须用getInterfaces，这样才能取到方法参数的泛型，JSON参数才会争取
	    for(Class<?> beanInterface :bean.getClass().getInterfaces()){
		    for (Method method : beanInterface.getMethods()) {
		          try{    
		                if(methodName.equals(method.getName())){
		                    result = InterfaceParametersUtils.getInterfaceInputJsonString(beanInterface.getName(),method.getName());
		                    break;
		                }
		          }catch(Exception e){
		              e.printStackTrace();
		          }
		    }
	    }
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		handleResponse(response, result);
	}
	}

	/**
	 * 通用处理过程
	 * 
	 * @param request
	 * @param response
	 * @param beanName
	 */
	@SuppressWarnings("unused")
	private void handle(HttpServletRequest request, HttpServletResponse response) {
		

	}

	/**
	 * 处理输出结果
	 * 
	 * @param response
	 * @param bizResponse
	 */
	private void handleResponse(HttpServletResponse response, Object result) {
		response.setCharacterEncoding(DEFAULT_CHARSET);
		response.setHeader(DEFAULT_CONTENT_TYPE_NAME, DEFAULT_CONTENT_TYPE_VALUE);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(result instanceof String){
				pw.print(result);
			}else{
				String data = JSON.toJSONString(result,true);
				System.out.println(data);
				pw.print(data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.flush();
				pw.close();
		}
	}

}
