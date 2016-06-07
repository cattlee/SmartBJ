package com.itheima62.smartbj.newscenterpage;

import android.view.View;

import com.itheima62.smartbj.activity.MainActivity;


/**
 * @author ltf
 * @创建时间2016-6-7上午10:06:22
 * @工程名SmartBJ
 * @描述   左侧界面基类
 * @svn提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public abstract class BaseNewsCenterPage
{
	protected MainActivity mainActivity ;
	protected View root;//根布局
	public BaseNewsCenterPage(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		root = initView();
		initEvent();
	}
	
	/**
	 * 子类覆盖此方法完成事件的处理
	 */
	public void initEvent(){
		
	}
	
	/**
	 * 子类覆盖此方法来显示自定义的View
	 * @return
	 */
	public abstract View initView();
	
	public View getRoot(){
		return root;
	}
	
	
	/**
	 * 子类覆盖此方法完成数据的显示
	 */
	public void initData(){
		
	}
	
	
}
