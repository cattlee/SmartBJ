package com.itheima62.smartbj.view;

import java.util.ArrayList;
import java.util.List;

import android.net.VpnService;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.itheima62.smartbj.R;
import com.itheima62.smartbj.basepage.BaseTagPage;
import com.itheima62.smartbj.basepage.GovAffairsBaseTagPager;
import com.itheima62.smartbj.basepage.HomeBaseTagPager;
import com.itheima62.smartbj.basepage.NewCenterBaseTagPager;
import com.itheima62.smartbj.basepage.SettingCenterBaseTagPager;
import com.itheima62.smartbj.basepage.SmartServiceBaseTagPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author Administrator
 * @创建时间 2015-7-4 下午4:17:20
 * @描述 主界面的fragment
 * 
 *     @ svn提交者：$Author: gd $ @ 提交时间: $Date: 2015-07-04 17:09:14 +0800 (Sat, 04
 *     Jul 2015) $ @ 当前版本: $Rev: 18 $
 */
public class MainContentFragment extends BaseFragment {

	@ViewInject(R.id.vp_main_content_pages)
	private ViewPager viewPager;

	@ViewInject(R.id.rg_content_radios)
	private RadioGroup rg_radios;

	private List<BaseTagPage> pages = new ArrayList<BaseTagPage>();

	private int selectedIndex;// 当前选中的页面编号  不给其赋值 默认为0.

	@Override
	public void initEvent() {
		// 添加自己的事件（给按钮组加事件）

		// 单选按钮的切换事件
		rg_radios.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				// 五个单选按钮
				switch (checkedId) {// 判断是那个按钮点击的
				case R.id.rb_main_content_home:// 主界面
					selectedIndex = 0;
					break;
				case R.id.rb_main_content_newscenter:// 新闻中心界面
					selectedIndex = 1;// 记住选中的页面编号
					break;
				case R.id.rb_main_content_smartservice:// 智慧服务界面
					selectedIndex = 2;// 记住选中的页面编号
					break;
				case R.id.rb_main_content_govaffairs:// 政务界面
					selectedIndex = 3;// 记住选中的页面编号
					break;
				case R.id.rb_main_content_settingcenter:// 设置中心界面
					selectedIndex = 4;// 记住选中的页面编号
					break;

				default:
					break;
				}// end switch(checkedId){}
				switchpage();
			}
		});
		super.initEvent();
	}

			/**
			 * 设置选中的页面
			 */
			private void switchpage() {
				// 根据selectedIndex设置选中的页面
				// BaseTagPage currentPage = pages.get(selectedIndex);
				viewPager.setCurrentItem(selectedIndex);// 设置viewPager现实的页面
				// 如果是第一个或者最后一个 则不让左侧菜单滑动出来
				if (selectedIndex == 0 || selectedIndex == pages.size() - 1) {
					// 不让左侧菜单滑动出来
					mainActivity.getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_NONE);// 滑动不出来
				} else {
					// 可以滑动出左侧菜单
					mainActivity.getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);// 屏幕任何位置都可以滑动
				}
			}
		

	@Override
	public View initView() {
		View root = View.inflate(mainActivity, R.layout.fragment_content_view,
				null);

		// xutils 动态注入view

		ViewUtils.inject(this, root);
		return root;
	}

	@Override
	public void initData() {
		// 首页
		pages.add(new HomeBaseTagPager(mainActivity));
		// 首页
		pages.add(new NewCenterBaseTagPager(mainActivity));
		// 首页
		pages.add(new SmartServiceBaseTagPager(mainActivity));
		// 首页
		pages.add(new GovAffairsBaseTagPager(mainActivity));
		// 首页
		pages.add(new SettingCenterBaseTagPager(mainActivity));

		MyAdapter adapter = new MyAdapter();
		viewPager.setAdapter(adapter);
		//设置默认选择首页
		switchpage();
		//设置第一个按钮被选中（首页）
		rg_radios.check(R.id.rb_main_content_home);
	}

	private class MyAdapter extends PagerAdapter {

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
