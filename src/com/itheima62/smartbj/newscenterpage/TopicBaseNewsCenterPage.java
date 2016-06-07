package com.itheima62.smartbj.newscenterpage;

import com.itheima62.smartbj.activity.MainActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/**
 * @author ltf
 * @创建时间2016-6-7上午10:05:25
 * @工程名SmartBJ
 * @描述   专题
 * @svn提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class TopicBaseNewsCenterPage extends BaseNewsCenterPage
{

	public TopicBaseNewsCenterPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);
		tv.setText("专题的内容");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
