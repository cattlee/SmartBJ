package com.itheima62.smartbj.utils;

import android.R.integer;
import android.content.Context;

/**
 * @author ltf
 * @创建时间2016-5-18下午4:19:08
 * @工程名SmartBJ
 * @描述TODO
 * @git提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class DensityUtil {
	 /** 
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
}
