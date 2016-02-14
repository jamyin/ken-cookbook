package com.ssic.cookbook.manager.controller;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class FixingsResult {
    
	//primary Key
	@Getter
	@Setter
    private String id;

    //配菜id
	@Getter
	@Setter
    private String fixingsId;

    //配菜方式：1:自主,2:智能,3:指定套餐
	@Getter
	@Setter
    private Integer fixingsType;

    //创建时间
	@Getter
	@Setter
    private Date createTime;

    //更新时间
	@Getter
	@Setter
    private Date lastUpdateTime;

    //是否有效:1是
	@Getter
	@Setter
    private Integer stat;

    
}