package com.itheima62.smartbj.basepage;

import com.itheima62.smartbj.activity.MainActivity;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @author Administrator
 * @创建时间 2015-7-4 下午5:30:22
 * @描述 TODO
 * 
 *     @ svn提交者：$Author: gd $ @ 提交时间: $Date: 2015-07-04 17:38:44 +0800 (Sat, 04
 *     Jul 2015) $ @ 当前版本: $Rev: 18 $
 */
public class SettingCenterBaseTagPager extends BaseTagPage {

	public SettingCenterBaseTagPager(MainActivity context) {
		super(context);
	}

	@Override
	public void initData() {
		// 屏蔽菜单按钮
		ib_menu.setVisibility(View.GONE);
		// 设置本Page的标题
		tv_title.setText("设置中心");
		// 要显示的内容 替换掉 白纸
		TextView tv = new TextView(mainActivity);
		tv.setText("设置中心的内容");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		// 替换掉白纸
		fl_content.addView(tv);// 添加自己的内容到白纸
		super.initData();
	}

}
