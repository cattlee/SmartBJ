package com.itheima62.smartbj.activity;

import com.itheima62.smartbj.R;
import com.itheima62.smartbj.view.Leftmenufragment;
import com.itheima62.smartbj.view.MainContentFragment;
import com.itheima62.smartbj.view.MainContentFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Window;

/**
 * @author ltf
 * @创建时间2016-5-17下午6:31:53
 * @工程名SmartBJ
 * @描述TODO
 * @git提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class MainActivity extends SlidingFragmentActivity {
	
	
	private static final String LEFT_MUNE_TAG = "LEFT_MUNE_TAG";
	private static final String MAIN_MUNE_TAG = "MAIN_MUNE_TAG";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		//设置界面无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		initView();//初始化界面
		initData();//初始化数据
		
	}
	
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		//获得fragment管理器
		FragmentManager fragmentManager=getSupportFragmentManager();
		//更换fragment事物步骤 1.获取事物
	    android.support.v4.app.FragmentTransaction transaction =fragmentManager.beginTransaction();
	
		//2.完成左侧菜单替换
	    transaction.replace(R.id.fl_left_menu, new Leftmenufragment(), LEFT_MUNE_TAG);
	    
	    transaction.replace(R.id.fl_main_menu, new MainContentFragment(),MAIN_MUNE_TAG);
	    
		//3.提交事物
	    transaction.commit();
	}


	private void initView() {
		//设置主界面
		setContentView(R.layout.fragment_content_tag);
		//设置左侧菜单界面
		setBehindContentView(R.layout.fragment_left);
		//设置滑动模式
		SlidingMenu sm=getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);//设置左侧滑动
		//设置滑动位置  可以全屏滑动
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//设置左侧滑动够剩余距离
        sm.setBehindOffset(200);//主屏幕总共长320  剩余70用于显示左侧菜单
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
