/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.admin.dao.DaoSupport;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dao.ProductDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductMaterialDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.service.IMaterialService;
import com.ssic.cookbook.manager.service.IProductCategoryService;
import com.ssic.cookbook.manager.service.IProductColorService;
import com.ssic.cookbook.manager.service.IProductCuisineService;
import com.ssic.cookbook.manager.service.IProductMaterialService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.IProductShapeService;
import com.ssic.cookbook.manager.service.IProductStyleService;
import com.ssic.cookbook.manager.service.IProductTasteService;
import com.ssic.cookbook.redis.ColorRedisTest;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductServiceImpl </p>
 * <p>Description: 成品菜Service实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:25:26	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:25:26</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private DaoSupport dao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private IProductCategoryService categoryService;
    @Autowired
    private IProductShapeService shapeService;
    @Autowired
    private IProductStyleService styleService;
    @Autowired
    private IProductColorService colorService;
    @Autowired
    private IProductCuisineService cuisineService;
    @Autowired
    private IProductTasteService tasteService;
    @Autowired
    IProductMaterialService productMaterialService;
    @Autowired
    private IMaterialService materialService;
    protected static final Log logger = LogFactory.getLog(ProductServiceImpl.class);

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findListByPage(com.ssic.cookbook.manager.dto.ProductDto)   
    */

    public List<ProductDto> findListByPage(ProductDto productDto, LimitPageDto limitPageDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        List<ProductDto> listDto = new ArrayList<ProductDto>();
        List<Product> list = productDao.findForList(product, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {

            for (Product p : list)
            {
                ProductDto dto = new ProductDto();
                BeanUtils.copyProperties(p, dto);
                if (p.getSingleCost() != null)
                {
                    dto.setSingleCost(p.getSingleCost().doubleValue() / 100);
                }
                if (p.getTotalCost() != null)
                {
                    dto.setTotalCost(Math.round(p.getTotalCost().doubleValue() / 100 * 100) / 100.0);
                }
                listDto.add(dto);
            }
            if (!CollectionUtils.isEmpty(listDto))
            {
                for (ProductDto dto : listDto)
                {
                    //重新组装成品菜dto对象;
                    setProductValues(dto);
                }
            }
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findById(java.lang.String)   
    */
    public ProductDto findById(String productId)
    {
        ProductDto dto = new ProductDto();
        Product product = productDao.findById(productId);
        if (product != null)
        {
            dto = BeanUtils.createBeanByTarget(product, ProductDto.class);
        }
        dto.setSingleCost(product.getSingleCost().doubleValue() / 100);
        dto.setTotalCost(Math.round(product.getTotalCost().doubleValue() / 100 * 100) / 100.0);
        return dto;
    }

    private void setProductValues(ProductDto dto)
    {
        //菜品类别名称放入返回dto对象中;
        if (!StringUtils.isEmpty(dto.getProductCategoryId()))
        {
            ProductCategoryDto categoryDto = categoryService.findById(dto.getProductCategoryId());
            if (!StringUtils.isEmpty(categoryDto.getName()))
            {
                dto.setProductCategoryName(categoryDto.getName());
            }
        }
        //菜品颜色名称放入返回dto对象中;
        if (!StringUtils.isEmpty(dto.getProductColorId()))
        {
            ProductColorDto colorDto = colorService.findById(dto.getProductColorId());
            if (!StringUtils.isEmpty(colorDto.getName()))
            {
                dto.setProductColorName(colorDto.getName());
            }
        }
        //菜系名称放入返回dto对象中;
        if (!StringUtils.isEmpty(dto.getProductStyleId()))
        {
            ProductStyleDto styleDto = styleService.findById(dto.getProductStyleId());
            if (!StringUtils.isEmpty(styleDto.getName()))
            {
                dto.setProductStyleName(styleDto.getName());
            }
        }
        //烹饪方法名称放入返回dto对象中;
        if (!StringUtils.isEmpty(dto.getProductCuisineId()))
        {
            ProductCuisineDto cuisineDto = cuisineService.findById(dto.getProductCuisineId());
            if (!StringUtils.isEmpty(cuisineDto.getName()))
            {
                dto.setProductCuisineName(cuisineDto.getName());
            }
        }
        //菜品品味名称放入返回dto对象中;
        if (!StringUtils.isEmpty(dto.getProductTasteId()))
        {
            ProductTasteDto tasteDto = tasteService.findById(dto.getProductTasteId());
            if (!StringUtils.isEmpty(tasteDto.getName()))
            {
                dto.setProductTasteName(tasteDto.getName());
            }
        }
        //菜品形状名称放入返回dto对象中;
        if (!StringUtils.isEmpty(dto.getProductShapeId()))
        {
            ProductShapeDto shapeDto = shapeService.findById(dto.getProductShapeId());
            if (!StringUtils.isEmpty(shapeDto.getName()))
            {
                dto.setProductShapeName(shapeDto.getName());
            }
        }

        /*    if (!StringUtils.isEmpty(dto.getSingleCost()) && !StringUtils.isEmpty(dto.getDemandNumber()))
            {
                BigDecimal b1 = new BigDecimal(Double.valueOf(dto.getSingleCost()));
                BigDecimal b2 = new BigDecimal(Double.valueOf(dto.getDemandNumber()));
                //放入总成本
                dto.setTotalCost(b1.multiply(b2).doubleValue());
            }*/
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findCount(com.ssic.cookbook.manager.dto.ProductDto)   
    */
    /*@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList'", beforeInvocation = true)*/
    @Caching(evict = {@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList:'"),
        @CacheEvict(value = "default", key = "'cookbook.manager.dto.findAllRedis'") })
    public int findCount(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return productDao.findCount(product);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
    */
    /*@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList'", beforeInvocation = true)*/
    @Caching(evict = {@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList:'"),
        @CacheEvict(value = "default", key = "'cookbook.manager.dto.findAllRedis'") })
    public void add(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setStat(DataStatus.ENABLED);
        product.setCreateTime(new Date());
        product.setLastUpdateTime(new Date());
        //价格转化为long
        Double singleCost = Double.valueOf(productDto.getSingleCost()) * 100;
        product.setSingleCost(singleCost.longValue());
        //总价
        product.setTotalCost(singleCost.longValue() * productDto.getDemandNumber());
        //插入成品菜
        productDao.insert(product);
        //插入成品菜原料关系
        addProductMaterial(productDto);
    }

    private void addProductMaterial(ProductDto productDto)
    { //插入成品菜-原料关系表
        List<String> idList = productDto.getMaterialIdList();
        List<ProductMaterialDto> productMaterialDtoList = new ArrayList<ProductMaterialDto>();
        if (!CollectionUtils.isEmpty(idList))
        {
            for (String materialId : idList)
            {
                boolean isTrue = validExist(materialId, productMaterialDtoList);
                if (isTrue)
                {
                    ProductMaterialDto productMaterialDto = new ProductMaterialDto();
                    productMaterialDto.setId(UUID.randomUUID().toString());
                    productMaterialDto.setMaterialId(materialId);
                    productMaterialDto.setProductId(productDto.getId());
                    productMaterialDtoList.add(productMaterialDto);
                }

            }

            for (int i = 0; i < productDto.getMaterialWeightList().size(); i++)
            {
                Integer materialWeight = productDto.getMaterialWeightList().get(i);
                productMaterialDtoList.get(i).setMaterialWeight(materialWeight);
                continue;
            }

            for (ProductMaterialDto dto : productMaterialDtoList)
            {
                //插入成品菜原料关系
                if (dto.getMaterialWeight() == null)
                {
                    dto.setMaterialWeight(1000);
                }
                productMaterialService.add(dto);
            }
        }
    }

    /**     
     * validExist：验证该集合中是否存在某一个对象;
     * @param materialId
     * @param productMaterialDtoList
     * @return
     * @exception	
     * @author 刘博
     * @date 2016年1月12日 下午1:36:32	 
     */
    private boolean validExist(String materialId, List<ProductMaterialDto> productMaterialDtoList)
    {
        for (ProductMaterialDto dto : productMaterialDtoList)
        {
            if (materialId.equals(dto.getMaterialId()))
            { //如果已经存在该id,则返回false;
                return false;
            }
        }
        return true;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#edit(com.ssic.cookbook.manager.dto.ProductDto)   
    */
    /* @CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList'", beforeInvocation = true)*/
    @Caching(evict = {@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList:'"),
        @CacheEvict(value = "default", key = "'cookbook.manager.dto.findAllRedis'") })
    public void edit(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        //价格转化为long
        Double singleCost = Double.valueOf(productDto.getSingleCost()) * 100;
        product.setSingleCost(singleCost.longValue());
        //总价
        product.setTotalCost(singleCost.longValue() * productDto.getDemandNumber());
        productDao.update(product);
        //删除原来的成品菜原料关系
        deleteProductMaterial(productDto.getId());
        //插入成品菜原料关系
        addProductMaterial(productDto);

    }

    public void editForUpload(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productDao.update(product);
    }

    private void deleteProductMaterial(String productId)
    { //插入成品菜-原料关系表
        ProductMaterialDto productMaterialDto = new ProductMaterialDto();
        productMaterialDto.setProductId(productId);
        List<ProductMaterialDto> list = productMaterialService.findListByPage(productMaterialDto, null);

        if (!CollectionUtils.isEmpty(list))
        {
            for (ProductMaterialDto proMaterDto : list)
            {//删除原有的成品菜-原料关系
                productMaterialService.delete(proMaterDto);
            }
        }
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#delete(com.ssic.cookbook.manager.dto.ProductDto)   
    */
    /*@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList'", beforeInvocation = true)*/
    @Caching(evict = {@CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductDtoList:'"),
        @CacheEvict(value = "default", key = "'cookbook.manager.dto.findAllRedis'") })
    public void delete(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productDao.update(product);

    }

    /**
     * @author pengcheng.yang
     * @desc 导出成品菜数据
     * @param materialDto
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public List<PageData> expExcelFindAll(PageData pd) throws Exception
    {
        return (List<PageData>) dao.expExcelFindAll("ProductXMapper.expExcelFindAll", pd);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findDistinctComponents(com.ssic.cookbook.manager.pojo.Product, com.ssic.cookbook.manager.dto.LimitPageDto)   
    */
    public List<ProductDto> findDistinctComponents(ProductDto product, LimitPageDto limitPageDto)
    {
        Product param = new Product();
        BeanUtils.copyProperties(product, param);
        List<ProductDto> listDto = new ArrayList<ProductDto>();
        List<Product> list = productDao.findDistinctComponents(param, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductDto.class);
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findByName(java.lang.String)   
    */
    public ProductDto findByName(String productName)
    {
        ProductDto dto = new ProductDto();
        Product p = productDao.findByName(productName);
        if (p != null)
        {
            BeanUtils.copyProperties(p, dto);
        }
        return dto;
    }

    /**
     * var0 :名称
     * var1 :类别
     * var2 :菜系
     * var3 :口味
     * var4 :形状
     * var5 :烹饪
     * var6 :颜色
     * var7 :原料
     * var8 :单份定价
     * var9 :单份克重
     * var10 :需求数
     * var11 :备注
     */
    public String importExcel(List<PageData> listPd)
    {
        String result = "";
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < listPd.size(); i++)
        { //单份定价  单份克重 需求数 的长度都必须小于7,否则返回

            if (listPd.get(i).getString("var8").length() > 7 || listPd.get(i).getString("var9").length() > 7
                || listPd.get(i).getString("var10").length() > 7)
            {
                result +=
                    "[" + listPd.get(i).getString("var0").trim() + "] 的(单份定价)或(单份克重)或(需求数)的长度大于于7,导入失败。";
                continue;
            }
            Product p = new Product();
            p.setId(UUID.randomUUID().toString());
            ProductDto productDto = new ProductDto();
            productDto.setName(listPd.get(i).getString("var0").trim());
            List<ProductDto> listDto = findListByPage(productDto, null);

            if (!CollectionUtils.isEmpty(listDto))
            {
                //成品菜名称如果重复，则返回;
                result += "  [" + listPd.get(i).getString("var0").trim() + "]名称已经存在,导入失败。";
                continue;
            }
            p.setName(listPd.get(i).getString("var0").trim());
            //类别
            if (!StringUtils.isEmpty(listPd.get(i).getString("var1")))
            {
                ProductCategoryDto category =
                    categoryService.findByName(listPd.get(i).getString("var1").trim());
                if (category == null)
                { //如果类别为空,需要重新创建一个类别
                    ProductCategoryDto productCategoryDto = new ProductCategoryDto();
                    productCategoryDto.setId(UUID.randomUUID().toString());
                    productCategoryDto.setName(listPd.get(i).getString("var1").trim());
                    categoryService.add(productCategoryDto);
                    p.setProductCategoryId(productCategoryDto.getId());
                }
                else
                {
                    p.setProductCategoryId(category.getId());
                }
            }
            else
            {
                return "类别不能为空";
            }
            //菜系
            if (!StringUtils.isEmpty(listPd.get(i).getString("var2")))
            {
                ProductStyleDto style = styleService.findByName(listPd.get(i).getString("var2").trim());
                if (style == null)
                { //如果菜系为空,需要重新创建一个菜系
                    ProductStyleDto productStyleDto = new ProductStyleDto();
                    productStyleDto.setId(UUID.randomUUID().toString());
                    productStyleDto.setName(listPd.get(i).getString("var2").trim());
                    styleService.add(productStyleDto);
                    p.setProductStyleId(productStyleDto.getId());
                }
                else
                {
                    p.setProductStyleId(style.getId());
                }
            }
            else
            {
                return "菜系不能为空";
            }
            //口味
            if (!StringUtils.isEmpty(listPd.get(i).getString("var3")))
            {
                ProductTasteDto taste = tasteService.findByName(listPd.get(i).getString("var3").trim());
                if (taste == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductTasteDto productTasteDto = new ProductTasteDto();
                    productTasteDto.setId(UUID.randomUUID().toString());
                    productTasteDto.setName(listPd.get(i).getString("var3").trim());
                    tasteService.add(productTasteDto);
                    p.setProductTasteId(productTasteDto.getId());
                }
                else
                {
                    p.setProductTasteId(taste.getId());
                }
            }
            else
            {
                return "口味不能为空";
            }
            //形状
            if (!StringUtils.isEmpty(listPd.get(i).getString("var4")))
            {
                ProductShapeDto shape = shapeService.findByName(listPd.get(i).getString("var4").trim());
                if (shape == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductShapeDto productShapeDto = new ProductShapeDto();
                    productShapeDto.setId(UUID.randomUUID().toString());
                    productShapeDto.setName(listPd.get(i).getString("var4").trim());
                    shapeService.add(productShapeDto);
                    p.setProductShapeId(productShapeDto.getId());
                }
                else
                {
                    p.setProductShapeId(shape.getId());
                }
            }
            else
            {
                return "形状不能为空";
            }
            //烹饪
            if (!StringUtils.isEmpty(listPd.get(i).getString("var5")))
            {
                ProductCuisineDto cuisine = cuisineService.findByName(listPd.get(i).getString("var5").trim());
                if (cuisine == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductCuisineDto productCuisineDto = new ProductCuisineDto();
                    productCuisineDto.setId(UUID.randomUUID().toString());
                    productCuisineDto.setName(listPd.get(i).getString("var5").trim());
                    cuisineService.add(productCuisineDto);
                    p.setProductCuisineId(productCuisineDto.getId());
                }
                else
                {
                    p.setProductCuisineId(cuisine.getId());
                }
            }
            else
            {
                return "烹饪方式不能为空";
            }
            //颜色
            if (!StringUtils.isEmpty(listPd.get(i).getString("var6")))
            {
                ProductColorDto color = colorService.findByName(listPd.get(i).getString("var6").trim());
                if (color == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductColorDto productColorDto = new ProductColorDto();
                    productColorDto.setId(UUID.randomUUID().toString());
                    productColorDto.setName(listPd.get(i).getString("var6").trim());
                    colorService.add(productColorDto);
                    p.setProductColorId(productColorDto.getId());
                }
                else
                {
                    p.setProductColorId(color.getId());
                }
            }
            else
            {
                return "颜色不能为空";
            }
            //原料
            if (!StringUtils.isEmpty(listPd.get(i).getString("var7")))
            {
                if (listPd.get(i).getString("var7").contains(","))
                {
                    String[] strarray = listPd.get(i).getString("var7").trim().split(",");
                    String k = null;
                    Long singleCost = 0L;
                    for (int j = 0; j < strarray.length; j++)
                    {
                        k = (strarray[j]).toString();
                        //如果原料不存在 则导入失败
                        boolean isExist = materialIsExist(k.trim());
                        if (isExist == true)
                        {
                            saveProductMaterial(p, k);
                            MaterialDto material = materialService.findByName(k);
                            if (material != null && material.getStat() == 1)
                            {
                                singleCost += material.getCost();
                            }
                            p.setSingleCost(singleCost);
                        }
                        else
                        {
                            result +=
                                "  [" + listPd.get(i).getString("var0").trim() + "]的原料:(" + k.trim()
                                    + ")不存在,无法导入。";
                            continue;
                        }
                    }
                }
                else
                {
                    //如果原料不存在 则导入失败
                    boolean isExist = materialIsExist(listPd.get(i).getString("var7").trim());
                    if (isExist == true)
                    {
                        //添加原料
                        saveProductMaterial(p, listPd.get(i).getString("var7").trim());
                    }
                    else
                    {
                        result +=
                            "  [" + listPd.get(i).getString("var0").trim() + "]的原料:("
                                + listPd.get(i).getString("var7").trim() + ")不存在,无法导入。";
                        continue;
                    }
                }
            }
            //单份定价
            if (!StringUtils.isEmpty(listPd.get(i).getString("var8")))
            {
                p.setEachPricing(Double.valueOf(listPd.get(i).getString("var8").trim()).longValue());
            }
            //单份克重
            if (!StringUtils.isEmpty(listPd.get(i).getString("var9")))
            {
                p.setSingleWeight(Integer.valueOf(listPd.get(i).getString("var9").trim()));
            }
            //需求数
            if (!StringUtils.isEmpty(listPd.get(i).getString("var10")))
            {
                p.setDemandNumber(Integer.valueOf(listPd.get(i).getString("var10").trim()));
            }
            //备注

            if (!StringUtils.isEmpty(listPd.get(i).getString("var11")))
            {
                p.setRemark(listPd.get(i).getString("var11").trim());
            }
            //总成本
            if (!StringUtils.isEmpty(p.getSingleCost()) && !StringUtils.isEmpty(p.getDemandNumber()))
            {
                BigDecimal b1 = new BigDecimal(Double.valueOf(p.getSingleCost()));
                BigDecimal b2 = new BigDecimal(Double.valueOf(p.getDemandNumber()));
                //放入总成本
                p.setTotalCost(b1.multiply(b2).longValue());
            }
            list.add(p);
        }
        if (!CollectionUtils.isEmpty(list))
        {
            for (Product p : list)
            {
                if (p.getSingleCost() == null || p.getTotalCost() == null)
                {
                    continue;
                }
                //查找6种不同类型的成分是否已经存在;
                List<Product> list2 = productDao.findDistinctComponents(p, null);
                if (!CollectionUtils.isEmpty(list2))
                {//如果存在重复的配菜,则直接退出for循环
                    result += " [" + p.getName().trim() + "]的配菜组合已经存在,无法导入。";
                    continue;
                }
                p.setStat(DataStatus.ENABLED);
                p.setCreateTime(new Date());
                p.setLastUpdateTime(new Date());
                productDao.insert(p);

            }

        }
        if (StringUtils.isEmpty(result))
        {
            result = "导入成品菜成功!";
        }
        return result;
    }

    public void saveProductMaterial(Product p, String materialName)
    {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setName(materialName);
        List<MaterialDto> materialList = materialService.findListByName(materialDto);
        if (!CollectionUtils.isEmpty(materialList))
        {
            for (MaterialDto dto : materialList)
            {
                ProductMaterialDto productMaterialDto = new ProductMaterialDto();
                productMaterialDto.setId(UUID.randomUUID().toString());
                productMaterialDto.setProductId(p.getId());
                productMaterialDto.setMaterialId(dto.getId());
                productMaterialService.add(productMaterialDto);
                p.setSingleCost(dto.getCost());

            }
        }

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findAll(com.ssic.cookbook.manager.dto.ProductDto)   
    */
    public List<ProductDto> findAll(ProductDto productDto)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        List<ProductDto> listDto = new ArrayList<ProductDto>();
        List<Product> list = productDao.findForList(product, null);
        if (!CollectionUtils.isEmpty(list))
        {

            for (Product p : list)
            {
                ProductDto dto = new ProductDto();
                BeanUtils.copyProperties(p, dto);
                dto.setSingleCost(p.getSingleCost().doubleValue() / 100);
                dto.setTotalCost(Math.round(p.getTotalCost().doubleValue() / 100 * 100) / 100.0);
                listDto.add(dto);
            }
            if (!CollectionUtils.isEmpty(listDto))
            {
                for (ProductDto dto : listDto)
                {
                    //重新组装成品菜dto对象;
                    setProductValues(dto);
                }
            }
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findAllRedis(com.ssic.cookbook.manager.dto.ProductDto)   
    */
    @Cacheable(value = "default", key = "'cookbook.manager.dto.findAllRedis'")
    public List<ProductDto> findAllRedis()
    {
        Product product = new Product();
        List<ProductDto> listDto = new ArrayList<ProductDto>();
        List<Product> list = productDao.findForList(product, null);
        if (!CollectionUtils.isEmpty(list))
        {
            for (Product p : list)
            {
                ProductDto dto = new ProductDto();
                BeanUtils.copyProperties(p, dto);
                dto.setSingleCost(p.getSingleCost().doubleValue() / 100);
                dto.setTotalCost(Math.round(p.getTotalCost().doubleValue() / 100 * 100) / 100.0);
                listDto.add(dto);
            }
            if (!CollectionUtils.isEmpty(listDto))
            {
                for (ProductDto dto : listDto)
                {
                    //重新组装成品菜dto对象;
                    setProductValues(dto);
                }
            }
            return listDto;
        }
        return listDto;
    }

    public int findCount(ProductDto productDto, Date dataStart, Date dataEnd, String materialCompare)
    {
        // TODO Auto-generated method stub
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return productDao.findCount(product, dataStart, dataEnd, materialCompare);
    }

    public List<ProductDto> findListByPage(ProductDto productDto, LimitPageDto limitPageDto, Date dateStart,
        Date dataEnd, String materialCompare)
    {
        // TODO Auto-generated method stub
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        List<ProductDto> listDto = new ArrayList<ProductDto>();
        List<Product> list =
            productDao.findForList(product, limitPageDto, dateStart, dataEnd, materialCompare);
        if (!CollectionUtils.isEmpty(list))
        {

            for (Product p : list)
            {
                ProductDto dto = new ProductDto();
                BeanUtils.copyProperties(p, dto);
                dto.setSingleCost(p.getSingleCost().doubleValue() / 100);
                dto.setTotalCost(Math.round(p.getTotalCost().doubleValue() / 100 * 100) / 100.0);
                listDto.add(dto);
            }
            if (!CollectionUtils.isEmpty(listDto))
            {
                for (ProductDto dto : listDto)
                {
                    //重新组装成品菜dto对象;
                    setProductValues(dto);
                }
            }
            return listDto;
        }
        return listDto;
    }

    public boolean materialIsExist(String materialName)
    {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setName(materialName);
        List<MaterialDto> list = materialService.findListByName(materialDto);
        if (!CollectionUtils.isEmpty(list) && list.size() > 0)
        {
            return true;
        }
        return false;
    }

}
