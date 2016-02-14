package com.ssic.cookbook.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.admin.dao.DaoSupport;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dao.ProductionMethodDao;
import com.ssic.cookbook.manager.dto.ProductionMethodDto;
import com.ssic.cookbook.manager.pojo.ProductionMethod;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample;
import com.ssic.cookbook.manager.service.IProductionMethodService;

@Service
public class ProductionMethodServiceImpl implements IProductionMethodService
{
    
    @Autowired
    private DaoSupport dao;
    @Autowired
    private   ProductionMethodDao productionMethodDao;

    /**
     * 查询所有菜谱制作方法
     * (non-Javadoc)   
     * @return 
     * @see com.ssic.cookbook.manager.service.IProductionMethodService#findAllProductionMethod()
     */
     //@Cacheable(value = "default", key = "'cookbook.manager.dto.ProductionMethodDtoList'")
    public List<ProductionMethodDto> findAllProductionMethod(ProductionMethodDto pmd)
    {  // TODO 添加方法注释
        List<ProductionMethodDto> list = productionMethodDao.selectAll(pmd);
        if(list.isEmpty() ){
        }
       return list;
    }
    public int findCount()
    {         
        return productionMethodDao.findCount();
    }    
    /**
     * 插入菜谱
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductionMethodService#insertProductionMethod(com.ssic.cookbook.manager.pojo.ProductionMethod)
     */
    //@CacheEvict(value = "default", key ="'cookbook.manager.dto.ProductionMethodDtoList'", beforeInvocation = true)
    public void insertProductionMethod(ProductionMethod pm)
    {
       
        // TODO 添加方法注释
        pm.setId(null);
        pm.setStat(1);
        pm.setCreateTime(new Date());
        pm.setLastUpdateTime(pm.getCreateTime());
        productionMethodDao.insertSelective(pm);
    }
/**
 * 
 * (non-Javadoc)   
 * @see com.ssic.cookbook.manager.service.IProductionMethodService#updateProductionMethod(com.ssic.cookbook.manager.pojo.ProductionMethod)
 */
    //@CacheEvict(value = "default", key ="'cookbook.manager.dto.ProductionMethodDtoList'", beforeInvocation = true)
    public void updateProductionMethod(ProductionMethod productionMethod)
    {
    // TODO 添加方法注释
        productionMethod.setLastUpdateTime(new Date());
        productionMethod.setStat(null);
        
    productionMethodDao.updateByPrimaryKeySelective(productionMethod);
    }      

            
                /**
                 * 
                 * (non-Javadoc)   
                 * @see com.ssic.cookbook.manager.service.IProductionMethodService#findProductionMethodById(java.lang.String)
                 */
    //@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductionMethodDtoList'", beforeInvocation = true)
                        public ProductionMethod findProductionMethodById(String id)
                        {
                            // TODO 添加方法注释
                            ProductionMethod  productionMethod=productionMethodDao.findProductionMethodById(id);
                            return productionMethod;
                        }
    /**
     * 
     * @author pengcheng.yang
     * @desc 导出制作方法
     * @date 2015-12-28
     */
    @SuppressWarnings("unchecked")
    public List<PageData> expProMetExcelFindAll(PageData pd) throws Exception {
        return (List<PageData>) dao.expExcelFindAll("ProductXMapper.expProMethExcelFindAll", pd);
    }

    
//@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductionMethodDtoList'", beforeInvocation = true)
    public void deleteProductionMethod(ProductionMethodDto pmd)
    {
        // TODO 添加方法注释
        productionMethodDao.deleteByPrimaryKey(pmd);
        
    }


 
}

