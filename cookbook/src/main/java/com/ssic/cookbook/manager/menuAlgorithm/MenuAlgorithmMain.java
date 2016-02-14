package com.ssic.cookbook.manager.menuAlgorithm;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;

public class MenuAlgorithmMain {
	
	
	
	
	private void getMenu(String id){
		
		//获取智能配菜配置信息
		IntelligentFixingsDto fixingsDto=getFixingsDto();
		
		
		
		
		
		
	}
	

	private IntelligentFixingsDto getFixingsDto(){
		IntelligentFixingsDto fixingsDto=new IntelligentFixingsDto();
		
		//primary Key
		fixingsDto.setId("1");
		
		//套餐名称
		fixingsDto.setSetMealName("成人套餐");
		
		//所属配菜主表id
		fixingsDto.setFixingsMasterId("1");
		
		//所属配菜名称
		fixingsDto.setFixingsMasterName("成人套餐");
		
		// 午餐数量
		fixingsDto.setLunchCount(100);
		
		
		// 晚餐数量
		fixingsDto.setDinnerCount(100);
		
		// 所属口味id
		fixingsDto.setProductTasteId("1");
		
		// 所属口味名称
		fixingsDto.setProductTasteName("甜");
		
		//所属颜色id
		fixingsDto.setProductColorId("1");
		
		//所属颜色名称
		fixingsDto.setProductColorName("红");
		
		// 所属烹饪方式id
		fixingsDto.setProductCuisineId("1");
		
		// 所属烹饪方式名称
		fixingsDto.setProductCuisineName("炸");
		
		// 所属菜系id
		fixingsDto.setProductStyleId("1");
		
		// 所属菜系名称
		fixingsDto.setProductStyleName("粤菜");
		
		// 所属形状id
		fixingsDto.setProductShapeId("1");
		
		// 所属形状名称
		fixingsDto.setProductShapeName("长");
		
		// 总成本运算符
		fixingsDto.setTotalCostOperator(">");
		
		// 总成本
		fixingsDto.setTotalCost(100L);
		
		// 总定价运算符
		fixingsDto.setTotalFixedPriceOperator(">");
		
		//总定价
		fixingsDto.setTotalFixedPrice(100L);
		
		// 是否敏感食材
		fixingsDto.setIsSensitiveIngredients(1);
		
		// 配菜开始日期
		fixingsDto.setFixingsStartTime(new Date());
		
		//配菜结束日期
		fixingsDto.setFixingsEndTime(new Date());
		

		
		
		
		fixingsDto.setCreateTime(new Date());
		
		return fixingsDto;
		
		
	}
}
