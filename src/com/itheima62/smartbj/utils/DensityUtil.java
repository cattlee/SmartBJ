package com.itheima62.smartbj.utils;

import android.R.integer;
import android.content.Context;

/**
 * @author ltf
 * @����ʱ��2016-5-18����4:19:08
 * @������SmartBJ
 * @����TODO
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date}${time}
 * @��ǰ�汾��$Rev$
 */
public class DensityUtil {
	 /** 
     * �����ֻ��ķֱ��ʴ� dip �ĵ�λ ת��Ϊ px(����) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
}
