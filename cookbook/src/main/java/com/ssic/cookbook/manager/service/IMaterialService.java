package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ReturnResultDto;
import com.ssic.util.model.Result;

public interface IMaterialService {

	public List<PageData> findByPage(Page page) throws Exception;
	
	public List<MaterialDto> findListByPage(MaterialDto materialDto);
	
	public List<MaterialDto> findListBy(MaterialDto materialDto);
	
	public List<MaterialDto> findAll(MaterialDto materialDto);
	
	public int findCount(MaterialDto materialDto);
	
	public void updateM(MaterialDto materialDto);
	
	public void insertM(MaterialDto materialDto);
	
	public MaterialDto findById(String id);
	
    public ReturnResultDto insertAllMater(MaterialDto materialDto);
	
    /**
     * @author pengcheng.yang
     * @desc 导出原料配置数据
     * @param materialDto
     * @return
     * @throws Exception 
     */
    public List<PageData> expExcelFindAll(PageData pd) throws Exception;
    
    public List<NutritionDto> findNuByMid(String mid);
    
    public void delM(String id);
    
    public void updateMaterAndNutr(MaterialDto materialDto);

    
    /**     
     * findByName：通过原料名称查找原料
     * @param string
     * @return 
     * @exception	
     * @author 刘博
     * @date 2015年12月28日 下午4:57:33	 
     */
    public MaterialDto findByName(String string);
    
    public ReturnResultDto insertExecl(List<MaterialDto> list);

    
    /**     
     * findListByName：一句话描述方法功能
     * @param materialDto
     * @return
     * @exception	
     * @author 刘博
     * @date 2016年1月15日 上午10:07:05	 
     */
    public List<MaterialDto> findListByName(MaterialDto materialDto);
}
