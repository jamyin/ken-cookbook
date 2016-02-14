/**
 * 
 */
package com.ssic.cookbook.manager.make;

import java.util.List;

import com.ssic.cookbook.manager.dto.DateRange;

/**		
 * <p>Title: IMakePlanStrategy </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author rkzhang	
 * @date 2015年12月28日 下午2:08:06	
 * @version 1.0
 * <p>修改人：rkzhang</p>
 * <p>修改时间：2015年12月28日 下午2:08:06</p>
 * <p>修改备注：</p>
 */
public interface IMakePlanStrategy {

    void setCandidateProductList(List<ProductDto> all);
    
    void setRule(List<IRule> all);
    
    List<DailyPlanResult> makePlan(DateRange dateRange, IntelligentFixingsDto condition, List<Meal> meals);
}

