package com.itheima62.smartbj.basepage;

import com.itheima62.smartbj.R;
import com.itheima62.smartbj.activity.MainActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author Administrator
 * @创建时间 2015-7-4 下午5:24:12
 * @描述 TODO
 *
 * @ svn提交者：$Author: gd $
 * @ 提交时间: $Date: 2015-07-04 17:38:44 +0800 (Sat, 04 Jul 2015) $
 * @ 当前版本: $Rev: 18 $
 */
public class BaseTagPage
{
	protected MainActivity mainActivity;
	protected View	root;
	protected ImageButton	ib_menu;//按钮ib
	protected TextView	tv_title;
	protected FrameLayout	fl_content;
	public BaseTagPage(MainActivity context){
		this.mainActivity = context;
		initView();//初始化布局
		initData();
		initEvent();
	}

	public  void initView() {
		//界面的根布局
		root = View.inflate(mainActivity, R.layout.fragment_content_base_content, null);
		
		ib_menu = (ImageButton) root.findViewById(R.id.ib_base_content_menu);
		
		tv_title = (TextView) root.findViewById(R.id.tv_base_content_title);
		
		fl_content = (FrameLayout) root.findViewById(R.id.fl_base_content_tag);
	}
	
	public void initEvent(){
		//给菜单按钮添加点击事件
		ib_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//打开或者关闭左侧菜单
				mainActivity.getSlidingMenu().toggle();//左侧页面的开关
			}
		});
	}
	
	public void initData(){
		
	}
	
	
	public View getRoot(){
		return root;
	}
}
