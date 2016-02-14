/**
 * 
 */
package com.ssic.cookbook.manager.service;

import com.ssic.cookbook.manager.dto.CategoryClassDto;


/**
 * @author wk.s
 *
 */
public interface ICategoryClassService {

	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(CategoryClassDto dto);
	
	/**
	 * <b>根据id更新</b>
	 * <br>以id为条件，其它fields为参数
	 * @param dto
	 * @return
	 */
	public boolean updateById(CategoryClassDto dto);
	
	/**
	 * 逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(CategoryClassDto dto);
}
