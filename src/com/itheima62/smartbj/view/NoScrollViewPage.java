package com.itheima62.smartbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author ltf
 * @创建时间2016-5-27下午2:11:59
 * @工程名SmartBJ
 * @描述不能华东的viewpage
 * @svn提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class NoScrollViewPage extends ViewPager {
	public NoScrollViewPage(Context context,AttributeSet attrs){
		super(context, attrs);
	}
	public NoScrollViewPage(Context context){
		super(context);
	}
	/* 
	 * 不让自己拦截
	 * @see android.support.v4.view.ViewPager#onInterceptTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
	/* 
	 * 设置无法获取touch事件
	 * (non-Javadoc)
	 * @see android.support.v4.view.ViewPager#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}
}
