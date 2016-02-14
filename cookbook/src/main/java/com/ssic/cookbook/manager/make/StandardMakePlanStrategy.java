/**
 * 
 */
package com.ssic.cookbook.manager.make;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ssic.cookbook.manager.dto.DateRange;
import com.ssic.util.DateUtils;

/**		
 * <p>Title: StandardMakePlanStrategy </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author rkzhang	
 * @date 2015年12月28日 下午3:26:11	
 * @version 1.0
 * <p>修改人：rkzhang</p>
 * <p>修改时间：2015年12月28日 下午3:26:11</p>
 * <p>修改备注：</p>
 */
public class StandardMakePlanStrategy implements IMakePlanStrategy {
    
     /**   
     * candidateProductList: 所有候选的菜品
     */   
    private List<ProductDto> candidateProductList;
    
    /**
     * 规则组
     */
    private List<IRule> rules;

    public StandardMakePlanStrategy() {
	// TODO Auto-generated constructor stub
    }


    public StandardMakePlanStrategy(List<ProductDto> candidateProductList, List<IRule> rules) {
	this.candidateProductList = candidateProductList;
	this.rules = rules;
    }


    public void setCandidateProductList(List<ProductDto> all) {
	this.candidateProductList = all;
    }


    public void setRule(List<IRule> all) {
	this.rules = all;
    }

    /** 
    * 
    * 
    */
    public List<DailyPlanResult> makePlan(DateRange dateRange, IntelligentFixingsDto condition, List<Meal> meals) {
	List<DailyPlanResult> resuts = new ArrayList<DailyPlanResult>();
	ResultSeacher seacher = new ResultSeacher(resuts);
	CandidateProductSeacher candidateProductSearcher = new CandidateProductSeacher(candidateProductList);
	//循环编排每一天记录
	for(Date curr = dateRange.getBeginDate() ; curr.before(dateRange.getEndDate()); curr = DateUtils.addDays(curr, 1)) {
	    for(Meal meal : meals) {
		selectProduct(candidateProductSearcher, seacher, curr, meal, condition);
	    }
	}
	
	return null;
    }


    private void selectProduct(CandidateProductSeacher candidateProductSearcher, ResultSeacher seacher, Date curr, Meal meal, IntelligentFixingsDto condition) {
	ProductDto product = candidateProductSearcher.getRedomProcut(candidateProductList);
	int max = candidateProductList.size() * 2;
	int count = 0;
	while(count < max) {	
		for(IRule rule : rules) {
		     //匹配规则，如果规则匹配成功
		     if(rule.check(seacher, product, meal, curr, condition)) {
			 DailyPlanResult result = seacher.findResult(meal, curr);
			 if(result == null) {
			     result = populateResult(product, meal, curr);
			 } else { 
			     result.addProduct(meal, product);
			 }
			 
			 seacher.getResults().add(result);
			 return;
		     }
		}
	    count++;
	}
    }


    
    

    /**     
     * populateResult：一句话描述方法功能
     * @param product
     * @param meal
     * @param curr
     * @return
     * @exception	
     * @author rkzhang
     * @date 2015年12月28日 下午7:27:51	 
     */
    private DailyPlanResult populateResult(ProductDto product, Meal meal,
	    Date curr) {
	// TODO 添加方法注释
	return null;
    }

}

