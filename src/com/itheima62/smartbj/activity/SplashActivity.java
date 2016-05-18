package com.itheima62.smartbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.itheima62.smartbj.R;
import com.itheima62.smartbj.utils.MyConstants;
import com.itheima62.smartbj.utils.SpTools;

/**
 * @author ltf
 * @����ʱ��2016-5-16����10:44:07
 * @������SmartBJ
 * @����TODO
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date ${time}
 * @��ǰ�汾��$Rev$
 */
public class SplashActivity extends Activity {
	private ImageView iv_mainview;
	private AnimationSet as;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ȥ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();// ��ʼ������

		startAnimation();// ��ʼ���Ŷ���

		initEvent();// ��ʼ������

	}

	private void initEvent() {
		// 1.��������������ɵ��¼�,ֻ��һ���õ����¼�����ʹ��������������ദ���ã�������Ϊ��Ա����
		as.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// �����¼�����
				// 2.�ж��ǽ��������� �����򵼽��� getApplicationContext()��ȡ������
				if (SpTools.getBoolean(getApplicationContext(),
						MyConstants.ISSETUP, false)) {
					// true ˵���Ѿ����ù� ֱ�ӽ���������
					Intent main=new Intent(SplashActivity.this,MainActivity.class);
					startActivity(main);//����������

				} else {
					// false û���ù� ���������򵼽���
					// SplashActivity.this�൱���ڲ�������
					Intent intent = new Intent(SplashActivity.this,
							GuideActivity.class);
					startActivity(intent);
				}
			}
		});

	}

	/**
	 * ��ʼ���Ŷ��� ��ת�����ţ�����
	 */
	private void startAnimation() {
		// false��ʾÿ�ֶ������ø��ԵĶ��������������ֺ�����
		as = new AnimationSet(false);

		// ��ת������ê��
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);// ���ö���ê��ΪͼƬ������
		// ���ö����Ĳ���ʱ��
		ra.setDuration(2000);
		ra.setFillAfter(true);// ���������꣬ͣ���ڵ�ǰ״̬
		// ��ӵ�������
		as.addAnimation(ra);

		// ���䶯��
		AlphaAnimation aa = new AlphaAnimation(0, 1);// ��3��ȫ͸����Ϊ��ȫ��͸��
		// ���ò���ʱ��
		aa.setDuration(2000);
		// ����������ɺ�ͣ���ڵ�ǰ״̬
		aa.setFillAfter(true);
		// ��ӵ�������
		as.addAnimation(aa);

		// ���Ŷ���
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		// ���ö�������ʱ��
		sa.setDuration(2000);
		sa.setFillAfter(true);
		// ��ӵ�������
		as.addAnimation(sa);

		// ���Ŷ��� ������as������Ҫ�ж���Ч���Ķ���
		iv_mainview.startAnimation(as);

		// ����������� ������һ�����棨�򵼽������ �����棩

		// 1.��������������ɵ��¼�

		// 2.�ж��ǽ��������� �����򵼽���

	}

	private void initView() {
		// ����������
		setContentView(R.layout.activity_splash);
		iv_mainview = (ImageView) findViewById(R.id.iv_splash_mainview);

	}

}
