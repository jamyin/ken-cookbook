package com.ssic.cookbook.manager.service;
import java.util.List;

import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.ProductionMethodDto;
import com.ssic.cookbook.manager.pojo.ProductionMethod;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample;

public interface IProductionMethodService
{
    /**
     * 
     * findAllProductionMethod：一句话描述方法功能
     * @param page
     * @return
     * @exception   
     * @author Administrator
     * @date 2015年12月18日 下午1:36:24
     */
    List<ProductionMethodDto> findAllProductionMethod(ProductionMethodDto pmd);
    /**
     * 
     * findCount：查询记录数
     * @param example
     * @return
     * @exception	
     * @author Administrator
     * @date 2015年12月18日 下午3:43:50
     */
    int findCount();

    /**
     * 
     * insterProductionMethod：新增菜谱
     * @param productionMethod
     * @return
     * @exception   
     * @author Administrator
     * @date 2015年12月18日 下午1:55:08
     */
    void insertProductionMethod(ProductionMethod productionMethod);
    /**
     * 
     * deleteProductionMethod：根据ID删除菜谱
     * @param pmd
     * @exception   
     * @author Administrator
     * @date 2015年12月18日 下午3:34:35
     */
    void deleteProductionMethod(ProductionMethodDto pmd);
/**
 * 修改菜谱
 * updateProductionMethod：一句话描述方法功能
 * @param productionMethod
 * @exception	
 * @author Administrator
 * @date 2015年12月18日 下午3:52:24
 */
    void updateProductionMethod(ProductionMethod productionMethod);
    
    /**
     * @author pengcheng.yang
     * @desc 导出原料配置数据
     * @param materialDto
     * @return
     * @throws Exception 
     */
    public List<PageData> expProMetExcelFindAll(PageData pd) throws Exception;
  
    /**
     * 
     * findProductionMethodById：根据id查询要修改的数据
     * @param id
     * @return
     * @exception	
     * @author Administrator
     * @date 2015年12月28日 上午11:35:14
     */
    ProductionMethod findProductionMethodById(String id);
    
}

