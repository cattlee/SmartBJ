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
 * @����ʱ��2016-5-16����4:44:33
 * @������SmartBJ
 * @���� ����viewpager���н����л� ��������򵼽���
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date ${time}
 * @��ǰ�汾��$Rev$
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
		initView();// ��ʼ������
		initData();// ��ʼ������
	}

	private void initData() {
		// viewpaper adapter list
		// ͼƬ������
		int[] pics = new int[] { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };
		// ����viewPagerʹ�õ�����
		guids = new ArrayList<ImageView>();
		// ��ʼ�������е�����
		for (int i = 0; i < pics.length; i++) {
			ImageView iv_temp = new ImageView(getApplicationContext());
			iv_temp.setBackgroundResource(pics[i]);

			// ��ӽ��������
			guids.add(iv_temp);

			// ���������Linearlayout��ʼ����ӻ�ɫ��
			View v_point = new View(getApplicationContext());
			v_point.setBackgroundResource(R.drawable.gray_point);
			int dip = 10;
			// ���û�ɫ��Ĵ�С
			LayoutParams params = new LayoutParams(DensityUtil.dip2px(
					getApplicationContext(), dip), DensityUtil.dip2px(
					getApplicationContext(), dip));// ע�ⵥλ��px ����dp

			// ���õ����ֱ�ӵĿ�϶
			// ��һ���㲻��Ҫָ��
			if (i != 0)// ���˵�һ����
				params.leftMargin = 10;// px
			v_point.setLayoutParams(params);// �޷�϶�İ�һ��

			// ��ӻ�ɫ�ĵ㵽���Բ�����
			ll_points.addView(v_point);
		}

		// ����û�в���ǰ�����λ����ȷ�����˵ģ�������ɣ��������ֱ�ӵľ���

		// ����ViewPager��������
		adapter = new MyAdapter();

		// ����������
		vp_guids.setAdapter(adapter);
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return guids.size();// �������ݵĸ���
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;//���� ���������  Ϊtrue ����ʾ��Ӧ��ImageView   
			//��instantiateItem���������ж� viewGroup���䷵�ص�ֵ�Ƿ�������ͬ
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//public Object instantiateItem(ViewGroup container, objectint position){}����ֵ��Ϊ destroyItem()��object����
			//ɾ��Viewpager �е�����
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//container   ����ViewPager
			//��ȡview
			View child=guids.get(position);
			//���view
			container.addView(child);
			return child;
		}

	}

	private void initView() {
		setContentView(R.layout.activity_guide);
		// viewPager���
		vp_guids = (ViewPager) findViewById(R.id.vp_guide_pages);
		// ��̬�ӵ�����
		ll_points = (LinearLayout) findViewById(R.id.ll_guide_points);
		// ���
		View v_redpoint = findViewById(R.id.v_guide_redpoint);
		// ��ʼ����İ�ť
		bt_starExpt = (Button) findViewById(R.id.bt_guide_startexp);

	}
}
