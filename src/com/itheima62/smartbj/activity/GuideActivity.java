

package com.itheima62.smartbj.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import com.itheima62.smartbj.utils.DensityUtil;
import com.itheima62.smartbj.utils.MyConstants;
import com.itheima62.smartbj.utils.SpTools;

/**
 * @author Administrator
 * @����ʱ�� 2015-7-4 ����11:05:45
 * @���� �����򵼽��� ������Viewpager������л�
 * 
 *     @ svn�ύ�ߣ�$Author: gd $ @ �ύʱ��: $Date: 2015-07-04 11:45:43 +0800 (Sat, 04
 *     Jul 2015) $ @ ��ǰ�汾: $Rev: 9 $
 */
public class GuideActivity extends Activity
{
	private ViewPager		vp_guids;
	private LinearLayout	ll_points;
	private View			v_redpoint;
	private Button			bt_startExp;
	private List<ImageView>	guids;
	private MyAdapter		adapter;
	private int	disPoints;//�����֮��ľ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ������
		initView();// ��ʼ������

		initData();// ��ʼ������

		initEvent();// ��ʼ������¼�
	}

	private void initEvent() {
		//����������� �������Ľ��
		v_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
					
					

					@Override
					public void onGlobalLayout() {
						//ȡ��ע�� ����仯�������Ļص����
						v_redpoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						//��������֮��ľ���
						disPoints = (ll_points.getChildAt(1).getLeft() - ll_points.getChildAt(0)
								.getLeft());
					}
				});
		
		//����ť��ӵ���¼�
		bt_startExp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//�������õ�״̬
				SpTools.setBoolean(getApplicationContext(), MyConstants.ISSETUP, true);//����������ɵ�״̬
				//����������
				Intent main = new Intent(GuideActivity.this,MainActivity.class);
				startActivity(main);//����������
				//�ر��Լ�
				finish();
			}
		});
		
		//��ViewPage���ҳ��ı���¼�
		vp_guids.setOnPageChangeListener(new OnPageChangeListener() {
			
			
			@Override
			public void onPageSelected(int position) {
				//��ǰViewPager��ʾ��ҳ��
				//���ViewPager������������ҳ��(���һҳ)����ʾbutton
				if (position == guids.size() - 1) {
					bt_startExp.setVisibility(View.VISIBLE);//�������ð�ť����ʾ
				} else {
					//�������һҳ�����ظ�button��ť
					bt_startExp.setVisibility(View.GONE);
				}
			}
			
			/* (non-Javadoc)
			 * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
			 * ��ҳ�滬�����̴������¼�
			 * @param position ��ǰViewPageͣ����λ��
			 * @param positionOffset ƫ�Ƶı���ֵ
			 * @param positionOffsetPixels ƫ�Ƶ�����
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
				//positionOffset �ƶ��ı���ֵ
				//���������߾�
				float leftMargin = disPoints * (position + positionOffset);
				
				//���ú�����߾�
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v_redpoint.getLayoutParams();
				layoutParams.leftMargin = Math.round(leftMargin);//��float������������
				
				//�������ò���
				v_redpoint.setLayoutParams(layoutParams);
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	}

	private void initData() {
		// viewpaper adapter list
		// ͼƬ������
		int[] pics = new int[] { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };

		// ����Viewpagerʹ�õ�����

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
			
			int dip =10;
			// ���û�ɫ��Ĵ�С  ��λpx
			LayoutParams params=new LayoutParams(DensityUtil.dip2px(getApplicationContext(),dip),DensityUtil.dip2px(getApplicationContext(),dip));//��λpx  Ϊ�豸����

			// ���õ����ֱ�ӵĿ�϶
			// ��һ���㲻��Ҫָ��
			if (i != 0)// ���˵�һ����
				params.leftMargin = 10;// dip
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

	private class MyAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {

			return guids.size();// �������ݵĸ���
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;// ���˺ͻ��������
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);// ��Viewpager���Ƴ�
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// container viewpaper
			// ��ȡView
			View child = guids.get(position);
			// ���View
			container.addView(child);//

			return child;
		}

	}

	private void initView() {
		setContentView(R.layout.activity_guide);

		// ViewPage���
		vp_guids = (ViewPager) findViewById(R.id.vp_guide_pages);

		// ��̬�ӵ�����
		ll_points = (LinearLayout) findViewById(R.id.ll_guide_points);

		// ���
		v_redpoint = findViewById(R.id.v_guide_redpoint);

		// ��ʼ����İ�ť
		bt_startExp = (Button) findViewById(R.id.bt_guide_startexp);
	}
}
