package com.itheima62.smartbj.activity;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.itheima62.smartbj.R;

import com.itheima62.smartbj.utils.MyConstants;
import com.itheima62.smartbj.utils.SpTools;

/**
 * @author ltf
 * @创建时间2016-5-16下午4:44:33
 * @工程名SmartBJ
 * @描述 利用viewpager进行界面切换 完成设置向导界面
 * @git提交者：$Auther$
 * @提交时间：${date ${time}
 * @当前版本：$Rev$
 */
public class GuideActivity extends Activity {
	private ViewPager vp_guids;
	private LinearLayout ll_points;
	private Button bt_starExpt;
	private List<ImageView> guids;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();// 初始化界面
		initData();// 初始化数据
	}

	private void initData() {
		// viewpaper adapter list
		// 图片的数组
		int[] pics = new int[] { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };
		// 定义viewPager使用的容器
		guids = new ArrayList<ImageView>();
		// 初始化容器中的数据
		for (int i = 0; i < pics.length; i++) {
			ImageView iv_temp = new ImageView(getApplicationContext());
			iv_temp.setBackgroundResource(pics[i]);

			// 添加界面的数据
			guids.add(iv_temp);

			// 给点的容器Linearlayout初始化添加灰色点
			View v_point = new View(getApplicationContext());
			v_point.setBackgroundResource(R.drawable.gray_point);
			int dip = 10;
			// 设置灰色点的大小
			LayoutParams params = new LayoutParams(DensityUtil.dip2px(
					getApplicationContext(), dip), DensityUtil.dip2px(
					getApplicationContext(), dip));// 注意单位是px 不是dp

			// 设置点与点直接的空隙
			// 第一个点不需要指定
			if (i != 0)// 过滤第一个点
				params.leftMargin = 10;// px
			v_point.setLayoutParams(params);// 无缝隙的挨一起

			// 添加灰色的点到线性布局中
			ll_points.addView(v_point);
		}

		// 界面没有布局前，点的位置是确定不了的，布局完成，再求出点直接的距离

		// 创建ViewPager的适配器
		adapter = new MyAdapter();

		// 设置适配器
		vp_guids.setAdapter(adapter);
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return guids.size();// 返回数据的个数
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;//过滤 缓存的作用  为true 才显示相应的ImageView   
			//从instantiateItem（）方法判断 viewGroup和其返回的值是否类型相同
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//public Object instantiateItem(ViewGroup container, objectint position){}返回值作为 destroyItem()的object参数
			//删除Viewpager 中的数据
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//container   就是ViewPager
			//获取view
			View child=guids.get(position);
			//添加view
			container.addView(child);
			return child;
		}

	}

	private void initView() {
		setContentView(R.layout.activity_guide);
		// viewPager组件
		vp_guids = (ViewPager) findViewById(R.id.vp_guide_pages);
		// 动态加点容器
		ll_points = (LinearLayout) findViewById(R.id.ll_guide_points);
		// 红点
		View v_redpoint = findViewById(R.id.v_guide_redpoint);
		// 开始体验的按钮
		bt_starExpt = (Button) findViewById(R.id.bt_guide_startexp);

	}
}
