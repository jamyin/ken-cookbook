/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.Date;
import java.util.List;

import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.pojo.Product;

/**		
 * <p>Title: IProductService </p>
 * <p>Description: 成品菜Service</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:24:31	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:24:31</p>
 * <p>修改备注：</p>
 */
public interface IProductService
{

    /**     
     * findListByPage：查找所有的成品菜
     * @param productDto
     * @param limitPageDto 
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22	 
     */
    List<ProductDto> findListByPage(ProductDto productDto, LimitPageDto limitPageDto);
    
    List<ProductDto> findListByPage(ProductDto productDto, LimitPageDto limitPageDto, Date dateStart,Date dataEnd,String materialCompare);

    /**     
     * findById：根据成品菜id查找对应的成品菜
     * @param productId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    ProductDto findById(String productId);
    
    /**     
     * findByName：根据成品菜名称查找对应的成品菜
     * @param productId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    ProductDto findByName(String productName);

    
    /**     
     * findCount：查找菜品数量
     * @param productDto
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午1:12:21	 
     */
    int findCount(ProductDto productDto);
    
    int findCount(ProductDto productDto,Date dataStart,Date dataEnd,String materialCompare);
    
    /**     
     * add：一句话描述方法功能
     * @param productDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductDto productDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductDto productDto);
    
    /**     
     * editForUpload：图片上传编辑
     * @param productDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void editForUpload(ProductDto productDto);
    
    /**     
     * delete：一句话描述方法功能
     * @param productDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductDto productDto);
    
    /**
     * @author l
     * @desc 导出成品菜数据
     * @param materialDto
     * @return
     * @throws Exception 
     */
    List<PageData> expExcelFindAll(PageData pd) throws Exception;
    
    /**
     * @author 刘博
     * @desc 查找6个成品菜的6个成分(类别,菜系,口味,形状,颜色,烹饪)中是否有重复
     * @param Product
     * @return
     * @throws Exception 
     */
    public List<ProductDto> findDistinctComponents(ProductDto productDto, LimitPageDto limitPageDto);

    
    /**     
     * importExcel： 导入excel到数据库
     * @param listPd
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月28日 下午3:56:39	 
     */
    String importExcel(List<PageData> listPd);

    
    /**     
     * findAll：查找所有成品菜
     * @param productDto
     * @return
     * @exception	
     * @author 刘博
     * @date 2016年1月8日 下午2:26:58	 
     */
    List<ProductDto> findAll(ProductDto productDto);
    /**     
     * findAll：查找所有成品菜
     * @param productDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2016年1月8日 下午2:26:58     
     */
    List<ProductDto> findAllRedis();

    
    /**     
     * materialIsExist：一句话描述方法功能
     * @param trim
     * @return
     * @exception	
     * @author Administrator
     * @date 2016年1月15日 下午3:59:07	 
     */
    boolean materialIsExist(String trim);

    
    /**     
     * saveProductMaterial：一句话描述方法功能
     * @param p
     * @param k
     * @exception	
     * @author Administrator
     * @date 2016年1月15日 下午3:59:14	 
     */
    void saveProductMaterial(Product p, String k);

   

    

}
