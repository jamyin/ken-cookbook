package com.ssic.cookbook.manager.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.Const;
import com.ssic.cookbook.admin.util.Jurisdiction;
import com.ssic.cookbook.admin.util.ObjectExcelView;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.service.IParamConfigService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.PageFunction;

@Controller
@RequestMapping("/dataReportController")
public class DataReportController extends BaseController{

	String menuUrl = "dataReportController/excel.do"; //菜单地址(权限用)
	@Autowired
	private IProductService productService;
	@Autowired
	private PageFunction pageFunction;
	@Autowired
	private IParamConfigService paramConfigService;
	
	@RequestMapping("/findDataReport")
	public ModelAndView findDataReport(Page page) throws ParseException{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

        ProductDto productDto = new ProductDto();		
    	String drname = pd.getString("name");
		if(!StringUtils.isEmpty(drname)){
			productDto.setName(drname);
		}
		//创建时间起始
		String createTimeStart = pd.getString("createTimeStart");
		String createTimeEnd = pd.getString("createTimeEnd");
		SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStart=null;
		Date dateEnd =null;
		if(!StringUtils.isEmpty(createTimeStart)){
			dateStart = sfm.parse(createTimeStart);
		}
		if(!StringUtils.isEmpty(createTimeEnd)){
	        dateEnd = sfm.parse(createTimeEnd);
		}
		String singleWeight = pd.getString("singleWeight");
		if(!StringUtils.isEmpty(singleWeight)){
			productDto.setSingleWeight(Integer.parseInt(singleWeight));
		}		
		String materialCompare = pd.getString("materialCompare");
		
		int count = productService.findCount(productDto,dateStart,dateEnd,materialCompare);
		//获取分页的开始结束
		LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);		
		List<ProductDto> listproduct = productService.findListByPage(productDto, limitPageDto,dateStart,dateEnd,materialCompare);
		
		//获取比较符号
		ParamConfigDto paramConfigDto = new ParamConfigDto();
		paramConfigDto.setParamType(CookbookFields.Material_Compare);
		List<ParamConfigDto> listparam = paramConfigService.findForList(paramConfigDto);
		mv.addObject("listparam", listparam);
		page.setPd(pd);
		//设置page信息
		pageFunction.setLimitPage(listproduct.size(), count, limitPageDto.getTotalPage(), page);
		mv.addObject("listproduct", listproduct);
		mv.setViewName("cookbook/dataReport/dataReport_list");
		mv.addObject("pd",pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if(Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				
				titles.add("成品创建时间"); 		//1
				titles.add("成品菜名称");  		//2
				titles.add("所属类别名称");			//3
				titles.add("菜系名称");			//4
				titles.add("单份克重");			//5
				titles.add("单份样本");			//6
				titles.add("总成本");		//7
				dataMap.put("titles", titles);
				ProductDto productDto = new ProductDto();		
		    	String drname = pd.getString("name");
				if(!StringUtils.isEmpty(drname)){
					productDto.setName(drname);
				}
				//创建时间起始
				String createTimeStart = pd.getString("createTimeStart");
				String createTimeEnd = pd.getString("createTimeEnd");
				SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd");
				Date dateStart=null;
				Date dateEnd =null;
				if(!StringUtils.isEmpty(createTimeStart)){
					dateStart = sfm.parse(createTimeStart);
				}
				if(!StringUtils.isEmpty(createTimeEnd)){
			        dateEnd = sfm.parse(createTimeEnd);
				}
				String singleWeight = pd.getString("singleWeight");
				if(!StringUtils.isEmpty(singleWeight)){
					productDto.setSingleWeight(Integer.parseInt(singleWeight));
				}		
				String materialCompare = pd.getString("materialCompare");
				List<ProductDto> listproduct = productService.findListByPage(productDto,null,dateStart,dateEnd,materialCompare);
                List<PageData> varList = new ArrayList<PageData>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                if(!CollectionUtils.isEmpty(listproduct)){
                	for(ProductDto pdto : listproduct){
                		PageData pageData = new PageData();
                        pageData.put("var1", sdf.format(pdto.getCreateTime()));
                        pageData.put("var2", pdto.getName());
                        pageData.put("var3", pdto.getProductCategoryName());
                        pageData.put("var4", pdto.getProductStyleName());
                        pageData.put("var5", pdto.getSingleWeight());
                        pageData.put("var6", pdto.getSingleCost());
                        pageData.put("var7", pdto.getTotalCost());
                        varList.add(pageData);
                	}
                }
				
				//List<PageData> expList = materialService.expExcelFindAll(pd);
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView();					//执行excel操作
				mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	
	}
}
