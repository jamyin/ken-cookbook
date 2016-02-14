/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;

import lombok.Getter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.mapper.ProductMapper;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.pojo.ProductExample;
import com.ssic.cookbook.manager.pojo.ProductExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductDao </p>
 * <p>Description: 成品菜Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博
 * @date 2015年12月16日 上午10:44:39	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午10:44:39</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductDao extends MyBatisBaseDao<Product>
{
    @Autowired
    @Getter
    private ProductMapper mapper;

    private static final Logger log = Logger.getLogger(ProductDao.class);

    public List<Product> findForList(Product param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数Product为空");
        }
        ProductExample example = new ProductExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //所属类别id
        if (!StringUtils.isEmpty(param.getProductCategoryId()))
        {
            criteria.andProductCategoryIdEqualTo(param.getProductCategoryId());
        }
        //所属颜色id
        if (!StringUtils.isEmpty(param.getProductColorId()))
        {
            criteria.andProductColorIdEqualTo(param.getProductColorId());
        }
        //所属烹饪方式id
        if (!StringUtils.isEmpty(param.getProductCuisineId()))
        {
            criteria.andProductCuisineIdEqualTo(param.getProductCuisineId());
        }
        //所属形状id
        if (!StringUtils.isEmpty(param.getProductShapeId()))
        {
            criteria.andProductShapeIdEqualTo(param.getProductShapeId());
        }
        //所属口味id
        if (!StringUtils.isEmpty(param.getProductTasteId()))
        {
            criteria.andProductTasteIdEqualTo(param.getProductTasteId());
        }
        //所属菜系id
        if (!StringUtils.isEmpty(param.getProductStyleId()))
        {
            criteria.andProductStyleIdEqualTo(param.getProductStyleId());
        }
        //每份定价
        if (!StringUtils.isEmpty(param.getEachPricing()))
        {
            criteria.andEachPricingEqualTo(param.getEachPricing());
        }
        //数量/份数
        if (!StringUtils.isEmpty(param.getDemandNumber()))
        {
            criteria.andDemandNumberEqualTo(param.getDemandNumber());
        }
        //单份克重
        if (!StringUtils.isEmpty(param.getSingleWeight()))
        {
            criteria.andSingleWeightEqualTo(param.getSingleWeight());
        }
        //成品菜名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        //如果有分页对象;
        if (limitPageDto != null && !StringUtils.isEmpty(limitPageDto.getStar())
            && !StringUtils.isEmpty(limitPageDto.getEnd()))
        {
            example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
                + limitPageDto.getEnd());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);

        return mapper.selectByExample(example);
    }

    public List<Product> findForList(Product param, LimitPageDto limitPageDto,Date dateStart,Date dateEnd,String materialCompare)
    {
        if (param == null)
        {
            log.error("参数Product为空");
        }
        ProductExample example = new ProductExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //所属类别id
        if (!StringUtils.isEmpty(param.getProductCategoryId()))
        {
            criteria.andProductCategoryIdEqualTo(param.getProductCategoryId());
        }
        //所属颜色id
        if (!StringUtils.isEmpty(param.getProductColorId()))
        {
            criteria.andProductColorIdEqualTo(param.getProductColorId());
        }
        //所属烹饪方式id
        if (!StringUtils.isEmpty(param.getProductCuisineId()))
        {
            criteria.andProductCuisineIdEqualTo(param.getProductCuisineId());
        }
        //所属形状id
        if (!StringUtils.isEmpty(param.getProductShapeId()))
        {
            criteria.andProductShapeIdEqualTo(param.getProductShapeId());
        }
        //所属口味id
        if (!StringUtils.isEmpty(param.getProductTasteId()))
        {
            criteria.andProductTasteIdEqualTo(param.getProductTasteId());
        }
        //所属菜系id
        if (!StringUtils.isEmpty(param.getProductStyleId()))
        {
            criteria.andProductStyleIdEqualTo(param.getProductStyleId());
        }
        //每份定价
        if (!StringUtils.isEmpty(param.getEachPricing()))
        {
            criteria.andEachPricingEqualTo(param.getEachPricing());
        }
        
      //单份克重比较
        if(!StringUtils.isEmpty(materialCompare)&&!StringUtils.isEmpty(param.getSingleWeight())){
      	  if(">".equals(materialCompare)){
      		  criteria.andSingleWeightGreaterThan(param.getSingleWeight());
      	  }else if("=".equals(materialCompare)){
      		  criteria.andSingleWeightEqualTo(param.getSingleWeight());
      	  }else{
      		  criteria.andSingleWeightLessThan(param.getSingleWeight());
      	  }
        }
        
        //成品菜名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        //如果有分页对象;
        if (limitPageDto != null && !StringUtils.isEmpty(limitPageDto.getStar())
            && !StringUtils.isEmpty(limitPageDto.getEnd()))
        {
            example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
                + limitPageDto.getEnd());
        }
        //创建时间起始
        if(dateStart!=null && dateEnd!=null){
        	criteria.andCreateTimeBetween(dateStart, dateEnd);
        }
        criteria.andStatEqualTo(CookbookFields.Enable);

        return mapper.selectByExample(example);
    }
    
    
    public List<Product> findDistinctComponents(Product param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数Product为空");
        }
        ProductExample example = new ProductExample();
        Criteria criteria = example.createCriteria();
        //所属类别id
        if (!StringUtils.isEmpty(param.getProductCategoryId()))
        {
            criteria.andProductCategoryIdEqualTo(param.getProductCategoryId());
        }
        //所属颜色id
        if (!StringUtils.isEmpty(param.getProductColorId()))
        {
            criteria.andProductColorIdEqualTo(param.getProductColorId());
        }
        //所属烹饪方式id
        if (!StringUtils.isEmpty(param.getProductCuisineId()))
        {
            criteria.andProductCuisineIdEqualTo(param.getProductCuisineId());
        }
        //所属形状id
        if (!StringUtils.isEmpty(param.getProductShapeId()))
        {
            criteria.andProductShapeIdEqualTo(param.getProductShapeId());
        }
        //所属口味id
        if (!StringUtils.isEmpty(param.getProductTasteId()))
        {
            criteria.andProductTasteIdEqualTo(param.getProductTasteId());
        }
        //所属菜系id
        if (!StringUtils.isEmpty(param.getProductStyleId()))
        {
            criteria.andProductStyleIdEqualTo(param.getProductStyleId());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.selectByExample(example);
    }

    public Product findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(Product param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBrand(Product param)
    {
        mapper.insert(param);
    }

    /**     
     * findCount：查找成品菜数量
     * @param productDto
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午1:14:03	 
     */
    public int findCount(Product param)
    {
        if (param == null)
        {
            log.error("参数Product为空");
        }
        ProductExample example = new ProductExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //所属类别id
        if (!StringUtils.isEmpty(param.getProductCategoryId()))
        {
            criteria.andProductCategoryIdEqualTo(param.getProductCategoryId());
        }
        //所属颜色id
        if (!StringUtils.isEmpty(param.getProductColorId()))
        {
            criteria.andProductColorIdEqualTo(param.getProductColorId());
        }
        //所属烹饪方式id
        if (!StringUtils.isEmpty(param.getProductCuisineId()))
        {
            criteria.andProductCuisineIdEqualTo(param.getProductCuisineId());
        }
        //所属形状id
        if (!StringUtils.isEmpty(param.getProductShapeId()))
        {
            criteria.andProductShapeIdEqualTo(param.getProductShapeId());
        }
        //所属口味id
        if (!StringUtils.isEmpty(param.getProductTasteId()))
        {
            criteria.andProductTasteIdEqualTo(param.getProductTasteId());
        }
        //所属菜系id
        if (!StringUtils.isEmpty(param.getProductStyleId()))
        {
            criteria.andProductStyleIdEqualTo(param.getProductStyleId());
        }
        //每份定价
        if (!StringUtils.isEmpty(param.getEachPricing()))
        {
            criteria.andEachPricingEqualTo(param.getEachPricing());
        }
        
        //数量
        if (!StringUtils.isEmpty(param.getDemandNumber()))
        {
            criteria.andDemandNumberEqualTo(param.getDemandNumber());
        }
        //单份克重
        if (!StringUtils.isEmpty(param.getSingleWeight()))
        {
            criteria.andSingleWeightEqualTo(param.getSingleWeight());
        }
        //成品菜名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.countByExample(example);
    }

    public int findCount(Product param,Date dataStart ,Date dataEnd,String materialCompare){
    	  if (param == null)
          {
              log.error("参数Product为空");
          }
          ProductExample example = new ProductExample();
          Criteria criteria = example.createCriteria();
          if (!StringUtils.isEmpty(param.getId()))
          {
              criteria.andIdEqualTo(param.getId());
          }
          //所属类别id
          if (!StringUtils.isEmpty(param.getProductCategoryId()))
          {
              criteria.andProductCategoryIdEqualTo(param.getProductCategoryId());
          }
          //所属颜色id
          if (!StringUtils.isEmpty(param.getProductColorId()))
          {
              criteria.andProductColorIdEqualTo(param.getProductColorId());
          }
          //所属烹饪方式id
          if (!StringUtils.isEmpty(param.getProductCuisineId()))
          {
              criteria.andProductCuisineIdEqualTo(param.getProductCuisineId());
          }
          //所属形状id
          if (!StringUtils.isEmpty(param.getProductShapeId()))
          {
              criteria.andProductShapeIdEqualTo(param.getProductShapeId());
          }
          //所属口味id
          if (!StringUtils.isEmpty(param.getProductTasteId()))
          {
              criteria.andProductTasteIdEqualTo(param.getProductTasteId());
          }
          //所属菜系id
          if (!StringUtils.isEmpty(param.getProductStyleId()))
          {
              criteria.andProductStyleIdEqualTo(param.getProductStyleId());
          }
          //每份定价
          if (!StringUtils.isEmpty(param.getEachPricing()))
          {
              criteria.andEachPricingEqualTo(param.getEachPricing());
          }
          
          //单份克重比较
          if(!StringUtils.isEmpty(materialCompare)&&!StringUtils.isEmpty(param.getSingleWeight())){
        	  if(">".equals(materialCompare)){
        		  criteria.andSingleWeightGreaterThan(param.getSingleWeight());
        	  }else if("=".equals(materialCompare)){
        		  criteria.andSingleWeightEqualTo(param.getSingleWeight());
        	  }else{
        		  criteria.andSingleWeightLessThan(param.getSingleWeight());
        	  }
          }
          
          //单份克重
          if (!StringUtils.isEmpty(param.getDemandNumber()))
          {
              criteria.andDemandNumberEqualTo(param.getDemandNumber());
          }
          //成品菜名称
          if (!StringUtils.isEmpty(param.getName()))
          {
              criteria.andNameLike("%" + param.getName() + "%");
          }
          //创建时间起始
          if(dataStart!=null && dataEnd!=null){
          	 criteria.andCreateTimeBetween(dataStart, dataEnd);
          }
          
          criteria.andStatEqualTo(CookbookFields.Enable);
          return mapper.countByExample(example);
    }
    
    
    /**     
     * update：更新成品菜
     * @param product
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午4:42:38	 
     */
    public void update(Product product)
    {
        if (product != null && !StringUtils.isEmpty(product.getId()))
        {
            product.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(product);
        }
    }

    /**     
     * findByName：通过成品菜名称查找成品菜
     * @param productName
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月28日 下午2:48:28	 
     */
    public Product findByName(String productName)
    {

        ProductExample example = new ProductExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(productName))
        {
            criteria.andNameEqualTo(productName);
        }
        criteria.andStatEqualTo(DataStatus.ENABLED);
        List<Product> list = mapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(list))
        {
            return list.get(0);
        }
        return null;
    }
}
