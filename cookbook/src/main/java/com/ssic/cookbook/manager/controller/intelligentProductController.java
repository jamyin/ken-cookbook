package com.ssic.cookbook.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.pojo.IndependentFixingsExample;
import com.ssic.cookbook.manager.service.IIntelligentFixingsService;
import com.ssic.cookbook.manager.util.PageFunction;

@Controller
@RequestMapping("/intelligentProductController")
public class intelligentProductController  extends BaseController
{
    @Autowired
    private  IIntelligentFixingsService  intelligentFixingsService;     
    @Autowired
    private PageFunction pageFunction;  
    @RequestMapping("/findAll")
    public ModelAndView findAllProductionMethod(Page page) 
    {
        ModelAndView mv = this.getModelAndView();                                                                                                                                                                                                                                                                        
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);               
        IndependentFixingsExample   example = new  IndependentFixingsExample();
        example.setDistinct(true);
        example.setOrderByClause("desc");
        FixingsResultDto fdto =new FixingsResultDto();
        int count = intelligentFixingsService.findCount();  
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);       
       
        fdto.setStar(limitPageDto.getStar());
        fdto.setEnd(limitPageDto.getEnd());
        //组织搜索条件
        
         String mealName=   pd.getString("productName");
         String totalCostOperator=   pd.getString("chengBen");
         String totalCost=   pd.getString("demandNumber");
         String totalFixedPriceOperator=   pd.getString("dingJia");
         String totalFixedPrice=   pd.getString("singleWeight");
        
        
        if(!StringUtils.isEmpty(mealName)){
            fdto.setMealName(mealName);          
        }
        
        if(!StringUtils.isEmpty(totalCostOperator)){
            fdto.setTotalCostOperator(totalCostOperator);//总成本运算符
        }
        if(!StringUtils.isEmpty(totalCost)){
            fdto.setTotalCost(Integer.valueOf(totalCost));//总成本
        } 
        if(!StringUtils.isEmpty(totalFixedPriceOperator)){
            fdto.setTotalFixedPriceOperator(totalFixedPriceOperator);//总定价运算符
        }
        if(!StringUtils.isEmpty(totalFixedPrice)){          
            fdto.setTotalFixedPrice(Integer.valueOf(totalFixedPrice));//总定价
        }
        //查询配菜名称
       List<FixingsResultDto>  iflist =   intelligentFixingsService.findAllIntelligentProduct(fdto);     
       
       if(iflist.size()<=9){
           count=iflist.size();
           //获取分页的开始结束
           limitPageDto= pageFunction.getLimitPage(page, count);     
         
           fdto.setStar(limitPageDto.getStar());
           fdto.setEnd(limitPageDto.getEnd());
        
       }       
     
        //查询类型名称
        List<FixingsResultDto>  typeList =   intelligentFixingsService.findAllIntelligentProductType();        
        //查询成品菜名称
        List<FixingsResultDto>  nameList =   intelligentFixingsService.findAllIntelligentProductName();    
  
        //设置page信息 
       pageFunction.setLimitPage(iflist.size(), count, limitPageDto.getTotalPage(), page);       
            mv.setViewName("cookbook/intelligentproduct/intelligent_product_list");  
           mv.addObject("iflist", iflist);
            mv.addObject("typeList", typeList);
            mv.addObject("nameList", nameList);
            return mv;                              
    }
    @RequestMapping( "/deleteI")
    @ResponseBody
    public  ModelAndView   deleteIntelligent( String  fixingsName){
        ModelAndView mv = this.getModelAndView();
        FixingsResultDto fdto =new FixingsResultDto();
        fdto.setFixingsName(fixingsName);
        intelligentFixingsService.deleteIntelligent(fdto);
        mv.setViewName("cookbook/intelligentproduct/intelligent_product_list");
        return mv;
    }
    
  

}

