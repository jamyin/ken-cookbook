/**
 * 
 */
package com.ssic.cookbook.manager.make;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**		
 * <p>Title: ResultSeacher </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author rkzhang	
 * @date 2015年12月28日 下午3:50:36	
 * @version 1.0
 * <p>修改人：rkzhang</p>
 * <p>修改时间：2015年12月28日 下午3:50:36</p>
 * <p>修改备注：</p>
 */
public class ResultSeacher {
      
    @Getter
    @Setter
    private List<DailyPlanResult> results;
    
    public ResultSeacher(List<DailyPlanResult> results) {
   	this.results = results;
    }

    
    //总定价 
    public double totalFixedPrice() {
	//TODO 
	return 0d;
    }
    
    public double totalCost() {
	//TODO
	return 0d;
	
    }
    
    public int count(ResultQuery query) {
	
	return 0;
    }
    
    @ToString
    class ResultQuery {
	
	@Getter
	@Setter
	private Date day;
	
	@Getter
	@Setter
	private String productTasteId;

	@Getter
	@Setter
	private String productColorId;

	@Getter
	@Setter
	private String productCuisineId;

	@Getter
	@Setter
	private String productStyleId;

	@Getter
	@Setter
	private String productShapeId;
	
    }

    
    /**     
     * getResult：一句话描述方法功能
     * @param meal
     * @param curr
     * @return
     * @exception	
     * @author rkzhang
     * @date 2015年12月28日 下午7:11:59	 
     */
    public DailyPlanResult findResult(Meal meal, Date curr) {
	// TODO 添加方法注释
	return null;
    }
}

