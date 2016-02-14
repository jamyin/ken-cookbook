/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.CategoryClassDao;
import com.ssic.cookbook.manager.dto.CategoryClassDto;
import com.ssic.cookbook.manager.service.ICategoryClassService;

/**
 * @author wk.s
 *
 */
@Service
public class CategoryClassServiceImpl implements ICategoryClassService {

	@Autowired
	private CategoryClassDao categoryClassDaop;
	
	public boolean add(CategoryClassDto dto) {
		
		return categoryClassDaop.add(dto);
	}

	public boolean updateById(CategoryClassDto dto) {
		
		return categoryClassDaop.updateById(dto);
	}

	public boolean delete(CategoryClassDto dto) {
		
		return categoryClassDaop.delete(dto);
	}

}
