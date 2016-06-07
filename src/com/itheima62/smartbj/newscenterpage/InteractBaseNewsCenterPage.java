

package com.itheima62.smartbj.newscenterpage;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima62.smartbj.activity.MainActivity;

/**
 * @author ltf
 * @创建时间2016-6-7上午10:06:05
 * @工程名SmartBJ
 * @描述    互动
 * @svn提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class InteractBaseNewsCenterPage extends BaseNewsCenterPage
{

	public InteractBaseNewsCenterPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		// 要展示的内容，
		TextView tv = new TextView(mainActivity);
		tv.setText("互动的内容");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
