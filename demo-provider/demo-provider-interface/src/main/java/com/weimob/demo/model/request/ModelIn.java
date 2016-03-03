package com.weimob.demo.model.request;

import java.io.Serializable;

/**
 * 入参对象
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */

public class ModelIn implements Serializable{
	
	private static final long serialVersionUID = 7898803584319848791L;
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "ModelIn [name=" + name + "]";
	}
	
}
