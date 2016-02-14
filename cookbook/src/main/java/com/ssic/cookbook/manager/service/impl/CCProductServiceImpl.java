/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.CCProductDao;
import com.ssic.cookbook.manager.dto.CategoryClassProductDto;
import com.ssic.cookbook.manager.service.ICCProductService;

/**
 * @author wk.s
 *
 */
@Service
public class CCProductServiceImpl implements ICCProductService {

	@Autowired
	private CCProductDao cCProductDao;
	
	public boolean add(CategoryClassProductDto dto) {
		
		return cCProductDao.add(dto);
	}

	public boolean updateById(CategoryClassProductDto dto) {
		
		return cCProductDao.updateById(dto);
	}

	public boolean delete(CategoryClassProductDto dto) {
		
		return cCProductDao.delete(dto);
	}

}
