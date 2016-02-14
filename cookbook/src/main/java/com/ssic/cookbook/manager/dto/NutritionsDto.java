package com.ssic.cookbook.manager.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class NutritionsDto implements Serializable{
		private static final long serialVersionUID = 1L;
		@Getter
	    @Setter
	    private String name;
	    @Getter
	    @Setter
	    private Integer content;  //含量
	    @Getter
	    @Setter
	    private String unit;  //单位
	    @Getter
	    @Setter
	    private Integer mweight;
	
}
