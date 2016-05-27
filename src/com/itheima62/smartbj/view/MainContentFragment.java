package com.itheima62.smartbj.view;

import java.util.ArrayList;
import java.util.List;

import android.net.VpnService;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.itheima62.smartbj.R;
import com.itheima62.smartbj.basepage.BaseTagPage;
import com.itheima62.smartbj.basepage.GovAffairsBaseTagPager;
import com.itheima62.smartbj.basepage.HomeBaseTagPager;
import com.itheima62.smartbj.basepage.NewCenterBaseTagPager;
import com.itheima62.smartbj.basepage.SettingCenterBaseTagPager;
import com.itheima62.smartbj.basepage.SmartServiceBaseTagPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author ltf
 * @����ʱ��2016-5-24����8:34:10
 * @������SmartBJ
 * @����  �������fragment
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date}${time}
 * @��ǰ�汾��$Rev$
 */
public class MainContentFragment extends BaseFragment
{

	//@ViewInject(R.id.vp_main_content_pages)
	private ViewPager			viewPager;

	//@ViewInject(R.id.rg_content_radios)
	private RadioGroup			rg_radios;

	private List<BaseTagPage>	pages	= new ArrayList<BaseTagPage>();

	@Override
	public View initView() {
		View root = View.inflate(mainActivity, R.layout.fragment_content_view,
				null);

		// xutils ��̬ע��view

		ViewUtils.inject(this, root);
		return root;
	}

	@Override
	public void initData() {
		// ��ҳ
		pages.add(new HomeBaseTagPager(mainActivity));
		// ��ҳ
		pages.add(new NewCenterBaseTagPager(mainActivity));
		// ��ҳ
		pages.add(new SmartServiceBaseTagPager(mainActivity));
		// ��ҳ
		pages.add(new GovAffairsBaseTagPager(mainActivity));
		// ��ҳ
		pages.add(new SettingCenterBaseTagPager(mainActivity));
		
		MyAdapter adapter = new MyAdapter();
		viewPager.setAdapter(adapter);
		
	}
	
	private class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pages.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			BaseTagPage baseTagPage = pages.get(position);
			View root = baseTagPage.getRoot();
			container.addView(root);
			return root;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
		
	}

}

