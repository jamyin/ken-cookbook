/**
 * 
 */
package com.ssic.cookbook.manager.service;

import com.ssic.cookbook.manager.dto.CategoryClassProductDto;

/**
 * @author wk.s
 *
 */
public interface ICCProductService {

	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(CategoryClassProductDto dto);
	
	/**
	 * <b>更新</b>
	 * <br>以id为条件，其它fields为参数
	 * @param dto
	 * @return
	 */
	public boolean updateById(CategoryClassProductDto dto);
	
	/**
	 * 逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(CategoryClassProductDto dto);
}
