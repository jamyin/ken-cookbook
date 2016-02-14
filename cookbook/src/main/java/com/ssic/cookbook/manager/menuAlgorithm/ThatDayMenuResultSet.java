package com.ssic.cookbook.manager.menuAlgorithm;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.ssic.cookbook.manager.dto.ProductDto;


public class ThatDayMenuResultSet {

	// 当天成菜名称
	@Getter
	@Setter
	private Map<String, ProductDto> productNameMap;

	// 当天配菜菜系
	@Getter
	@Setter
	private Map<String, ProductDto> productStyleMap;

	// 当天配菜形状
	@Getter
	@Setter
	private Map<String, ProductDto> productShapeMap;

	// 当天配菜口味
	@Getter
	@Setter
	private Map<String, ProductDto> productTasteMap;

	// 当天配菜烹饪方式
	@Getter
	@Setter
	private Map<String, ProductDto> productCuisineMap;

	// 当天配菜颜色
	@Getter
	@Setter
	private Map<String, ProductDto> productColorMap;

}
