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
 * @����ʱ��2016-5-17����6:31:53
 * @������SmartBJ
 * @����TODO
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date}${time}
 * @��ǰ�汾��$Rev$
 */
public class MainActivity extends SlidingFragmentActivity {
	
	
	private static final String LEFT_MUNE_TAG = "LEFT_MUNE_TAG";
	private static final String MAIN_MUNE_TAG = "MAIN_MUNE_TAG";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		//���ý����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		initView();//��ʼ������
		initData();//��ʼ������
		
	}
	
	
	/**
	 * ��ʼ������
	 */
	private void initData() {
		//���fragment������
		FragmentManager fragmentManager=getSupportFragmentManager();
		//����fragment���ﲽ�� 1.��ȡ����
	    android.support.v4.app.FragmentTransaction transaction =fragmentManager.beginTransaction();
	
		//2.������˵��滻
	    transaction.replace(R.id.fl_left_menu, new Leftmenufragment(), LEFT_MUNE_TAG);
	    
	    transaction.replace(R.id.fl_main_menu, new MainContentFragment(),MAIN_MUNE_TAG);
	    
		//3.�ύ����
	    transaction.commit();
	}


	private void initView() {
		//����������
		setContentView(R.layout.fragment_content_tag);
		//�������˵�����
		setBehindContentView(R.layout.fragment_left);
		//���û���ģʽ
		SlidingMenu sm=getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);//������໬��
		//���û���λ��  ����ȫ������
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//������໬����ʣ�����
        sm.setBehindOffset(200);//����Ļ�ܹ���320  ʣ��70������ʾ���˵�
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
