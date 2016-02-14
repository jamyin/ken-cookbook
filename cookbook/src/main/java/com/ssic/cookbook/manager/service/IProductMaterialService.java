/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductMaterialDto;

/**		
 * <p>Title: IProductMaterialService </p>
 * <p>Description: 成品菜-原料Service</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月24日 下午3:30:01	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月24日 下午3:30:01</p>
 * <p>修改备注：</p>
 */
public interface IProductMaterialService
{

    /**     
     * findListByPage：查找所有的成品菜原料关系
     * @param ProductMaterialDto
     * @param limitPageDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public List<ProductMaterialDto> findListByPage(ProductMaterialDto productMaterialDto,
        LimitPageDto limitPageDto);

    /**     
     * add：添加成菜菜原料关系
     * @param ProductMaterialDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductMaterialDto productMaterialDto);

    /**     
     * edit：编辑成菜菜原料关系
     * @param ProductMaterialDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductMaterialDto productMaterialDto);

    /**     
     * delete：删除成菜菜原料关系
     * @param ProductMaterialDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductMaterialDto productMaterialDto);

    /**     
     * findCount：查找成品菜-原料数量
     * @param ProductMaterialDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34  
     */
    public int findCount(ProductMaterialDto productMaterialDto);
}
