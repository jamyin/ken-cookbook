package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;
import com.ssic.cookbook.manager.mapper.IndependentFixingsExMapper;
import com.ssic.cookbook.manager.mapper.IndependentFixingsMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixings;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;
@Repository
public class IntelligentFixingsDao extends MyBatisBaseDao<IntelligentFixings>
{  
    @Autowired 
    @Getter
    private IndependentFixingsMapper mapper;

    @Autowired 
    @Getter
    private IndependentFixingsExMapper exmapper;

    @Autowired 
    @Getter
    private IntelligentFixingsMapper mappers;
    
    @Autowired 
    @Getter
    private IntelligentFixingsExMapper exmappers;

    public List<FixingsResultDto> findAllIntelligentProduct(FixingsResultDto fdto)
    {
        // TODO 添加方法注释
        List<FixingsResultDto> list=   exmapper.findAllIntelligentProduct(fdto);
        return list;
    }

    public List<FixingsResultDto> findAllIntelligentProductType()
    {
        // TODO 添加方法注释
        return exmapper.findAllIntelligentProductType();
    }

    public List<FixingsResultDto> findAllIntelligentProductName()
    {
        // TODO 添加方法注释
        return exmapper.findAllIntelligentProductName();
    }

    public int findCount()
    {
      
        return exmapper.findCount();
    }
    
    /**
     * @author YIn
     * @time:2015年12月31日 上午11:09:39
     */
    public boolean addSmart(IntelligentFixings smart) {
        //smart.setId(UUID.randomUUID()+"");
        smart.setCreateTime(new Date());
        smart.setStat(1);
        boolean flag = false;
        int i = mappers.insertSelective(smart);
        if(i == 1){
            flag = true;
        }else{
            flag = false;   
        }
    return flag;
    }

    /**
	 * @author YIn
     * @param record 
	 * @time:2016年1月7日 下午3:35:59
	 */
	public int updateIntelligentFixings(IntelligentFixings record) {
		return mappers.updateByPrimaryKeySelective(record);
	}

    public void deleteIntelligent(FixingsResultDto fdto)
    {
        // TODO 添加方法注释
        exmapper.deleteIntelligent(fdto);
    }

    /**
	 * @author YIn
     * @time:2016年1月8日 上午11:34:25
	 */
	public List<IntelligentFixingsDto> findIntelligentFixings(
			IntelligentFixingsDto dto) {
		
		return exmappers.findIntelligentFixings(dto);
	}

	/**
	 * @author YIn
	 * @time:2016年1月12日 上午9:42:49
	 */
	public int findCount(IntelligentFixingsDto dto) {
		return exmappers.findCount(dto);
	}
  

}

