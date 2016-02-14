/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.CategoryClassDto;
import com.ssic.cookbook.manager.dto.CategoryClassProductDto;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.FixingsResultTypeDto;
import com.ssic.cookbook.manager.dto.FixingsResultTypeProductDto;
import com.ssic.cookbook.manager.dto.FixingsVO;
import com.ssic.cookbook.manager.dto.IndependentFixingsDto;

/**
 * @author wk.s
 *
 */
public interface DIYBookService {

	/**
	 * 保存自定义菜单相关数据
	 * @param independent
	 * @param category
	 * @param product
	 * @return
	 */
	public String add(IndependentFixingsDto independent, CategoryClassDto category, CategoryClassProductDto product, FixingsVO temp);
	
	public List<PageData> QueryFixingsResult(PageData pd) throws Exception;
}
