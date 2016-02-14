package com.ssic.cookbook.manager.dao;

import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;





import com.ssic.cookbook.manager.dto.ProductionMethodDto;
import com.ssic.cookbook.manager.mapper.ProductStyleMapper;
import com.ssic.cookbook.manager.mapper.ProductionMethodExMapper;
import com.ssic.cookbook.manager.mapper.ProductionMethodMapper;
import com.ssic.cookbook.manager.pojo.ProductionMethod;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;
@Repository
public class ProductionMethodDao extends MyBatisBaseDao<ProductionMethod>
{

    @Autowired 
    @Getter
    private ProductionMethodMapper mapper;
    @Autowired 
    @Getter
    private ProductionMethodExMapper exmapper;

    /**
     * 
     * selectAll：返回所有烹饪方法集合
     * @param example
     * @return
     * @exception	
     * @author Administrator
     * @date 2015年12月18日 下午1:31:53
     */
    public List<ProductionMethodDto> selectAll(ProductionMethodDto pmd)
    {
       
                    
        return exmapper.selectByExample(pmd);
    }

    /**
     * 返回烹饪方法数量
     * findCount：一句话描述方法功能
     * @param example
     * @return
     * @exception	
     * @author Administrator
     * @date 2015年12月18日 下午1:31:24
     */
    public int findCount()
    {
        // TODO 添加方法注释\\
        ProductionMethodExample  example = new ProductionMethodExample(); 
        Criteria criteria = example.createCriteria();
        criteria.andStatEqualTo(CookbookFields.Enable);
        example.setOrderByClause("desc");
        return  mapper.countByExample(example);
    }

 

    public ProductionMethod findProductionMethodById(String id)
    {
        // TODO 添加方法注释
       
        return  exmapper.findProductionMethodById(id);
    }

    public void deleteByPrimaryKey(ProductionMethodDto pmd)
    {
        // TODO 添加方法注释
        exmapper.deleteByPrimaryKey(pmd);
        
    }


   

}

