/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.FixingsMasterDao;
import com.ssic.cookbook.manager.dto.FixingsMasterDto;
import com.ssic.cookbook.manager.service.IFixingsMasterService;

/**
 * @author wk.s
 *
 */
@Service
public class FixingsMasterServiceImpl implements IFixingsMasterService {

	@Autowired
	private FixingsMasterDao fixingsMasterDao;
	
	public boolean add(FixingsMasterDto dto) {
		
		return fixingsMasterDao.add(dto);
	}

	public boolean updateById(FixingsMasterDto dto) {
		
		return fixingsMasterDao.updateById(dto);
	}

	public boolean delete(FixingsMasterDto dto) {
		
		return fixingsMasterDao.delete(dto);
	}

}
