/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**		
 * <p>Title: DateRange </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author rkzhang	
 * @date 2015年12月28日 下午3:05:00	
 * @version 1.0
 * <p>修改人：rkzhang</p>
 * <p>修改时间：2015年12月28日 下午3:05:00</p>
 * <p>修改备注：</p>
 */
@ToString
public class DateRange {
    
    @Getter
    @Setter
    private Date beginDate;

    @Getter
    @Setter
    private Date endDate;
    
}

