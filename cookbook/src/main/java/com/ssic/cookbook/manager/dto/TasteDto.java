package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * 智能配菜口味DTO
 * @author YIn
 * @time:2016年1月6日 下午5:11:02
 * @ClassName: TasteDto
 * @Description: TODO
 * @
 */
public class TasteDto {
	
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
    private String tasteId;
	
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