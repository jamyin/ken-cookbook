/**
 * 
 */
package com.ssic.cookbook.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.service.IMaterialNutritionService;
import com.ssic.cookbook.manager.service.IParamConfigService;

/**		
 * <p>Title: NutritionController </p>
 * <p>Description: 营养controller</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月24日 上午11:44:36	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月24日 上午11:44:36</p>
 * <p>修改备注：</p>
 */
@Controller
@RequestMapping("/nutritionController")
public class NutritionController extends BaseController
{
    @Autowired
    private IMaterialNutritionService materialNutritionService;
    @Autowired
    private IParamConfigService paramConfigService;
    
    @RequestMapping("/hasNutrName")
    @ResponseBody
    public List<ParamConfigDto> hasNutrName(String nutrName){
    	ParamConfigDto paramConfigDto = new ParamConfigDto();
    	paramConfigDto.setParamName(nutrName);
    	List<ParamConfigDto> list = paramConfigService.findByList(paramConfigDto);
    	return list;
    }
    
    
    
}
