package com.ssic.cookbook.manager.menuAlgorithm;

import java.util.List;

import com.ssic.cookbook.manager.dto.CategoryDto;


/**		
* <p>Title: CostingUtil </p>
* <p>Description: 每道菜成本运算</p>
* <p>Copyright (c) 2015 </p>
* <p>Company: 上海天坊信息科技有限公司</p>
* @author：张亚伟	
* @Date：2015年12月31日 上午11:54:22	
* @Version 1.0
* <p>修改人：</p>张亚伟
* <p>修改时间：</p>2015年12月31日 上午11:54:22 
* <p>修改备注：</v> 
*/
public class CostingUtil {

	/**
	 * getCost：计算每道菜所需成本
	 * @param totalPrice 总价
	 * @param costOperator. 运算符
	 * @param categoryList 品类集合
	 * @exception	
	 * @author：张亚伟
	 * @Date：2015年12月31日 下午1:06:00
	 */
	public int getCost(Long totalPrice,String costOperator,List<CategoryDto> categoryList){
		//获取成菜数量
		int dishCon=0;
		for (CategoryDto categoryDto : categoryList) {
			Long categoryCount = categoryDto.getCategoryCount();
			if (categoryCount!=null&&categoryCount>0) {
				dishCon+=categoryCount;
			}
		}
		
		if (dishCon==0||totalPrice<=0) {
			return 0;
		}
		//平均价
		int average=(int) (totalPrice/dishCon);
		
		//根据运算符计算价格浮动
		if (costOperator.equals("=")||costOperator.equals("<")) {
			return average;
		}else if (costOperator.equals(">")) {
			average=(int) (average+(average*0.3));
		}
		return average;
	}
}
