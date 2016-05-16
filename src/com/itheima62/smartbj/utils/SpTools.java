package com.itheima62.smartbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 * @创建时间 2015-7-4 上午10:40:52
 * @描述 TODO
 *
 * @ svn提交者：$Author: gd $
 * @ 提交时间: $Date: 2015-07-04 10:51:01 +0800 (Sat, 04 Jul 2015) $
 * @ 当前版本: $Rev: 5 $
 */
public class SpTools
{
	/**
	 * 
	 * sharedPreference的操作类
	 * @param key
	 *        关键字
	 * @param value
	 *       对应的值
	 */
	public static void setBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();//提交保存键值对
		
	}
	
	/**
	 * @param context
	 * @param key
	 *        关键字
	 * @param defValue
	 *        设置的默认值
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean defValue){
		//context.getSharedPreferences(,)获取SharedPreferences对象进行存储可以被同一程序的其他组件共享
		//第一个参数为存储文件  第二个参数操作类型
		SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
}
