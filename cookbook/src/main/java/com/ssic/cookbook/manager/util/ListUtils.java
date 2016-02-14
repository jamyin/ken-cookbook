/**
 * 
 */
package com.ssic.cookbook.manager.util;

import java.util.ArrayList;
import java.util.List;

/**		
 * <p>Title: listUtils </p>
 * <p>Description:list工具类</p>
 * <p>Copyright (c) 2016 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2016年1月12日 下午4:05:39	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2016年1月12日 下午4:05:39</p>
 * <p>修改备注：</p>
 */
public class ListUtils
{

    public static List<String> getNewList(List<String> li)
    {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < li.size(); i++)
        {
            String str = li.get(i); //获取传入集合对象的每一个元素
            if (!list.contains(str))
            { //查看新集合中是否有指定的元素，如果没有则加入
                list.add(str);
            }
        }
        return list; //返回集合
    }

}
