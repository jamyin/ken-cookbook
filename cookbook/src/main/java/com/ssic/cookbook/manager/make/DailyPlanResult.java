/**
 * 
 */
package com.ssic.cookbook.manager.make;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**		
 * <p>Title: PlanResult </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author rkzhang	
 * @date 2015年12月28日 下午2:23:28	
 * @version 1.0
 * <p>修改人：rkzhang</p>
 * <p>修改时间：2015年12月28日 下午2:23:28</p>
 * <p>修改备注：</p>
 */
@ToString
public class DailyPlanResult {

    @Getter
    @Setter
    private Date planDay;
    
     /**   
     * Map<早中晚餐类型, Map<菜品类型（大荤，小荤）, List<ProductDto>>>      
     */   
    @Getter
    @Setter
    private Map<Meal, Map<String, List<ProductDto>>> planResults = new HashMap<Meal, Map<String,List<ProductDto>>>();

    
    /**     
     * addProduct：增加菜谱
     * @param meal
     * @param product
     * @exception	
     * @author rkzhang
     * @date 2015年12月28日 下午7:47:00	 
     */
    public void addProduct(Meal meal, ProductDto product) {
	Map<String, List<ProductDto>> mealMap = planResults.get(meal);
	
    }
    
}

