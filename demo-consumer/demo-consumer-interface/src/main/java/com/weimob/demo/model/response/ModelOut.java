package com.weimob.demo.model.response;

import java.io.Serializable;

/**
 * 出参对象
 * @author wangdongdong 
 * @date 2016年2月3日
 *
 */

public class ModelOut implements Serializable{
	
	private static final long serialVersionUID = 3822097591221203335L;
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ModelOut [name=" + name + "]";
	}

}
