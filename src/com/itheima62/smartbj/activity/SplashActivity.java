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
 * @创建时间2016-5-16上午10:44:07
 * @工程名SmartBJ
 * @描述TODO
 * @git提交者：$Auther$
 * @提交时间：${date ${time}
 * @当前版本：$Rev$
 */
public class SplashActivity extends Activity {
	private ImageView iv_mainview;
	private AnimationSet as;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去掉标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();// 初始化界面

		startAnimation();// 开始播放动画

		initEvent();// 初始化界面

	}

	private void initEvent() {
		// 1.监听动画播放完成的事件,只是一处用到该事件，则使用匿名类对象，若多处调用，则声明为成员变量
		as.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 监听事件播完
				// 2.判断是进入主界面 还是向导界面 getApplicationContext()获取上下文
				if (SpTools.getBoolean(getApplicationContext(),
						MyConstants.ISSETUP, false)) {
					// true 说明已经设置过 直接进入上下文
					Intent main=new Intent(SplashActivity.this,MainActivity.class);
					startActivity(main);//启动主界面

				} else {
					// false 没设置过 进入设置向导界面
					// SplashActivity.this相当于内部类启动
					Intent intent = new Intent(SplashActivity.this,
							GuideActivity.class);
					startActivity(intent);
				}
			}
		});

	}

	/**
	 * 开始播放动画 旋转，播放，渐变
	 */
	private void startAnimation() {
		// false表示每种动画采用各自的动画插入器（数字函数）
		as = new AnimationSet(false);

		// 旋转动画，锚点
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);// 设置动画锚点为图片的中心
		// 设置动画的播放时间
		ra.setDuration(2000);
		ra.setFillAfter(true);// 动画播放完，停留在当前状态
		// 添加到动画集
		as.addAnimation(ra);

		// 渐变动画
		AlphaAnimation aa = new AlphaAnimation(0, 1);// 又3完全透明变为完全不透明
		// 设置播放时间
		aa.setDuration(2000);
		// 动画播放完成后，停留在当前状态
		aa.setFillAfter(true);
		// 添加到动画集
		as.addAnimation(aa);

		// 缩放动画
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		// 设置动画播放时间
		sa.setDuration(2000);
		sa.setFillAfter(true);
		// 添加到动画集
		as.addAnimation(sa);

		// 播放动画 将动画as集给需要有动画效果的对象
		iv_mainview.startAnimation(as);

		// 动画播放完成 进入下一个界面（向导界面或者 主界面）

		// 1.监听动画播放完成的事件

		// 2.判断是进入主界面 还是向导界面

	}

	private void initView() {
		// 设置主界面
		setContentView(R.layout.activity_splash);
		iv_mainview = (ImageView) findViewById(R.id.iv_splash_mainview);

	}

}
