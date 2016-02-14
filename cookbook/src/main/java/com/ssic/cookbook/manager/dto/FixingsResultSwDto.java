package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class FixingsResultSwDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Map<String,List<String>> mMap;
	@Getter
	@Setter
	private Map<String,List<String>> aMap;
	@Getter
	@Setter
	private Map<String, NutritionsDto> map; //营养
	@Getter
	@Setter
	private Map<String, NutritionsDto> map2; //原料
	@Getter
	@Setter
	private int totalsingleCost;
	@Getter
	@Setter
	private int totaleachPricing;
	@Getter
	@Setter
	private float singleCost;//单份成本
	@Getter
	@Setter
	private float eachPricing;//单份定价
}
