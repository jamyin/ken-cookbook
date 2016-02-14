/**
 * 
 */
package com.ssic.cookbook.manager.service;

import com.ssic.cookbook.manager.dto.IndependentFixingsDto;

/**
 * @author wk.s
 *
 */
public interface IIndependentFixingsService {

	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(IndependentFixingsDto dto);
	
	/**
	 * <b>更新</b>
	 * <br>id为条件，其它field为参数
	 * @param dto
	 * @return
	 */
	public boolean updateById(IndependentFixingsDto dto);
	
	/**
	 * 根据条件逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(IndependentFixingsDto dto);
}
