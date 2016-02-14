package com.ssic.cookbook.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssic.cookbook.manager.dto.MaterialBigCategoryDto;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.service.IMaterialBigCategoryService;
import com.ssic.cookbook.manager.service.IMaterialService;

@Controller
@RequestMapping("/materialBigCategoryController")
public class MaterialBigCategoryController {

	@Autowired
	private IMaterialService materialService;
	@Autowired
	private IMaterialBigCategoryService materialBigCategoryService;
	
	//查询这个大类被哪个原料使用
	@RequestMapping("/findMByBigId")
	@ResponseBody
	public List<MaterialDto> findMByBigId(String bigId){
		MaterialDto materialDto = new MaterialDto();
        materialDto.setBigCategoryId(bigId);
		List<MaterialDto> list = materialService.findListByPage(materialDto);
		return list;
	}
	
	//查询这个大类名称是否已经存在
	@RequestMapping("/hasBigName")
	@ResponseBody
	public List<MaterialBigCategoryDto> hasBigName(String bigName){
		MaterialBigCategoryDto materialBigCategoryDto = new MaterialBigCategoryDto();
		materialBigCategoryDto.setName(bigName);
		List<MaterialBigCategoryDto> list = materialBigCategoryService.findForList(materialBigCategoryDto);
		return list;
	}
	
	
}
