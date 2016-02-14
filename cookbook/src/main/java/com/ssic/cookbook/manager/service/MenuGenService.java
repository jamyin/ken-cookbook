/**
 * 
 */
package com.ssic.cookbook.manager.service;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;

/**
 * @author wk.s
 *
 */
public interface MenuGenService {

	public String generateMenuV02(IntelligentFixingsDto dto, Page page);
}
