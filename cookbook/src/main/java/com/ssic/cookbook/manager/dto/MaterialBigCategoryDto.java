package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class MaterialBigCategoryDto implements Serializable{

	@Getter
	@Setter
	private String id;
	@Getter
	@Setter
    private String name;
	@Getter
	@Setter
    private Date createTime;
	@Getter
	@Setter
    private Date lastUpdateTime;
	@Getter
	@Setter
    private Integer stat;
	@Getter
	@Setter
	private Integer limitStar;
	@Getter
	@Setter
	private Integer limitEnd;

}
