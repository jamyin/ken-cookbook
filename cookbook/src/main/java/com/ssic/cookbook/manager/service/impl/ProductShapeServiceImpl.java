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

import com.ssic.cookbook.manager.dao.ProductShapeDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.ProductCuisine;
import com.ssic.cookbook.manager.pojo.ProductShape;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.service.IProductShapeService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductShapeShapeServiceImpl </p>
 * <p>Description: 成品菜形状Service层实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:52:24	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:52:24</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductShapeServiceImpl implements IProductShapeService
{
    @Autowired
    private ProductShapeDao shapeDao;

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductShapeService#findListByPage(com.ssic.cookbook.manager.dto.ProductShapeDto)   
     */
   
    public List<ProductShapeDto> findListByPage(ProductShapeDto productShapeDto, LimitPageDto limitPageDto)
    {
        ProductShape productShape = new ProductShape();
        BeanUtils.copyProperties(productShapeDto, productShape);
        List<ProductShapeDto> listDto = new ArrayList<ProductShapeDto>();
        List<ProductShape> list = shapeDao.findForList(productShape, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductShapeDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductShapeService#findById(java.lang.String)   
    */
    public ProductShapeDto findById(String productShapeId)
    {
        ProductShapeDto dto = new ProductShapeDto();
        ProductShape ProductShape = shapeDao.findById(productShapeId);
        if (ProductShape != null)
        {
            dto = BeanUtils.createBeanByTarget(ProductShape, ProductShapeDto.class);
        }
        return dto;
    }

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */
   
    public void add(ProductShapeDto productShapeDto)
    {
        ProductShape productShape = new ProductShape();
        BeanUtils.copyProperties(productShapeDto, productShape);
        productShape.setStat(DataStatus.ENABLED);
        productShape.setCreateTime(new Date());
        productShape.setLastUpdateTime(new Date());
        shapeDao.insert(productShape);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductShapeService#edit(com.ssic.cookbook.manager.dto.ProductShapeDto)   
    */
   
    public void edit(ProductShapeDto productShapeDto)
    {
        ProductShape productShape = new ProductShape();
        BeanUtils.copyProperties(productShapeDto, productShape);
        shapeDao.update(productShape);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductShapeService#delete(com.ssic.cookbook.manager.dto.ProductShapeDto)   
    */
  
    public void delete(ProductShapeDto productShapeDto)
    {
        ProductShape productShape = new ProductShape();
        BeanUtils.copyProperties(productShapeDto, productShape);
        shapeDao.update(productShape);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductShapeService#findByName(java.lang.String)   
    */
    public ProductShapeDto findByName(String name)
    {
        ProductShape productShape = new ProductShape();
        productShape.setName(name);
        List<ProductShape> list = shapeDao.findByName(productShape);
        if (!CollectionUtils.isEmpty(list))
        {
            ProductShapeDto dto = new ProductShapeDto();
            BeanUtils.copyProperties(list.get(0), dto);
            return dto;
        }
        return null;
    }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductShapeService#findCount(com.ssic.cookbook.manager.dto.ProductShapeDto)   
     */
   
    public int findCount(ProductShapeDto productShapeDto)
    {
        ProductShape productShape = new ProductShape();
        BeanUtils.copyProperties(productShapeDto, productShape);
        return shapeDao.findCount(productShape);
    }

}
