package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * 智能配菜颜色DTO
 * @author YIn
 * @time:2016年1月6日 下午5:11:02
 * @ClassName: ColorDto
 * @Description: TODO
 * @
 */
public class ColorDto {
	
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
    private String colorId;
	
	@Getter
	@Setter
    private String intelligentFixingsId;
	
	@Getter
	@Setter
    private Date createTime;
	
	@Getter
	@Setter
    private Date lastUpdateTime;
	
	@Getter
	@Setter
    private Integer stat;
}