package com.itheima62.smartbj.newstpipage;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itheima62.smartbj.R;
import com.itheima62.smartbj.activity.MainActivity;
import com.itheima62.smartbj.domain.NewsCenterData.NewsData.ViewTagData;
import com.itheima62.smartbj.domain.TPINewsData;
import com.itheima62.smartbj.domain.TPINewsData.TPINewsData_Data.TPINewsData_Data_ListNewsData;
import com.itheima62.smartbj.domain.TPINewsData.TPINewsData_Data.TPINewsData_Data_LunBoData;
import com.itheima62.smartbj.utils.DensityUtil;
import com.itheima62.smartbj.utils.MyConstants;
import com.itheima62.smartbj.utils.SpTools;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * @author ltf
 * @创建时间2016-6-2下午4:56:38
 * @工程名SmartBJ
 * @描述    新闻中心页签对应的页面
 * @svn提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public class TPINewsNewsCenterPager {
	private static final Class<Object> TPINewsData = null;

	// 所有组件

	@ViewInject(R.id.vp_tpi_news_lunbo_pic)
	private ViewPager vp_lunbo; // 轮播图的显示组件

	@ViewInject(R.id.tv_tpi_news_pic_desc)
	private TextView tv_pic_desc;// 图片的描述信息

	@ViewInject(R.id.ll_tpi_news_pic_points)
	private LinearLayout ll_points;// 轮播图的每张图片对应的点组合

	@ViewInject(R.id.lv_tpi_news_listnews)
	private com.itheima62.smartbj.view.RefreshListView lv_listnews;// 显示列表新闻的组件

	// 数据
	private MainActivity mainActivity;
	private View root;
	private ViewTagData viewTagData;// 页签对应的数据

	private Gson gson;

	// 轮播图的数据
	private List<TPINewsData_Data_LunBoData> lunboDatas = new ArrayList<TPINewsData.TPINewsData_Data.TPINewsData_Data_LunBoData>();

	// 轮播图的适配器
	private LunBoAdapter lunboAdapter;

	private BitmapUtils bitmapUtils;

	private int picSelectIndex;

	private Handler handler;

	private LunBoTask lunboTask;
	
	// 新闻列表的数据
	private List<TPINewsData_Data_ListNewsData> listNews = new ArrayList<TPINewsData.TPINewsData_Data.TPINewsData_Data_ListNewsData>();

	private ListNewsAdapter listNewsAdapter;

	public TPINewsNewsCenterPager(MainActivity mainActivity,
			ViewTagData viewTagData) {
		this.mainActivity = mainActivity;
		this.viewTagData = viewTagData;
		gson = new Gson();
		// 轮播的任务
		lunboTask = new LunBoTask();

		// xutils bitmag 组件
		bitmapUtils = new BitmapUtils(mainActivity);
		bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);

		initView();// 初始化界面
		initData();// 初始化数据
		initEvent();// 初始化事件
	}

	private void initEvent() {
		// 给轮播图添加页面切换事件（滑动新闻中心的viewpage时对相应页面添加不同的图标和点）
		vp_lunbo.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				picSelectIndex = position;
				//设置图片的信息和点
				setPicDescAndPointSelect(picSelectIndex);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initData() {
		// 轮播图的适配器
		lunboAdapter = new LunBoAdapter();
		// 给轮播图
		vp_lunbo.setAdapter(lunboAdapter);

		// 新闻列表的适配器
		listNewsAdapter = new ListNewsAdapter();

		// 设置新闻列表适配
		lv_listnews.setAdapter(listNewsAdapter);

		// 从本地获取数据
		String jsonCache = SpTools.getString(mainActivity, viewTagData.url, "");
		if (!TextUtils.isEmpty(jsonCache)) {
			// 有数据，解析数据
			com.itheima62.smartbj.domain.TPINewsData newsData = parseJson(jsonCache);
			// 处理数据
			processData(newsData);
		}

		getDataFromNet();// 从网络获取数据
	}

	/**
	 * @param newsData
	 */
	private void processData(com.itheima62.smartbj.domain.TPINewsData newsData) {
		// 完成数据的处理

		// 1.设置轮播图的数据
		setLunBoData(newsData);

		// 2.轮播图对应的点处理
		initPoints();// 初始化轮播图的点

		// 3. 设置图片描述和点的选中效果
		setPicDescAndPointSelect(picSelectIndex);

		// 4. 开始轮播图        lunboProcess（）轮播图的处理
		lunboTask.startLunbo();

		// 5. 新闻列表的数据
		setListViewNews(newsData);
	}

	/**
	 * 设置新闻列表的数据
	 * 
	 * @param newsData
	 */
	private void setListViewNews(
			com.itheima62.smartbj.domain.TPINewsData newsData) {

		listNews = newsData.data.news;
		// 更新界面，适配器与新闻中心 控件结合  进行操作
		listNewsAdapter.notifyDataSetChanged();
	}

	/**
	 * 处理轮播图
	 */
	private void lunboProcess() {
		if (handler == null) {

			handler = new Handler();
		}
		// 清空掉原来所有的任务，防止重复加载页面  页面加载太快
		handler.removeCallbacksAndMessages(null);
		
		 //延迟1.5s 时间  发送消息
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 任务
				// 控制轮播图的显示，ViewPage.setCurrentItem( )直接跳转到指定页面
				vp_lunbo.setCurrentItem((vp_lunbo.getCurrentItem() + 1)
						% vp_lunbo.getAdapter().getCount());
				
				//再发一次
				handler.postDelayed(this, 1500);
			}
		}, 1500);
	}

	private class LunBoTask extends Handler implements Runnable {

		/**
		 * 控制轮播图 不再移动（当用户触摸轮播图时，轮播图不应该再移动）
		 */
		public void stopLunbo() {
			// 移除当前所有的任务
			removeCallbacksAndMessages(null);
		}

		public void startLunbo() {
			stopLunbo();//移除轮播图
			postDelayed(this, 2000);
		}
		//runable 的抽象方法
		@Override
		public void run() {
			// 控制轮播图的显示
			vp_lunbo.setCurrentItem((vp_lunbo.getCurrentItem() + 1)
					% vp_lunbo.getAdapter().getCount());
			postDelayed(this, 2000);
		}

	}

	private void setPicDescAndPointSelect(int picSelectIndex) {
		// 设置描述信息
		tv_pic_desc.setText(lunboDatas.get(picSelectIndex).title);

		// 设置点是否是选中
		for (int i = 0; i < lunboDatas.size(); i++) {
			ll_points.getChildAt(i).setEnabled(i == picSelectIndex);
		}

	}

	private void initPoints() {
		// 清空以前存在的点
		ll_points.removeAllViews();
		// 轮播图有几张 加几个点
		for (int i = 0; i < lunboDatas.size(); i++) {
			View v_point = new View(mainActivity);
			// 设置点的背景选择器
			v_point.setBackgroundResource(R.drawable.point_seletor);
			v_point.setEnabled(false);// 默认是默认的灰色点

			// 设置点的大小 均为5dip 利用DensityUtil.dip2px（，）方法进行转换
			LayoutParams params = new LayoutParams(DensityUtil.dip2px(
					mainActivity, 5), DensityUtil.dip2px(mainActivity, 5));
			// 设置点与点直接的间距
			params.leftMargin = DensityUtil.dip2px(mainActivity, 10);

			// 设置点的参数
			v_point.setLayoutParams(params);
			ll_points.addView(v_point);
		}
	}

	private void setLunBoData(com.itheima62.smartbj.domain.TPINewsData newsData) {
		// 获取轮播图的数据
		lunboDatas = newsData.data.topnews;

		lunboAdapter.notifyDataSetChanged();// 更新界面
	}

	/**
	 * @author ltf
	 * @创建时间2016-6-2下午4:55:41
	 * @工程名SmartBJ
	 * @描述     新闻中心中  新闻列表的适配器
	 * @svn提交者：$Auther$
	 * @提交时间：${date}${time}
	 * @当前版本：$Rev$
	 */
	private class ListNewsAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listNews.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		/* (non-Javadoc)
		 * 进行显示新闻列表数据
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			//convertView  为缓存视图
			if (convertView == null) {
				//获取缓存视图
				convertView = View.inflate(mainActivity,
						R.layout.tpi_news_listview_item, null);
				
				holder = new ViewHolder();
				holder.iv_icon = (ImageView) convertView
						.findViewById(R.id.iv_tpi_news_listview_item_icon);
				holder.iv_newspic = (ImageView) convertView
						.findViewById(R.id.iv_tpi_news_listview_item_pic);
				holder.tv_title = (TextView) convertView
						.findViewById(R.id.tv_tpi_news_listview_item_title);
				holder.tv_time = (TextView) convertView
						.findViewById(R.id.tv_tpi_news_listview_item_time);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// 设置数据
			//获取数据
			TPINewsData_Data_ListNewsData tpiNewsData_Data_ListNewsData = listNews
					.get(position);
			// 设置标题
			holder.tv_title.setText(tpiNewsData_Data_ListNewsData.title);

			// 设置时间
			holder.tv_time.setText(tpiNewsData_Data_ListNewsData.pubdate);

			// 设置图片  利用xutils  读取url为convertView的 图片数据
			bitmapUtils.display(holder.iv_newspic,
					tpiNewsData_Data_ListNewsData.listimage);

			return convertView;
		}

	}

	/**
	 * @author ltf
	 * @创建时间2016-6-2下午4:59:07
	 * @工程名SmartBJ
	 * @描述    新闻中心 新闻列表的 缓存类
	 * @svn提交者：$Auther$
	 * @提交时间：${date}${time}
	 * @当前版本：$Rev$
	 */
	private class ViewHolder {
		ImageView iv_newspic;
		TextView tv_title;
		TextView tv_time;
		ImageView iv_icon;
	}

	/**
	 * @author Administrator
	 * @创建时间 2015-7-7 上午10:54:11
	 * @描述 轮播图的适配器
	 * 
	 *     @ svn提交者：$Author: gd $ @ 提交时间: $Date: 2015-07-07 16:52:09 +0800 (Tue,
	 *     07 Jul 2015) $ @ 当前版本: $Rev: 46 $
	 */
	private class LunBoAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv_lunbo_pic = new ImageView(mainActivity);
			iv_lunbo_pic.setScaleType(ScaleType.FIT_XY);

			// 设备默认的图片,网络缓慢
			iv_lunbo_pic.setImageResource(R.drawable.home_scroll_default);

			// 给图片添加数据
			TPINewsData_Data_LunBoData tpiNewsData_Data_LunBoData = lunboDatas
					.get(position);

			// 图片的url
			String topimageUrl = tpiNewsData_Data_LunBoData.topimage;

			// 把url的图片给iv_lunbo_pic
			// 异步加载图片，并且显示到组件中
			bitmapUtils.display(iv_lunbo_pic, topimageUrl);

			// 给图片添加触摸事件
			iv_lunbo_pic.setOnTouchListener(new OnTouchListener() {

				private float downX;
				private float downY;
				private long downTime;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:// 按下停止轮播
						System.out.println("按下");
						downX = event.getX();
						downY = event.getY();
						downTime = System.currentTimeMillis();
						lunboTask.stopLunbo();
						break;
					case MotionEvent.ACTION_CANCEL:// 事件取消
						lunboTask.startLunbo();
						break;
					case MotionEvent.ACTION_UP:// 松开
						float upX = event.getX();
						float upY = event.getY();

						if (upX == downX && upY == downY) {
							long upTime = System.currentTimeMillis();
							if (upTime - downTime < 500) {
								// 点击
								lunboPicClick("被单击了。。。。。");
							}
						}
						System.out.println("松开");
						lunboTask.startLunbo();// 开始轮播
						break;
					default:
						break;
					}
					return true;
				}

				private void lunboPicClick(Object data) {
					// 处理图片的点击事件
					System.out.println(data);

				}
			});
			container.addView(iv_lunbo_pic);

			return iv_lunbo_pic;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lunboDatas.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

	}

	private TPINewsData parseJson(String jsonData) {
		// 解析json数据

		TPINewsData tpiNewsData = gson.fromJson(jsonData, TPINewsData.class);
		return tpiNewsData;
	}

	private void getDataFromNet() {
		// httpUtils
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyConstants.SERVERURL + viewTagData.url,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求数据成功
						String jsonData = responseInfo.result;

						// 保存数据到本地
						SpTools.setString(mainActivity, viewTagData.url,
								jsonData);

						// 解析数据
						com.itheima62.smartbj.domain.TPINewsData newsData = parseJson(jsonData);

						// 处理数据
						processData(newsData);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 请求数据失败
					}
				});
	}

	private void initView() {
		// 页签对应页面的根布局
		root = View.inflate(mainActivity, R.layout.tpi_news_centent, null);

		ViewUtils.inject(this, root);

		View lunBoPic = View.inflate(mainActivity, R.layout.tpi_news_lunbopic,
				null);
		ViewUtils.inject(this, lunBoPic);

		// 把轮播图加到listView中    加载到listview的头上
		lv_listnews.addLunboView(lunBoPic);
	}

	public View getRootView() {
		return root;
	}

}
