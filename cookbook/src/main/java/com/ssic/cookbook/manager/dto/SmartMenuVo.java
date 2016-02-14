/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import java.util.List;

/**
 * @author wk.s
 * 
 * 智能配菜
 */
public class SmartMenuVo {

	private Integer status; // 200表示成功；400表示失败
	private String msg;
	private List<ProductVo> product;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ProductVo> getProduct() {
		return product;
	}
	public void setProduct(List<ProductVo> product) {
		this.product = product;
	}
	
}
