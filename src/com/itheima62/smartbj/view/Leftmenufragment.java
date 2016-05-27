package com.itheima62.smartbj.view;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @author ltf
 * @创建时间2016-5-24下午8:32:35
 * @工程名SmartBJ
 * @描述  左侧菜单的fragment
 * @git提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class Leftmenufragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);		
		tv.setText("左侧菜单");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
