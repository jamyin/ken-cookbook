/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.ProductCategoryDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.pojo.ProductCategory;
import com.ssic.cookbook.manager.service.IProductCategoryService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductCategoryServiceImpl </p>
 * <p>Description: 成品菜类别Service层实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午4:18:54	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午4:18:54</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService
{

    @Autowired
    private ProductCategoryDao categoryDao;

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductCategoryService#findListByPage(com.ssic.cookbook.manager.dto.ProductCategoryDto)   
     */
    
    public List<ProductCategoryDto> findListByPage(ProductCategoryDto productCategoryDto,
        LimitPageDto limitPageDto)
    {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDto, productCategory);
        List<ProductCategoryDto> listDto = new ArrayList<ProductCategoryDto>();
        List<ProductCategory> list = categoryDao.findForList(productCategory, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductCategoryDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCategoryService#findById(java.lang.String)   
    */
    public ProductCategoryDto findById(String productCategoryId)
    {
        ProductCategoryDto dto = new ProductCategoryDto();
        ProductCategory productCategory = categoryDao.findById(productCategoryId);
        if (productCategory != null)
        {
            dto = BeanUtils.createBeanByTarget(productCategory, ProductCategoryDto.class);
        }
        return dto;
    }

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */

    public void add(ProductCategoryDto productCategoryDto)
    {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDto, productCategory);
        productCategory.setStat(DataStatus.ENABLED);
        productCategory.setCreateTime(new Date());
        productCategory.setLastUpdateTime(new Date());
        categoryDao.insert(productCategory);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCategoryService#edit(com.ssic.cookbook.manager.dto.ProductCategoryDto)   
    */
  
    public void edit(ProductCategoryDto productCategoryDto)
    {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDto, productCategory);
        categoryDao.update(productCategory);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCategoryService#delete(com.ssic.cookbook.manager.dto.ProductCategoryDto)   
    */
 
    public void delete(ProductCategoryDto productCategoryDto)
    {
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDto, productCategory);
        categoryDao.update(productCategory);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCategoryService#findByName(java.lang.String)   
    */
    public ProductCategoryDto findByName(String name)
    {
        ProductCategory category = new ProductCategory();
        category.setName(name);
        List<ProductCategory> list = categoryDao.findByName(category);
        if (!CollectionUtils.isEmpty(list))
        {
            ProductCategoryDto dto = new ProductCategoryDto();
            BeanUtils.copyProperties(list.get(0), dto);
            return dto;
        }
        return null;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCategoryService#findCount(com.ssic.cookbook.manager.dto.ProductCategoryDto)   
    */
  
    public int findCount(ProductCategoryDto productCategoryDto)
    {
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(productCategoryDto, category);
        return categoryDao.findCount(category);
    }
}
