package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;
import com.ssic.cookbook.manager.dto.SmartResultDto;

public interface IIntelligentFixingsService 
{

    List<FixingsResultDto> findAllIntelligentProduct(FixingsResultDto fdto);

    List<FixingsResultDto> findAllIntelligentProductType();

    List<FixingsResultDto> findAllIntelligentProductName();

    int findCount();

//    int findCount(IndependentFixingsExample example);

    /**
     * 添加智能配菜
     * @author YIn
     * @time:2015年12月31日 上午9:27:01
     * @param dto
     * @return
     */
	SmartResultDto addSmart(IntelligentFixingsDto dto);

    /**
     * 查询智能配菜
     * @author YIn
     * @time:2016年1月4日 上午9:51:00
     * @param dto
     * @param limitPageDto 
     * @return
     */
	List<IntelligentFixingsDto> findSmart(IntelligentFixingsDto dto);
	
    /**
     * 查询智能配菜条数
     * @author YIn
     * @time:2016年1月12日 上午9:40:10
     * @param dto
     * @return
     */
	int findCount(IntelligentFixingsDto dto); 
    
	/**
	 * 修改智能配菜
	 * @author YIn
	 * @time:2016年1月7日 下午3:09:37
	 * @param dto
	 * @return
	 */
	int update(IntelligentFixingsDto dto);
/**
 * 
 * deleteIntelligent：删除配菜
 * @param fdto
 * @exception	
 * @author Administrator
 * @date 2016年1月5日 上午11:06:22
 */
    void deleteIntelligent(FixingsResultDto fdto);



}

