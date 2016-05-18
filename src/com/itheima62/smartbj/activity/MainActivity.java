package com.itheima62.smartbj.activity;

import com.itheima62.smartbj.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * @author ltf
 * @创建时间2016-5-17下午6:31:53
 * @工程名SmartBJ
 * @描述TODO
 * @git提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
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
