package com.itheima62.smartbj.newstpipage;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap.Config;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itheima62.smartbj.R;
import com.itheima62.smartbj.R.id;
import com.itheima62.smartbj.activity.MainActivity;
import com.itheima62.smartbj.domain.NewsCenterData.NewsData.ViewTagData;
import com.itheima62.smartbj.domain.TPINewsData;
import com.itheima62.smartbj.domain.TPINewsData.TPINewsData_Data;
import com.itheima62.smartbj.domain.TPINewsData.TPINewsData_Data.TPINewsData_Data_LunBoData;
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
 * @创建时间2016-5-31下午7:21:35
 * @工程名SmartBJ
 * @描述 新闻中心页签（北京、中国、国际、国际。。。）对应的页面
 * @svn提交者：$Auther$
 * @提交时间：${date ${time}
 * @当前版本：$Rev$
 */
public class TPINewsNewsCenterPager {
	// 该页面显示的所有组件

	@ViewInject(R.id.vp_tpi_news_lunbo_pic)
	private ViewPager vp_lunbo;// 轮播图的显示组件

	@ViewInject(R.id.tv_tpi_news_pic_desc)
	private TextView tv_tpi_desc;// 图片的描述信息

	@ViewInject(R.id.ll_guide_points)
	private LinearLayout ll_points;// 轮播图的每个图片对应的点组合

	@ViewInject(R.id.lv_tpi_news_listnews)
	private ListView lv_listnews;

	// 数据
	private MainActivity mainActivity;
	private View root;
	private ViewTagData viewTagData;// 页签对应的数据

	private Gson gson;
	// 轮播图的数据
	private List<TPINewsData_Data_LunBoData> lunBoDatas = new ArrayList<TPINewsData.TPINewsData_Data.TPINewsData_Data_LunBoData>();// 新闻中心轮播图的数据
																																	// 适配器容器
	// 轮播图的数据
	private LunBoAdapter lunboAdapter;

																																	private BitmapUtils bitmapUtils;

	public TPINewsNewsCenterPager(MainActivity mainActivity,
			ViewTagData viewTagData) {
		this.mainActivity = mainActivity;
		this.viewTagData = viewTagData;
		gson = new Gson();

		//xutils中的bitmap组件 用于异步   读取url对应的图片  并将图片给相应的组件
		bitmapUtils = new BitmapUtils(mainActivity);
		bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
		initView();// 初始化界面
		initData();// 初始化数据
		initEvent();// 初始化事件
	}

	private void initEvent() {
		// TODO Auto-generated method stub

	}

	private void initData() {
		// 轮播图的适配器在initData（）方法中创建，防止重复创建对象，。（initdata（）方法只执行一次）
		lunboAdapter = new LunBoAdapter();

		// 适配器给轮播图
		vp_lunbo.setAdapter(lunboAdapter);

		// 先从本地获取缓存数据
		String jsonCache = SpTools.getString(mainActivity, viewTagData.url, "");
		if (!TextUtils.isEmpty(jsonCache)) {
			// 有数据 解析json数据
			TPINewsData newsData = parseJson(jsonCache);
			// 处理数据
			processData(newsData);
		}
		// 再 获取网络的数据
		getDataFromNet();// 获取网络的数据
	}

	/**
	 * 解析json数据
	 * 
	 * @param jsonData
	 */
	private TPINewsData parseJson(String jsonData) {
		// 解析json数据
		TPINewsData tpiNewsData = gson.fromJson(jsonData, TPINewsData.class);
		return tpiNewsData;
	}

	/**
	 * 处理json数据
	 */
	private void processData(TPINewsData newsData) {
		// 完成数据的处理

		// 1、设置轮播图的数据
		setlunBoData(newsData);
	}

	private void setlunBoData(TPINewsData newsData) {
		// 获取轮播图的数据
		lunBoDatas = newsData.data.topnews;

		lunboAdapter.notifyDataSetChanged();// 由适配器更新界面
	}

	/**
	 * @author ltf
	 * @创建时间2016-6-1上午9:36:49
	 * @工程名SmartBJ
	 * @描述 轮播图的适配器
	 * @svn提交者：$Auther$
	 * @提交时间：${date ${time}
	 * @当前版本：$Rev$
	 */
	private class LunBoAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 适配器销毁数据（即移除数据）
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 初始化数据
			ImageView iv_lunbo_pic = new ImageView(mainActivity);
			
			//设置默认图片 防止网络缓慢无图片显示
			iv_lunbo_pic.setImageResource(R.drawable.home_scroll_default);
			
			// 给图片添加数据，先获取轮播图的图片数据
			TPINewsData_Data_LunBoData tpiNewsData_Data_LunBoData = lunBoDatas
					.get(position);

			// 图片的url
			String topimageUrl = tpiNewsData_Data_LunBoData.topimage;
			
			//把url的图片给iv_lunbo_pic   (通过xutils类 进行完成)
			//异步加载图片 并且显示在组件中
			bitmapUtils.display(iv_lunbo_pic, topimageUrl);
			
			
			container.addView(iv_lunbo_pic);

			return iv_lunbo_pic;
		}

		@Override
		public int getCount() {
			// 轮播图适配器的大小
			return lunBoDatas.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// 当适配器适配成功时 返回true，才会将view进行显示。
			return view == object;
		}
	}

	private void getDataFromNet() {
		// httpUtils来实现
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyConstants.SERVERURL + viewTagData.url,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求数据成功
						// 获取json数据
						String jsonData = responseInfo.result;

						// 保存数据到本地 jsonData
						SpTools.setString(mainActivity, viewTagData.url,
								jsonData);

						// 解析数据
						TPINewsData newsData = parseJson(jsonData);
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
		root = View.inflate(mainActivity, R.layout.tpi_news_centent, null);
		ViewUtils.inject(this, root);// 动态注入组件
	}

	public View getRootView() {
		return root;
	}
}
