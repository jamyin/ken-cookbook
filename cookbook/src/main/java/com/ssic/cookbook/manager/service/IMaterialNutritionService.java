/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;
import com.ssic.cookbook.manager.dto.MaterialNutritionDto;

/**		
 * <p>Title: IMaterialNutritionService </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月22日 下午5:22:49	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月22日 下午5:22:49</p>
 * <p>修改备注：</p>
 */
public interface IMaterialNutritionService
{
    public List<MaterialNutritionDto> findForList(MaterialNutritionDto materialNutritionDto);
    
    public List<MaterialNutritionDto> findByMaterialId(String materialId);
    
    public MaterialNutritionDto findById(String id);
    
    public void updateBN(MaterialNutritionDto materialNutritionDto);
    
    public void insertBN(MaterialNutritionDto materialNutritionDto);
    
    public void deleteMN(String mid);
    
}

