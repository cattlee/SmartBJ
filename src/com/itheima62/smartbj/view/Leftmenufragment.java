package com.itheima62.smartbj.view;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @author ltf
 * @����ʱ��2016-5-24����8:32:35
 * @������SmartBJ
 * @����  ���˵���fragment
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date}${time}
 * @��ǰ�汾��$Rev$
 */
public class Leftmenufragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);		
		tv.setText("���˵�");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
