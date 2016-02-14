package com.ssic.cookbook.manager.util;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dto.LimitPageDto;

/**
 * <p>Title: PageUtil </p>
 * <p>Description: 分页工具类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 张亚伟	
 * @date 2015年12月17日 下午5:25:22	
 * @version 1.0
 * <p>修改人：张亚伟</p>
 * <p>修改时间：2015年12月17日 下午5:25:22</p>
 * <p>修改备注：</p>
 */
public class PageUtil {
	private static final PageFunction pageFunction = new PageFunction();
	
	public static void page(Page page,Integer count,Integer dataCount){
		page.setTotalResult(count);
		
	}
}

