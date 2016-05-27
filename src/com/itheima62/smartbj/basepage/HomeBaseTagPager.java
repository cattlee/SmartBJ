package com.itheima62.smartbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @author Administrator
 * @创建时间 2015-7-4 下午5:30:22
 * @描述 TODO
 *
 * @ svn提交者：$Author: gd $
 * @ 提交时间: $Date: 2015-07-04 17:38:44 +0800 (Sat, 04 Jul 2015) $
 * @ 当前版本: $Rev: 18 $
 */
public class HomeBaseTagPager extends BaseTagPage
{

	public HomeBaseTagPager(Context context) {
		super(context);
	}
	@Override
	public void initData() {
		tv_title.setText("首页");
		
		TextView tv = new TextView(context);
		tv.setText("首页的内容");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		super.initData();
	}
}
