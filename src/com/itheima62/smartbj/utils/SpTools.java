package com.itheima62.smartbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 * @����ʱ�� 2015-7-4 ����10:40:52
 * @���� TODO
 *
 * @ svn�ύ�ߣ�$Author: gd $
 * @ �ύʱ��: $Date: 2015-07-04 10:51:01 +0800 (Sat, 04 Jul 2015) $
 * @ ��ǰ�汾: $Rev: 5 $
 */
public class SpTools
{
	/**
	 * 
	 * sharedPreference�Ĳ�����
	 * @param key
	 *        �ؼ���
	 * @param value
	 *       ��Ӧ��ֵ
	 */
	public static void setBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();//�ύ�����ֵ��
		
	}
	
	/**
	 * @param context
	 * @param key
	 *        �ؼ���
	 * @param defValue
	 *        ���õ�Ĭ��ֵ
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean defValue){
		//context.getSharedPreferences(,)��ȡSharedPreferences������д洢���Ա�ͬһ����������������
		//��һ������Ϊ�洢�ļ�  �ڶ���������������
		SharedPreferences sp = context.getSharedPreferences(MyConstants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
}
