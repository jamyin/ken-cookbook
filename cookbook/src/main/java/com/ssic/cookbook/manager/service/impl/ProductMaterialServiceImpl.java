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

import com.ssic.cookbook.manager.dao.ProductMaterialDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductMaterialDto;
import com.ssic.cookbook.manager.pojo.ProductMaterial;
import com.ssic.cookbook.manager.service.IProductMaterialService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductMaterialServiceImpl </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月24日 下午3:31:02	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月24日 下午3:31:02</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductMaterialServiceImpl implements IProductMaterialService
{

    @Autowired
    private ProductMaterialDao dao;

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findListByPage(com.ssic.cookbook.manager.dto.ProductDto)   
    */

    public List<ProductMaterialDto> findListByPage(ProductMaterialDto productMaterialDto,
        LimitPageDto limitPageDto)
    {
        ProductMaterial productMaterial = new ProductMaterial();
        BeanUtils.copyProperties(productMaterialDto, productMaterial);
        List<ProductMaterialDto> listDto = new ArrayList<ProductMaterialDto>();
        List<ProductMaterial> list = dao.findForList(productMaterial, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductMaterialDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */
 
    public void add(ProductMaterialDto productMaterialDto)
    {
        ProductMaterial productMaterial = new ProductMaterial();
        BeanUtils.copyProperties(productMaterialDto, productMaterial);
        if (productMaterialDto.getMaterialWeight() != null)
        {
            productMaterial.setMaterialWeight(productMaterialDto.getMaterialWeight());
        }
        productMaterial.setStat(DataStatus.ENABLED);
        productMaterial.setCreateTime(new Date());
        productMaterial.setLastUpdateTime(new Date());
        dao.insert(productMaterial);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductMaterialService#edit(com.ssic.cookbook.manager.dto.ProductMaterialDto)   
    */

    public void edit(ProductMaterialDto productMaterialDto)
    {
        ProductMaterial productMaterial = new ProductMaterial();
        BeanUtils.copyProperties(productMaterialDto, productMaterial);
        dao.update(productMaterial);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductMaterialService#delete(com.ssic.cookbook.manager.dto.ProductMaterialDto)   
    */
 
    public void delete(ProductMaterialDto productMaterialDto)
    {
        ProductMaterial productMaterial = new ProductMaterial();
        BeanUtils.copyProperties(productMaterialDto, productMaterial);
        productMaterial.setStat(DataStatus.DISABLED);
        dao.update(productMaterial);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductMaterialService#findCount(com.ssic.cookbook.manager.dto.ProductMaterialDto)   
    */
    public int findCount(ProductMaterialDto productMaterialDto)
    {
        ProductMaterial productMaterial = new ProductMaterial();
        BeanUtils.copyProperties(productMaterialDto, productMaterial);
        return dao.findCount(productMaterial);
    }

}
