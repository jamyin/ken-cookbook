package com.ssic.cookbook.manager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.CategoryClassDto;
import com.ssic.cookbook.manager.dto.CategoryClassProductDto;
import com.ssic.cookbook.manager.dto.CategoryDto;
import com.ssic.cookbook.manager.dto.ColorDto;
import com.ssic.cookbook.manager.dto.CookVo;
import com.ssic.cookbook.manager.dto.CuisineDto;
import com.ssic.cookbook.manager.dto.FixingsMasterDto;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.FixingsResultSwDto;
import com.ssic.cookbook.manager.dto.FixingsVO;
import com.ssic.cookbook.manager.dto.IndependentFixingsDto;
import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.MaterialBigCategoryDto;
import com.ssic.cookbook.manager.dto.NutritionsDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.dto.ShapeDto;
import com.ssic.cookbook.manager.dto.SmartNutritionDto;
import com.ssic.cookbook.manager.dto.SmartResultDto;
import com.ssic.cookbook.manager.dto.StyleDto;
import com.ssic.cookbook.manager.dto.TasteDto;
import com.ssic.cookbook.manager.pojo.IndependentFixingsExample;
import com.ssic.cookbook.manager.service.DIYBookService;
import com.ssic.cookbook.manager.service.ICCProductService;
import com.ssic.cookbook.manager.service.ICategoryClassService;
import com.ssic.cookbook.manager.service.IFixingsMasterService;
import com.ssic.cookbook.manager.service.IFixingsResultService;
import com.ssic.cookbook.manager.service.IIndependentFixingsService;
import com.ssic.cookbook.manager.service.IIntelligentFixingsService;
import com.ssic.cookbook.manager.service.IMaterialBigCategoryService;
import com.ssic.cookbook.manager.service.INutritionService;
import com.ssic.cookbook.manager.service.IParamConfigService;
import com.ssic.cookbook.manager.service.IProductCategoryService;
import com.ssic.cookbook.manager.service.IProductColorService;
import com.ssic.cookbook.manager.service.IProductCuisineService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.IProductShapeService;
import com.ssic.cookbook.manager.service.IProductStyleService;
import com.ssic.cookbook.manager.service.IProductTasteService;
import com.ssic.cookbook.manager.util.PageFunction;
import com.ssic.cookbook.manager.util.Tools;
import com.ssic.util.DateUtils;
import com.ssic.util.UUIDGenerator;
import com.ssic.util.constants.DataStatus;
import com.ssic.util.model.Response;
/**
 * <p>Title: SmartController </p>
 * <p>Description: 配菜</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 张亚伟	
 * @date 2015年12月18日 上午9:47:39	
 * @version 1.0
 * <p>修改人：张亚伟</p>
 * <p>修改时间：2015年12月18日 上午9:47:39</p>
 * <p>修改备注：</p>
 */
@Controller
@RequestMapping("/smart")
public class SmartController extends BaseController {

	@Autowired
	private IProductCategoryService iProductCategoryService;
	
	@Autowired
	private IFixingsResultService iFixingsResultService;
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private IMaterialBigCategoryService materialBigCategoryService;
	
	@Autowired
	private IIndependentFixingsService iFixingsService;
	
	@Autowired
	private IFixingsMasterService fixingsMasterService;
	
	@Autowired
	private ICategoryClassService categoryClassService;
	
	@Autowired
	private ICCProductService cCProductService;
	
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
    private INutritionService iNutritionService;
    
    @Autowired
    private IParamConfigService iParamConfigService;
    
    @Autowired
    private IIntelligentFixingsService iIntelligentFixingsService;
    
    @Autowired
    private PageFunction pageFunction;  
    
/*    @Autowired
    private MenuGenService menuGenService;  */

    
	
    @Autowired
    private DIYBookService diyBookService;
    /**
	 * menuList：菜单列表
	 * @param page
	 * @return
	 * @exception	
	 * @author 张亚伟
	 * @date 2015年12月18日 上午9:48:02
	 */
	@RequestMapping("/menuList.do")
	public ModelAndView menuList(Page page) {
	    ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();

        //配菜名称
        String menuName = pd.getString("menuName");
        System.out.println("配菜名称:" + menuName);
        //配菜总成本运算符
        String totalcostOperators = pd.getString("totalcostOperators");
        System.out.println("配菜总成本运算符:" + totalcostOperators);
        //配菜总成本价格
        String totalcostPrice = pd.getString("totalcostPrice");
        System.out.println("配菜总成本价格:" + totalcostPrice);
        //配菜总定价运算符
        String totalPricingOperators = pd.getString("totalPricingOperators");
        System.out.println("配菜总定价运算符:" + totalPricingOperators);
        //配菜总定价价格
        String totalPricingPrice = pd.getString("totalPricingPrice");
        System.out.println("配菜总定价价格:" + totalPricingPrice);
    
   
        page.setPd(pd);
        IndependentFixingsExample example = new IndependentFixingsExample();
        example.setDistinct(true);
        example.setOrderByClause("desc");
        int count = iIntelligentFixingsService.findCount();
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);

        //组织搜索条件
        FixingsResultDto fdto = new FixingsResultDto();
        String mealName = pd.getString("productName");
        String totalCostOperator = pd.getString("chengBen");
        String totalCost = pd.getString("demandNumber");
        String totalFixedPriceOperator = pd.getString("dingJia");
        String totalFixedPrice = pd.getString("singleWeight");

        if (!StringUtils.isEmpty(mealName))
        {
            fdto.setMealName(mealName);
        }

        if (!StringUtils.isEmpty(totalCostOperator))
        {
            fdto.setTotalCostOperator(totalCostOperator);//总成本运算符
        }
        if (!StringUtils.isEmpty(totalCost))
        {
            fdto.setTotalCost(Integer.valueOf(totalCost));//总成本
        }
        if (!StringUtils.isEmpty(totalFixedPriceOperator))
        {
            fdto.setTotalFixedPriceOperator(totalFixedPriceOperator);//总定价运算符
        }
        if (!StringUtils.isEmpty(totalFixedPrice))
        {
            fdto.setTotalFixedPrice(Integer.valueOf(totalFixedPrice));//总定价
        }
        //查询配菜名称
        List<FixingsResultDto> iflist = iIntelligentFixingsService.findAllIntelligentProduct(fdto);
        //查询类型名称
        List<FixingsResultDto> typeList = iIntelligentFixingsService.findAllIntelligentProductType();
        //查询成品菜名称
        List<FixingsResultDto> nameList = iIntelligentFixingsService.findAllIntelligentProductName();

        //设置page信息
        pageFunction.setLimitPage(iflist.size(), count, limitPageDto.getTotalPage(), page);
        mv.setViewName("cookbook/smart/menuList");
        mv.addObject("iflist", iflist);
        mv.addObject("typeList", typeList);
        mv.addObject("nameList", nameList);
        return mv;
	}
	
	/**
	 * menuTypeShow：跳转到新增菜谱类别选择页面
	 * @return
	 * @exception	
	 * @author 张亚伟
	 * @date 2015年12月21日 下午3:48:21
	 */
	@RequestMapping("/menuTypeShow.do")
	public ModelAndView menuTypeShow() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("cookbook/smart/menuTypeSelect");
		return mv;
	}
	
	/**
	 * menuCustomShow：跳转到自选菜谱页面
	 * @return
	 * @exception	
	 * @author 张亚伟
	 * @date 2015年12月21日 下午3:48:44
	 */
	@RequestMapping("/menuStyleShow.do")
	public ModelAndView menuStyleShow(Page page) {
		PageData pd=this.getPageData();
		
		//获取选菜类型（1：自主配菜，2：智能配菜，3：指定套餐）
		String type=pd.getString("type");
		
		//获取配菜名称
		String menuName=pd.getString("menuName");
		ModelAndView mv = this.getModelAndView();
		
		FixingsMasterDto dto = new FixingsMasterDto();
		String masterID = UUIDGenerator.getUUID();
		dto.setId(masterID);
		dto.setFixingsType(Integer.parseInt(type));
		dto.setFixingsName(menuName);
		if (type.equals("1")) {
			fixingsMasterService.add(dto);
			//获取所有大类
			List<MaterialBigCategoryDto> menuTypelist = materialBigCategoryService.findForList(new MaterialBigCategoryDto());
			//获取所有成菜
			//List<ProductDto> productList = productService.findListByPage(new ProductDto(), null);
			List<ProductDto> productList = productService.findAllRedis();
			mv.setViewName("cookbook/smart/menuCustom");
			mv.addObject("menuTypelist", menuTypelist);
			mv.addObject("masterID", masterID);
			mv.addObject("productList", productList);
		}else if (type.equals("2")) {
			//获取所有大类
			//List<MaterialBigCategoryDto> menuTypelist = materialBigCategoryService.findForList(new MaterialBigCategoryDto());
			//类别集合
	        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
	        List<ProductCategoryDto> listCategory = categoryService.findListByPage(productCategoryDto, null);
	        //菜系集合
	        ProductStyleDto productStyleDto = new ProductStyleDto();
	        List<ProductStyleDto> listStyle = styleService.findListByPage(productStyleDto, null);
	        //口味集合
	        ProductTasteDto productTasteDto = new ProductTasteDto();
	        List<ProductTasteDto> listTaste = tasteService.findListByPage(productTasteDto, null);
	        //颜色集合
	        ProductColorDto productColorDto = new ProductColorDto();
	        List<ProductColorDto> listColor = colorService.findListByPage(productColorDto, null);
	        //形状集合
	        ProductShapeDto productShapeDto = new ProductShapeDto();
	        List<ProductShapeDto> listShape = shapeService.findListByPage(productShapeDto, null);
	        //烹饪集合
	        ProductCuisineDto productCuisineDto = new ProductCuisineDto();
	        List<ProductCuisineDto> listCuisine = cuisineService.findListByPage(productCuisineDto, null);
	        //营养集合
	        ParamConfigDto paramConfigDto = new ParamConfigDto();
	        paramConfigDto.setParamType("material_nutrition");
	        List<ParamConfigDto> listNutrition = iParamConfigService.findForList(paramConfigDto);
			mv.setViewName("cookbook/smart/menuSmart");
			//mv.addObject("menuTypelist", menuTypelist);
			mv.addObject("listCategory", listCategory);
	        mv.addObject("listStyle", listStyle);
	        mv.addObject("listTaste", listTaste);
	        mv.addObject("listColor", listColor);
	        mv.addObject("listShape", listShape);
	        mv.addObject("listCuisine", listCuisine);
	        mv.addObject("listNutrition", listNutrition);
		}else{
		}
		return mv;
	}
	
	/**
	 * 增加智能配菜
	 * @author YIn
	 * @time:2015年12月30日 下午5:32:04
	 * @param dto
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/addSmart.do")
    @ResponseBody
    public Response<SmartResultDto> addSmart(IntelligentFixingsDto dto) throws Exception
    {
    	logger.debug("IntelligentFixingsDto：" + dto);
    	Response<SmartResultDto> result = new Response<SmartResultDto>();
    	
    	for(int i = 0;i < dto.getCategoryId().length;i++){
    		CategoryDto categoryDto = new CategoryDto();
    		categoryDto.setCategoryId(dto.getCategoryId()[i]);
    		categoryDto.setCategoryCount(Long.parseLong(dto.getCategoryCount()[i]));
    		dto.getCategory().add(categoryDto);
    	}
    	for(int i = 0;i < dto.getColorId().length;i++){
    		ColorDto colorDto = new ColorDto();
    		colorDto.setColorId(dto.getColorId()[i]);
    		dto.getColor().add(colorDto);
    	}
    	for(int i = 0;i < dto.getTasteId().length;i++){
    		TasteDto tasteDto = new TasteDto();
    		tasteDto.setTasteId(dto.getTasteId()[i]);
    		dto.getTaste().add(tasteDto);
    	}
    	for(int i = 0;i < dto.getCuisineId().length;i++){
    		CuisineDto cuisineDto = new CuisineDto();
    		cuisineDto.setCuisineId(dto.getCuisineId()[i]);
    		dto.getCuisine().add(cuisineDto);
    	}
    	for(int i = 0;i < dto.getStyleId().length;i++){
    		StyleDto styleDto = new StyleDto();
    		styleDto.setStyleId(dto.getStyleId()[i]);
    		dto.getStyle().add(styleDto);
    	}
    	for(int i = 0;i < dto.getShapeId().length;i++){
    		ShapeDto shapeDto = new ShapeDto();
    		shapeDto.setShapeId(dto.getShapeId()[i]);
    		dto.getShape().add(shapeDto);
    	}
    	for(int i = 0;i < dto.getNutritionId().length;i++){
    		SmartNutritionDto smartNutritionDto = new SmartNutritionDto();
    		smartNutritionDto.setNutritionId(dto.getNutritionId()[i]);
    		smartNutritionDto.setNutritionOperator(dto.getNutritionOperator()[i]);
    		smartNutritionDto.setNutritionContent(Long.parseLong(dto.getNutritionContent()[i]));
    		dto.getNutrition().add(smartNutritionDto);
    	}
    	
    	dto.setFixingsStartTime(DateUtils.StringToDate(dto.getFixingsStartTimeStr(), "yyyy-MM-dd"));
    	dto.setFixingsEndTime(DateUtils.StringToDate(dto.getFixingsEndTimeStr(), "yyyy-MM-dd"));
    	SmartResultDto res = iIntelligentFixingsService.addSmart(dto);
    	 if(res == null){
    		 result.setStatus(500);
    		 result.setMessage("添加智能配菜失败");
    		 return result;
    	 }else{
    		/* Page page = new Page();
    		 String id = menuGenService.generateMenu(res, page);
    		 if(StringUtils.isEmpty(id)){
    			 result.setStatus(500);
        		 result.setMessage("保存智能配菜结果集失败");
        		 return result; 
    		 }*/
    		 result.setStatus(200);
    		 result.setMessage("添加智能配菜成功");
    		 result.setData(res);
    	 }
        
        return result;
    }
    
	/**
	 * 指定智能套餐名字
	 * @author YIn
	 * @time:2016年1月7日 下午2:25:12
	 * @return
	 */
	@RequestMapping("/toAddSmartName.do")
	public ModelAndView toAddSmartName(SmartResultDto smartResultDto) {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("smartResultDto", smartResultDto);
		mv.setViewName("cookbook/smart/toAddSmartName");
		return mv;
	}
	
	/**
	 * 增加智能套餐名称
	 * @author YIn
	 * @time:2015年12月30日 下午5:32:04
	 * @param dto
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/addSmartName.do")
    @ResponseBody
    public Response<String> addSmartName(IntelligentFixingsDto dto) throws Exception
    {
    	logger.debug("IntelligentFixingsDto：" + dto);
    	Response<String> result = new Response<String>();
    	if(dto.getId() == null || dto.getId().equals("")){
    		result.setStatus(500);
   		 	result.setMessage("智能配菜ID为空");
   		 	return result;
    	}
    	 int flag = iIntelligentFixingsService.update(dto);
    	 if(flag>0){
    		 result.setStatus(200);
    		 result.setData(dto.getId());
    		 result.setMessage("添加智能配菜套餐成功");
    	 }else{
    		 result.setStatus(500);
    		 result.setMessage("添加智能配菜套餐失败");
    	 }
        return result;
    }
    /**
     * 查询智能配菜
     * @author YIn
     * @time:2015年12月30日 下午5:32:04
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findSmart.do")
    @ResponseBody
    public List<IntelligentFixingsDto> findSmart(IntelligentFixingsDto dto,Page page) throws Exception
    {
    	logger.debug("IntelligentFixingsDto：" + dto);
    	int count = iIntelligentFixingsService.findCount(dto);
		 //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        dto.setStar(limitPageDto.getStar());
        dto.setEnd(limitPageDto.getEnd());
        dto.setTotalPage(limitPageDto.getTotalPage());
    	List<IntelligentFixingsDto> result= iIntelligentFixingsService.findSmart(dto);
    	return result;
    }
    
    /**
     * 查询智能配菜
     * @author YIn
     * @time:2015年12月30日 下午5:32:04
     * @param dto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findSmartList.do")
    public ModelAndView findSmartList(IntelligentFixingsDto dto,Page page) throws Exception
    {
    	logger.debug("IntelligentFixingsDto：" + dto);
    	ModelAndView mv = this.getModelAndView();
    	PageData pd = new PageData();
		pd = this.getPageData();
		
		int count = iIntelligentFixingsService.findCount(dto);
		 //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        dto.setStar(limitPageDto.getStar());
        dto.setEnd(limitPageDto.getEnd());
        dto.setTotalPage(limitPageDto.getTotalPage());
    	List<IntelligentFixingsDto> list= iIntelligentFixingsService.findSmart(dto);
    	page.setPd(pd);
    	//设置page信息
    	pageFunction.setLimitPage(list.size(), count, limitPageDto.getTotalPage(), page);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	for(IntelligentFixingsDto i: list){
    		if(i.getCreateTime() != null){
    			i.setCreateTimeStr(sdf.format(i.getCreateTime()));}
    		if(i.getLastUpdateTime() != null){
    			i.setLastUpdateTimeStr(sdf.format(i.getLastUpdateTime()));
    		}
    	}
    	mv.setViewName("cookbook/smart/smartList");
    	mv.addObject("intelligentFixingsDto", list);
    	mv.addObject("pd",pd);
		return mv;
    }
  
	
	/**
	 * 保存数据
	 * @param page
	 * @param independent
	 * @param master
	 * @param category
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.do")
	public Response<CookVo> menuCustomFrom(Page page, IndependentFixingsDto independent, 
			CategoryClassDto category, CategoryClassProductDto product, FixingsVO temp) {
		
		Response<CookVo> response = new Response<CookVo>();
		String resultId = diyBookService.add(independent, category, product, temp);
		if(StringUtils.isNotBlank(resultId)){
			response.setStatus(DataStatus.HTTP_SUCCESS);
			CookVo cvo = new CookVo();
			cvo.setRid(resultId);
			cvo.setLunchCount(independent.getLunchCount());
			cvo.setDinnerCount(independent.getDinnerCount());
			response.setData(cvo);
			response.setMessage("添加数据成功");
		}else{
			response.setStatus(DataStatus.HTTP_FAILE);
			response.setMessage("添加数据失败");
		}
		return response;
	} 
	
	/**
	 * 到编辑智能套餐页面
	 * @author YIn
	 * @time:2016年1月11日 上午9:59:28
	 * @return
	 */
	@RequestMapping("/toEditSmart.do")
	public ModelAndView toEditSmart(String smartId, Page page) {
		ModelAndView mv = this.getModelAndView();
		//类别集合
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        List<ProductCategoryDto> listCategory = categoryService.findListByPage(productCategoryDto, null);
        //菜系集合
        ProductStyleDto productStyleDto = new ProductStyleDto();
        List<ProductStyleDto> listStyle = styleService.findListByPage(productStyleDto, null);
        //口味集合
        ProductTasteDto productTasteDto = new ProductTasteDto();
        List<ProductTasteDto> listTaste = tasteService.findListByPage(productTasteDto, null);
        //颜色集合
        ProductColorDto productColorDto = new ProductColorDto();
        List<ProductColorDto> listColor = colorService.findListByPage(productColorDto, null);
        //形状集合
        ProductShapeDto productShapeDto = new ProductShapeDto();
        List<ProductShapeDto> listShape = shapeService.findListByPage(productShapeDto, null);
        //烹饪集合
        ProductCuisineDto productCuisineDto = new ProductCuisineDto();
        List<ProductCuisineDto> listCuisine = cuisineService.findListByPage(productCuisineDto, null);
        //营养集合
        ParamConfigDto paramConfigDto = new ParamConfigDto();
        paramConfigDto.setParamType("material_nutrition");
        List<ParamConfigDto> listNutrition = iParamConfigService.findForList(paramConfigDto);
		
		IntelligentFixingsDto dto= new IntelligentFixingsDto();
		dto.setId(smartId);
		int count = iIntelligentFixingsService.findCount(dto);
		 //获取分页的开始结束
		LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
		dto.setStar(limitPageDto.getStar());
        dto.setEnd(limitPageDto.getEnd());
        dto.setTotalPage(limitPageDto.getTotalPage());
		List<IntelligentFixingsDto> list= iIntelligentFixingsService.findSmart(dto);
		for(IntelligentFixingsDto i: list){
			//设置起始时间回显
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			i.setFixingsStartTimeStr(sdf.format(i.getFixingsStartTime()));
			//设置结束时间回显
			i.setFixingsEndTimeStr(sdf.format(i.getFixingsEndTime()));
			if(i.getTotalCost() != null){
    		i.setTotalCostStr(Long.toString(i.getTotalCost()));
			}
			if(i.getTotalFixedPrice() != null){
				i.setTotalFixedPriceStr(Long.toString(i.getTotalFixedPrice()));
			}
			
			for(SmartNutritionDto j: i.getNutrition()){
				if(j.getNutritionContent() != null){
					j.setNutritionContentStr(Long.toString(j.getNutritionContent()));
				}
			}
    	}
    	mv.addObject("intelligentFixingsDto", list.get(0));
    	
    	mv.addObject("listCategory", listCategory);
        mv.addObject("listStyle", listStyle);
        mv.addObject("listTaste", listTaste);
        mv.addObject("listColor", listColor);
        mv.addObject("listShape", listShape);
        mv.addObject("listCuisine", listCuisine);
        mv.addObject("listNutrition", listNutrition);
		mv.setViewName("cookbook/smart/smartEdit");
		return mv;
	}
	
	
	/**
	 * 编辑智能配菜
	 * @author YIn
	 * @time:2016年1月11日 下午6:49:10
	 * @param dto
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/editSmart.do")
    @ResponseBody
    @Transactional
    public Response<SmartResultDto> editSmart(IntelligentFixingsDto dto) throws Exception
    {
    	logger.debug("IntelligentFixingsDto：" + dto);
    	Response<SmartResultDto> result = new Response<SmartResultDto>();
    	dto.setStat(0); //逻辑删除
   	 	iIntelligentFixingsService.update(dto);
	   	for(int i = 0;i < dto.getCategoryId().length;i++){
	 		CategoryDto categoryDto = new CategoryDto();
	 		categoryDto.setCategoryId(dto.getCategoryId()[i]);
	 		categoryDto.setCategoryCount(Long.parseLong(dto.getCategoryCount()[i]));
	 		dto.getCategory().add(categoryDto);
	 	}
	 	for(int i = 0;i < dto.getColorId().length;i++){
	 		ColorDto colorDto = new ColorDto();
	 		colorDto.setColorId(dto.getColorId()[i]);
	 		dto.getColor().add(colorDto);
	 	}
	 	for(int i = 0;i < dto.getTasteId().length;i++){
	 		TasteDto tasteDto = new TasteDto();
	 		tasteDto.setTasteId(dto.getTasteId()[i]);
	 		dto.getTaste().add(tasteDto);
	 	}
	 	for(int i = 0;i < dto.getCuisineId().length;i++){
	 		CuisineDto cuisineDto = new CuisineDto();
	 		cuisineDto.setCuisineId(dto.getCuisineId()[i]);
	 		dto.getCuisine().add(cuisineDto);
	 	}
	 	for(int i = 0;i < dto.getStyleId().length;i++){
	 		StyleDto styleDto = new StyleDto();
	 		styleDto.setStyleId(dto.getStyleId()[i]);
	 		dto.getStyle().add(styleDto);
	 	}
	 	for(int i = 0;i < dto.getShapeId().length;i++){
	 		ShapeDto shapeDto = new ShapeDto();
	 		shapeDto.setShapeId(dto.getShapeId()[i]);
	 		dto.getShape().add(shapeDto);
	 	}
	 	for(int i = 0;i < dto.getNutritionId().length;i++){
	 		SmartNutritionDto smartNutritionDto = new SmartNutritionDto();
	 		smartNutritionDto.setNutritionId(dto.getNutritionId()[i]);
	 		smartNutritionDto.setNutritionOperator(dto.getNutritionOperator()[i]);
	 		smartNutritionDto.setNutritionContent(Long.parseLong(dto.getNutritionContent()[i]));
	 		dto.getNutrition().add(smartNutritionDto);
	 	}
	 	
	 	dto.setFixingsStartTime(DateUtils.StringToDate(dto.getFixingsStartTimeStr(), "yyyy-MM-dd"));
	 	dto.setFixingsEndTime(DateUtils.StringToDate(dto.getFixingsEndTimeStr(), "yyyy-MM-dd"));
	 	dto.setLastUpdateTime(new Date());
	 	SmartResultDto res = iIntelligentFixingsService.addSmart(dto);
    	 if(res == null){
 	    	 dto.setStat(1); //逻辑删除1
	   	 	 iIntelligentFixingsService.update(dto);
    		 result.setStatus(500);
    		 result.setMessage("编辑智能配菜失败");
    	 }else{
    		 result.setStatus(200);
    		 result.setMessage("编辑智能配菜成功");
    		 result.setData(res);
    	 }
        
        return result;
    }
	
	/**
	 * 
	 * @author YIn
	 * @time:2016年1月11日 上午10:10:26
	 * @param page
	 * @param independent
	 * @param category
	 * @param product
	 * @param temp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delSmart.do")
	 public Response<String> delSmart(String id){
	    	logger.debug("smartId：" + id);
	    	Response<String> result = new Response<String>();
	    	if(id == null || id.equals("")){
	    		result.setStatus(500);
	   		 	result.setMessage("智能配菜ID为空");
	   		 	return result;
	    	}
	    	IntelligentFixingsDto dto= new IntelligentFixingsDto();
	    	dto.setId(id);
	    	dto.setStat(0); //逻辑删除
	    	 int flag = iIntelligentFixingsService.update(dto);
	    	 if(flag>0){
	    		 result.setStatus(200);
	    		 result.setMessage("删除智能配菜套餐成功");
	    	 }else{
	    		 result.setStatus(500);
	    		 result.setMessage("删除智能配菜套餐失败");
	    	 }
	        return result;
	    }
	
//	@RequestMapping("/menuResultListPage.do")
//	public ModelAndView menuResultList(String pid) {
//		ModelAndView mv = this.getModelAndView();
//		mv.setViewName("cookbook/smart/menuResultList");
//		mv.addObject("pid",pid);
//		return mv;
//	}
	
	@RequestMapping("menuResultListPage.do")
	public ModelAndView menuResultListV02(String pid, String lunchCount, String dinnerCount) {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("cookbook/smart/menuResultList");
		ModelMap map = new ModelMap();
		map.put("pid", pid);
		map.put("lunchCount", lunchCount);
		map.put("dinnerCount", dinnerCount);
		mv.addAllObjects(map);
		return mv;
	}
	
	//09c590d6-2122-4a5a-9ca5-867451223eea
	@RequestMapping("/QueryFixingsResult.do")
	@ResponseBody
	public Map<String,FixingsResultSwDto> QueryFixingsResult(String pid, String lunchCount, String dinnerCount) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("prid", pid);
		Integer lunchCountInt = 0;
		Integer dinnerCountInt = 0;
		if(StringUtils.isNotBlank(lunchCount) && (!lunchCount.equals("null"))){
			lunchCountInt = Integer.parseInt(lunchCount);
		}else{
			lunchCountInt = 0;
		}
		if(StringUtils.isNotBlank(dinnerCount) && (!dinnerCount.equals("null"))){
			dinnerCountInt = Integer.parseInt(dinnerCount);
		}else{
			dinnerCountInt = 0;
		}
		int totalCount = lunchCountInt.intValue() + dinnerCountInt.intValue();
		List<PageData> FixingsResult = diyBookService.QueryFixingsResult(pd);
		
		//map1 存放所有成品菜数据 key:时间 value:当天成品菜结果集
		Map<String,FixingsResultSwDto> map1 = new HashMap<String, FixingsResultSwDto>();
		//包含每天营养 key:时间 value:每天的营养
		Map<String,Map<String, NutritionsDto>> map2 = new HashMap<String, Map<String, NutritionsDto>>();
		
		//包含计算原料克重
		Map<String,Map<String, NutritionsDto>> map4 = new HashMap<String, Map<String, NutritionsDto>>();
		//key:
		Map<String,Map<String, PageData>> map3 = new HashMap<String, Map<String,PageData>>();
		for (PageData pageData : FixingsResult) {
			String ftime = pageData.get("ftime").toString();
			//成菜滤重
			Map<String, PageData> mapp  = map3.get(ftime);
			if(mapp != null){
				mapp.put(pageData.get("pname").toString(), pageData);
			}else{
				mapp = new HashMap<String, PageData>();
				mapp.put(pageData.get("pname").toString(), pageData);
				map3.put(ftime, mapp);
			}
			
			//计算每天包含营养
			Map<String, NutritionsDto> mp = map2.get(ftime);
			String nuname = pageData.get("nuname").toString();
			if(mp != null){
				NutritionsDto nd = mp.get(nuname);
				if(nd != null){
					nd.setContent(nd.getContent()+Integer.parseInt(pageData.get("content").toString()));
					nd.setUnit(pageData.get("unit").toString());
				}else{
					NutritionsDto nd1 = new NutritionsDto();
					nd1.setName(nuname);
					nd1.setUnit(pageData.get("unit").toString());
					nd1.setContent(Integer.parseInt(pageData.get("content").toString()));
					mp.put(nuname,nd1);
				}
			}else{
				Map<String, NutritionsDto> mn = new HashMap<String, NutritionsDto>();
				NutritionsDto nd1 = new NutritionsDto();
				nd1.setName(nuname);
				nd1.setUnit(pageData.get("unit").toString());
				nd1.setContent(Integer.parseInt(pageData.get("content").toString()));
				mn.put(nuname,nd1);
				map2.put(ftime, mn);
			}
			
			//原料统计
			Map<String, NutritionsDto> mpn = map4.get(ftime);
			String materialname = pageData.get("materialname").toString();
			if(mpn != null){
				NutritionsDto nd = mpn.get(materialname);
				if(nd != null){
					nd.setMweight(nd.getMweight()+Integer.parseInt(pageData.get("mweight").toString()));
				}else{
					NutritionsDto nd1 = new NutritionsDto();
					nd1.setName(materialname);
					nd1.setMweight(Integer.parseInt(pageData.get("mweight").toString()));
					mpn.put(materialname,nd1);
				}
			}else{
				Map<String, NutritionsDto> m = new HashMap<String, NutritionsDto>();
				NutritionsDto nd1 = new NutritionsDto();
				nd1.setName(materialname);
				nd1.setMweight(Integer.parseInt(pageData.get("mweight").toString()));
				m.put(materialname,nd1);
				map4.put(ftime, m);
			}
		}
		
		//组装上下午配菜结果
		for (String map3key : map3.keySet()) {
			//当天总成本
			Integer totalsingleCost = 0;
			//当天总定价
			Integer totaleachPricing = 0;
			//当天成品菜份数
			Integer totalFs = 0;
			FixingsResultSwDto fdto = map1.get(map3key);
			for (String key : map3.get(map3key).keySet()) {
				 
				PageData pageData = map3.get(map3key).get(key);
				totalsingleCost += Integer.parseInt(pageData.get("singleCost").toString());
				totaleachPricing += Integer.parseInt(pageData.get("eachPricing").toString());
				totalFs++;
				if(fdto != null){
					String mtype = pageData.get("mtype").toString();
					if("1".equals(mtype)){
						Map<String,List<String>> mMap = fdto.getMMap();
						if(mMap != null){
							List<String> plist = mMap.get(pageData.get("typename").toString());
							if(plist != null){
								plist.add(pageData.get("pname").toString());
							}else{ 
								plist = new ArrayList<String>();
								plist.add(pageData.get("pname").toString());
								mMap.put(pageData.get("typename").toString(), plist);
							}
						}else{
							List<String> list = new ArrayList<String>();
							list.add(pageData.get("pname").toString());
							mMap = new HashMap<String, List<String>>();
							mMap.put(pageData.get("typename").toString(), list);
							fdto.setMMap(mMap);
						}

					}else if("2".equals(mtype)){
						Map<String,List<String>> aMap = fdto.getAMap();
						if(aMap != null){
							List<String> plist = aMap.get(pageData.get("typename").toString());
							if(plist != null){
								plist.add(pageData.get("pname").toString());
							}else{ 
								plist = new ArrayList<String>();
								plist.add(pageData.get("pname").toString());
								aMap.put(pageData.get("typename").toString(), plist);
							}
						}else{
							List<String> list = new ArrayList<String>();
							list.add(pageData.get("pname").toString());
							aMap = new HashMap<String, List<String>>();
							aMap.put(pageData.get("typename").toString(), list);
							fdto.setAMap(aMap);
						}

					}
				}else{
					 fdto = new FixingsResultSwDto(); 
					 String mtype = pageData.get("mtype").toString();
					 if("1".equals(mtype)){
						 Map<String,List<String>> mMap = new HashMap<String, List<String>>();
						 List<String> list = new ArrayList<String>();
						 list.add(pageData.get("pname").toString());
						 mMap.put(pageData.get("typename").toString(), list);
						 fdto.setMMap(mMap);
					 }else if("2".equals(mtype)){
						 Map<String,List<String>> AMap = new HashMap<String, List<String>>();
						 List<String> list = new ArrayList<String>();
						 list.add(pageData.get("pname").toString());
						 AMap.put(pageData.get("typename").toString(), list);
						 fdto.setAMap(AMap);
					 }
					 map1.put(map3key, fdto);
				}
			}
			//组装包含营养
			Map<String, NutritionsDto> map = map2.get(map3key);
			Map<String, NutritionsDto> mp4 = map4.get(map3key);
			fdto.setMap2(mp4);
			fdto.setMap(map);
			if(totalCount != 0){
//				fdto.setTotalsingleCost(totalsingleCost*totalCount); // 单价x数量
//				fdto.setTotaleachPricing(totaleachPricing*totalCount); // 单价x数量
				fdto.setTotalsingleCost(totalsingleCost); // 单价x数量
				fdto.setTotaleachPricing(totaleachPricing); // 单价x数量
			}else{
				fdto.setTotalsingleCost(totalsingleCost); 
				fdto.setTotaleachPricing(totaleachPricing); 
			}
			fdto.setSingleCost(totalsingleCost/totalFs);
			fdto.setEachPricing(totaleachPricing/totalFs);
			map1.put(map3key,fdto);
		}
		return map1;
	}

	/**
	 * 通过pids查询成品菜总的成本价
	 * @param pids 
	 * <br>pids以逗号","分隔(尾端还有一个多余的逗号)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCostSum.do")
	public String getCostSum(String pids){
		
		Double costSum = 0.0;
		String[] pidArr = Tools.toStrArr(pids, ",");
		
		for (String pid : pidArr) {
			
			ProductDto pro = productService.findById(pid);
			if(pro != null){
				costSum += pro.getSingleCost();
			}
		}
		return String.valueOf(costSum);
	}

}
