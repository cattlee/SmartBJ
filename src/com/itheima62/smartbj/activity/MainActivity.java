package com.itheima62.smartbj.activity;

import com.itheima62.smartbj.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * @author ltf
 * @����ʱ��2016-5-17����6:31:53
 * @������SmartBJ
 * @����TODO
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date}${time}
 * @��ǰ�汾��$Rev$
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}
	
	
	private void initView() {
		setContentView(R.layout.activity_main);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
