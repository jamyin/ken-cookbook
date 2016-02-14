package com.ssic.cookbook.manager.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ReturnResultDto implements Serializable{

	@Getter
	@Setter
	private String msg;
	@Getter
	@Setter
	private String errorCode; 
	
	
}
