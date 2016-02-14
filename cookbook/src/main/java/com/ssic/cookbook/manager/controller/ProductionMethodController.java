package com.ssic.cookbook.manager.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.Const;
import com.ssic.cookbook.admin.util.Jurisdiction;
import com.ssic.cookbook.admin.util.ObjectExcelView;
import com.ssic.cookbook.admin.util.FileUpload;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductionMethodDto;
import com.ssic.cookbook.manager.pojo.ProductionMethod;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample;
import com.ssic.cookbook.manager.pojo.ProductionMethodExample.Criteria;
import com.ssic.cookbook.manager.service.IProductionMethodService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.PageFunction;

/**
 *      
 * <p>Title: ProductionMethodController </p>
 * <p>Description: 菜谱制作方法</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 高猛华
 * @date 2015年12月18日 上午11:10:29 
 * @version 1.0
 * <p>修改人：Administrator</p>
 * <p>修改时间：2015年12月18日 上午11:10:29</p>
 * <p>修改备注：</p>
 */
@Controller
@RequestMapping("/productionMethod") 
public class ProductionMethodController extends BaseController
{
    
    String menuUrl = "productionMethod/excel.do"; //菜单地址(权限用)
    @Autowired
    private  IProductionMethodService productionMethodService;
    @Autowired
    private PageFunction pageFunction;    
   
 
    @RequestMapping("/findAllProductionMethod")
    public ModelAndView findAllProductionMethod(Page page ) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

            PageData pd = new PageData();
            pd = this.getPageData();
           
            page.setPd(pd);                    
            //查询菜谱制作方法数量
            int count = productionMethodService.findCount();
          
            ProductionMethodDto pmd = new ProductionMethodDto();
          //获取分页的开始结束
            LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);     
           pmd.setProductName(pd.getString("productName"));
           pmd.setStar(limitPageDto.getStar());
           pmd.setEnd(limitPageDto.getEnd());
            List<ProductionMethodDto> pmlist  =  productionMethodService.findAllProductionMethod(pmd);
            if(pmlist.size()<=9){
                count=pmlist.size();
                //获取分页的开始结束
                limitPageDto= pageFunction.getLimitPage(page, count);     
              
               pmd.setStar(limitPageDto.getStar());
               pmd.setEnd(limitPageDto.getEnd());
             
            }            
            List<String> pmn= new ArrayList<String>() ;
            //设置page信息            
            pageFunction.setLimitPage(pmlist.size(), count, limitPageDto.getTotalPage(), page);                  
          //组装mv对象并返回到前台页面
            mv.setViewName("cookbook/productmethod/product_method_list");   
            mv.addObject("pmn", pmn);
           mv.addObject("pmlist",pmlist);
            return mv;                              
    }
 
    @RequestMapping( "/deleteP")
    @ResponseBody
    public  ModelAndView   deleteProductionMethod( String  id){
        ModelAndView mv = this.getModelAndView();
        ProductionMethodDto pmd = new ProductionMethodDto();
        pmd.setId(id);
        productionMethodService.deleteProductionMethod(pmd);
        mv.setViewName("cookbook/productmethod/product_method_list");
        return mv;
    }
    
   /**
    * 
    * insterProductionMethod：跳转到新增菜谱制作方法页面
    * @return
    * @exception    
    * @author Administrator
    * @date 2015年12月24日 下午1:56:10
    */
    @RequestMapping("/add")    
    public ModelAndView insterProductionMethod(ProductionMethod productionMethod){       
        ModelAndView mv = this.getModelAndView();    
         mv.setViewName("cookbook/productmethod/product_method_add");     
         mv.addObject("productionMethod", productionMethod);
        return mv;                            
    }

    
    @RequestMapping(value="/save")
    public ModelAndView saveProductionMethod(  MultipartFile imgUrl, String  productionName, String  productionMethod) throws Exception{      
        ModelAndView mv = this.getModelAndView();      
        ProductionMethod  pm= new ProductionMethod();   
        pm.setProductionName(productionName);
        pm.setProductionMethod(productionMethod);
        pm.setStat(1);
        String filePath = Const.IMAGEPATH;                                //图片上传路径
        if (!(new File(filePath).isDirectory())) {
            new File(filePath).mkdir();           
        }
        String fileName =  FileUpload.fileUp(imgUrl, filePath,UUID.randomUUID().toString()); //执行上传
        
        pm.setImgUrl(filePath+fileName);
         productionMethodService.insertProductionMethod(pm);
       //  return new ModelAndView("redirect:/productionMethod/findAllProductionMethod");
      // mv.setViewName("cookbook/productmethod/product_method_list");   
      // return mv;           
         mv.setViewName("save_result");
         return mv;
    }

    @RequestMapping("/update")                               
    @ResponseBody
    public  ModelAndView  updateProductionMethod(@RequestParam("id") String  id ){
        ModelAndView mv = this.getModelAndView();        
        ProductionMethod productionMethod=   productionMethodService.findProductionMethodById(id);
        mv.setViewName("cookbook/productmethod/product_method_edit");
        mv.addObject("pd", productionMethod);
       
        return mv;
    }     
    @RequestMapping("/edit")    
    @ResponseBody
    public ModelAndView edit(@ModelAttribute("productionMethod") ProductionMethod  productionMethod ){     
        ModelAndView mv = this.getModelAndView();            
        productionMethodService.updateProductionMethod(productionMethod);        
     /*   mv.setViewName("cookbook/productmethod/product_method_list");      
        return mv;    */  
        mv.setViewName("save_result");
        return mv;
    }
    
    
    /**
     * @author pengcheng.yang
     * @desc 导出原料信息到EXCEL
     * @return  ModelAndView
     * @date 2015-12-28
     */
    @RequestMapping(value="/excel")
    public ModelAndView exportExcel(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try{
            if(Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
                Map<String,Object> dataMap = new HashMap<String,Object>();
                List<String> titles = new ArrayList<String>();
                
                titles.add("制作名称");         // 1、制作名称
                titles.add("制作方法");     // 2、制作方法
                dataMap.put("titles", titles);
                
                List<PageData> expList = productionMethodService.expProMetExcelFindAll(pd);
                List<PageData> varList = new ArrayList<PageData>();
                for(int i=0;i<expList.size();i++){
                    PageData vpd = new PageData();
                    vpd.put("var1", expList.get(i).getString("productionName"));        // 1、制作名称
                    vpd.put("var2", expList.get(i).getString("productionMethod"));      // 2、制作方法
                    varList.add(vpd);
                }
                dataMap.put("varList", varList);
                ObjectExcelView erv = new ObjectExcelView();                    //执行excel操作
                mv = new ModelAndView(erv,dataMap);
            }
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }
    

}

