/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.IndependentFixingsDao;
import com.ssic.cookbook.manager.dto.IndependentFixingsDto;
import com.ssic.cookbook.manager.service.IIndependentFixingsService;

/**
 * @author Administrator
 *
 */

@Service
public class IndependentFixingsServiceImpl implements IIndependentFixingsService {

	@Autowired
	private IndependentFixingsDao independentFixingsDao;
	
	public boolean add(IndependentFixingsDto dto) {

		return independentFixingsDao.add(dto);
	}

	public boolean updateById(IndependentFixingsDto dto) {
		
		return independentFixingsDao.updateById(dto);
	}

	public boolean delete(IndependentFixingsDto dto) {
		
		return independentFixingsDao.delete(dto);
	}

}
