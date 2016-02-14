package com.ssic.cookbook.manager.dto;

import java.util.Date;

import com.ssic.base.redis.RedisKeyPrefix;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
public class IndependentFixingsDto {

	// primary Key
	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private Date fixingsTime;
	
	@Getter
	@Setter
	private String fixingsTimeVO;
	
	// 配菜开始日期
	@Getter
	@Setter
	private Date fixingsStartTime;
	
	@Getter
	@Setter
	private String startTimeVo;

	// 配菜结束日期
	@Getter
	@Setter
	private Date fixingsEndTime;
	
	@Getter
	@Setter
	private String endTimeVo;

	// 午餐数量
	@Getter
	@Setter
	private Integer lunchCount;

	// 晚餐数量
	@Getter
	@Setter
	private Integer dinnerCount;

	// 所属配菜主表id
	@Getter
	@Setter
	private String fixingsMasterId;

	// 创建时间
	@Getter
	@Setter
	private Date createTime;

	// 更新时间
	@Getter
	@Setter
	private Date lastUpdateTime;

	// 是否有效:1是
	@Getter
	@Setter
	private Integer stat;

}