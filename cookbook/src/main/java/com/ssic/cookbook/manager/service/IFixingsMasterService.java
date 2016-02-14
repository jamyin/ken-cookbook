/**
 * 
 */
package com.ssic.cookbook.manager.service;

import com.ssic.cookbook.manager.dto.FixingsMasterDto;

/**
 * @author wk.s
 *
 */
public interface IFixingsMasterService {

	/**
	 * 新增
	 * 
	 * @param dto
	 * @return
	 */
	public boolean add(FixingsMasterDto dto);
	
	/**
	 * 根据id更新
	 * @param dto
	 * @return
	 */
	public boolean updateById(FixingsMasterDto dto);
	
	/**
	 * 逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(FixingsMasterDto dto);
}
