package com.itheima62.smartbj.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima62.smartbj.R;

/**
 * @author ltf
 * @创建时间2016-6-2下午8:15:48
 * @工程名SmartBJ
 * @描述 自定义 刷新头和加载数据尾 的ListView
 * @svn提交者：$Auther$
 * @提交时间：${date ${time}
 * @当前版本：$Rev$
 */
public class RefreshListView extends ListView {

	private View foot; // listview加载更多数据的尾部组件
	private LinearLayout head; // listview刷新数据的头部组件
	private LinearLayout ll_refresh_head_root;
	private int ll_refresh_head_root_Height;
	private int ll_refresh_foot_Height;
	private float downY = -1; // 作为其是否获得坐标的标记
	private final int PULL_DOWN = 1; // 下拉刷新状态
	private final int RELEASE_STATE = 2; // 松开刷新
	private final int REFRESHING = 3; // 正在刷新
	private int currentState = PULL_DOWN; // 当前的状态
	private View lunbotu;
	private int listViewOnScreanY; // listview在屏幕中的y轴坐标位置
	private TextView tv_state;
	private TextView tv_time;
	private ImageView iv_arrow; // 刷新动画的图片view
	private ProgressBar pb_loading;
	private RotateAnimation up_ra;
	private RotateAnimation down_ra;
	private OnRefreshDataListener listener;// 刷新数据的监听回调
	private boolean isEnablePullRefresh;// 下拉刷新是否可用 默认为false。
	private boolean isloadingMore; // 是否处于加载更多数据的操作

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initView();
		initAnimation();

		// 初始化事件
		initEvent();
	}

	/**
	 * 初始化事件的方法
	 */
	private void initEvent() {
		// 添加当前Listview的滑动事件,设置监听器OnScrollListener
		setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// 滑动状态的改变,状态停止时，如果ListView显示最后一条，加载更多数据的显示
				// 判断是否是最后一条数据显示,且当不处于数据加载状态（防止多次重复加载）
				if (getLastVisiblePosition() == getAdapter().getCount() - 1&&!isloadingMore) {
					// 如果是最后一条数据，显示加载更多的组件
					foot.setPadding(0, 0, 0, 0);// 显示加载更多数据，设置foot（view）自身内容和wait框的间距。
					// listview.setselection(position)，表示将列表移动到指定的Position处。
					setSelection(getAdapter().getCount());// 新加载的数据相当于position为
															// getAdapter().getCount()

					// 加载更多的数据
					
					isloadingMore=true;
					if (listener != null) {
						listener.loadingMore();// 实现该接口的组件去完成数据的加载

					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
	}

	public RefreshListView(Context context, AttributeSet attrs) {

		// 通过配置文件 配置组件 调用该构造函数
		this(context, attrs, 0);
	}

	public RefreshListView(Context context) {

		// 代码写组件 调用该构造函数
		this(context, null);

	}

	private void initView() {
		// TODO Auto-generated method stub
		initFoot();
		initHead();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.AbsListView#onTouchEvent(android.view.MotionEvent)
	 * 覆盖此父类onTouchEvent（）方法 完成自己的事件处理，listview的拖动事件也写在ontouchevent（）中了
	 * 手机屏幕事件的处理方法onTouchEvent
	 * 。该方法在View类中的定义，并且所有的View子类全部重写了该方法，应用程序可以通过该方法处理手机屏幕的触摸事件
	 * 参数event为手机屏幕触摸事件封装类的对象
	 * ，其中封装了该事件的所有信息，例如触摸的位置、触摸的类型以及触摸的时间等。该对象会在用户触摸手机屏幕时被创建。
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 需要我们的功能屏蔽掉父类的touch事件，其他任然按照父类处理
		// 下拉拖动（当listview显示第一个条数据）时 屏蔽父类事件

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:// 按下
			downY = ev.getY();// 按下时y轴坐标
			break;
		case MotionEvent.ACTION_MOVE:// 移动

			// 判断现在是否处于刷新数据的状态
			if (currentState == REFRESHING) {
				// 数据正在刷新
				break;

			}
			if (!isEnablePullRefresh) {
				// 如果不启用下拉刷新，直接跳出
				break;
			}
			if (!isLunboFullShow()) {
				// 轮播图没有完全显示，其响应的是listview的事件 （开始在设置轮播图和listview 响应事件时 如是定义）
				break;// 跳出，执行父类的事件
			}

			if (downY == -1) { // 按下的时候没有获取坐标，重新获取值
				downY = ev.getY();
			}

			// 获取移动位置的坐标
			float moveY = ev.getY();

			// 移动的位置间距
			float dy = moveY - downY;
			// System.out.println("dy:" + dy);
			// 下拉拖动（当listview显示第一个条数据）处理自己的事件，不让listview原生的拖动事件生效
			if (dy > 0 && getFirstVisiblePosition() == 0) {

				// 当前padding top 的参数值
				float scrollYDis = -ll_refresh_head_root_Height + dy;

				if (scrollYDis < 0 && currentState != PULL_DOWN) {
					// 刷新头没有完全显示
					// 下拉刷新的状态
					currentState = PULL_DOWN;// 目的只执行一次
					// 更新 缓存箭头动画
					refreshState();
				} else if (scrollYDis >= 0 && currentState != RELEASE_STATE) {
					currentState = RELEASE_STATE;// 记录松开刷新，只进了一次
					// 更新 缓存箭头动画
					refreshState();
				}
				ll_refresh_head_root.setPadding(0, (int) scrollYDis, 0, 0);
				return true;
			}

			break;
		case MotionEvent.ACTION_UP:// 松开
			downY = -1;
			// 判断状态
			// 如果是PULL_DOWN状态,松开恢复原状
			if (currentState == PULL_DOWN) {
				ll_refresh_head_root.setPadding(0,
						-ll_refresh_head_root_Height, 0, 0);
			} else if (currentState == RELEASE_STATE) {
				// 刷新数据
				ll_refresh_head_root.setPadding(0, 0, 0, 0);
				currentState = REFRESHING;// 改变状态为正在刷新数据的状态
				refreshState();// 更新 刷新界面
				// 真正的 刷新数据
				if (listener != null) {
					listener.refresdData();
				}
			}
			break;
		default:
			break;
		}

		// 本方法没有覆盖的方法仍然 按照父类方法去调用。
		return super.onTouchEvent(ev);
	}

	/**
	 * 新闻中心 新闻listview数据更新监听器
	 * 
	 * @param listener
	 */
	public void setOnRefreshDataListener(OnRefreshDataListener listener) {
		this.listener = listener;
	}

	/**
	 * @author ltf
	 * @创建时间2016-6-3下午4:15:58
	 * @工程名SmartBJ
	 * @描述 刷新数据的接口监听器
	 * @svn提交者：$Auther$
	 * @提交时间：${date ${time}
	 * @当前版本：$Rev$
	 */
	public interface OnRefreshDataListener {
		void refresdData();

		void loadingMore();// 加载更多数据
	}

	private void initAnimation() {
		// 往上的动画
		up_ra = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		up_ra.setDuration(500);
		up_ra.setFillAfter(true);// 停留在动画结束的状态

		// 往下的动画
		down_ra = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		down_ra.setDuration(500);
		down_ra.setFillAfter(true);// 停留在动画结束的状态
	}

	/**
	 * 刷新状态的切换动画
	 */
	private void refreshState() {
		switch (currentState) {
		case PULL_DOWN:// 下拉刷新
			System.out.println("下拉刷新");
			// 改变文字
			tv_state.setText("下拉刷新");
			// 添加刷新状态的动画效果 向上动画
			iv_arrow.startAnimation(down_ra);
			break;
		case RELEASE_STATE:// 松开刷新
			System.out.println("松开刷新");
			tv_state.setText("松开刷新");
			// 添加刷新状态的动画效果 向下动画
			iv_arrow.startAnimation(up_ra);
			break;
		case REFRESHING:// 正在刷新状态
			iv_arrow.clearAnimation();// 清除所有动画
			iv_arrow.setVisibility(View.GONE);// 隐藏箭头
			pb_loading.setVisibility(View.VISIBLE);// 显示进度条
			tv_state.setText("正在刷新数据");

		default:
			break;
		}

	}

	/**
	 * 刷新数据成功 ,调用该方法处理结果
	 */
	public void refreshStateFinish() {
		// 下拉刷新
		if (isloadingMore) {
			// 加载更多数据
			isloadingMore=false;
			//隐藏加载更多数据的组件
		foot.setPadding(0, -ll_refresh_foot_Height, 0, 0);
		} else {
			// 改变下拉刷新
			tv_state.setText("下拉刷新");
			iv_arrow.setVisibility(View.VISIBLE);// 显示箭头
			pb_loading.setVisibility(View.INVISIBLE);// 隐藏进度条
			// 设置刷新时间为当前时间
			tv_time.setText(getCurrentFormatDate());
			// 隐藏 刷新的头布局
			ll_refresh_head_root.setPadding(0, -ll_refresh_head_root_Height, 0,
					0);

			currentState = PULL_DOWN;// 初始化为下拉刷新的状态
		}

	}

	/**
	 * @return 获取当前时间
	 */
	private String getCurrentFormatDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * @return 轮播图是否完全显示
	 */
	private boolean isLunboFullShow() {
		// TODO Auto-generated method stub
		// 判断轮播图是否完全显示

		int[] location = new int[2];
		// 如果轮播图没有完全显示，相应的是Listview的事件
		// 判断轮播图是否完全显示
		// 取listview在屏幕中坐标 和 轮播图在屏幕中的坐标 判断
		// 取listview在屏幕中坐标
		if (listViewOnScreanY == 0) {// 说明还没有获取listview的坐标，下一步进行判断
			this.getLocationOnScreen(location);
			// 获取listview在屏幕中的y轴坐标
			listViewOnScreanY = location[1];
		}

		// 轮播图在屏幕中的坐标
		lunbotu.getLocationOnScreen(location);
		// 判断
		if (location[1] < listViewOnScreanY) {
			// 轮播图没有完全显示
			// 继续相应listview的事件
			// System.out.println("没有显示");
			return false;
		}
		return true;

	}

	/**
	 * 初始化尾部组件
	 */
	private void initFoot() {
		// 获取 listview 的尾部组件foot

		foot = View.inflate(getContext(), R.layout.listview_refresh_foot, null);

		// 测量尾部组件的高度 以（0,0）为坐标原点

		foot.measure(0, 0);

		// listview尾部组件的高度
		ll_refresh_foot_Height = foot.getMeasuredHeight();

		// 设置尾部组件foot的padding 每个组件都可以设置padding
		foot.setPadding(0, -ll_refresh_foot_Height, 0, 0);

		// 尾部组件foot 加载ListView中
		addFooterView(foot);
	}

	/**
	 * 用户自己选择是否启用下拉刷新头的功能
	 * 
	 * @param isPullrefresh
	 * 
	 *            true 启用下拉刷新 false 不用下拉刷新
	 * 
	 */
	public void setIsRefreshHead(boolean isPullrefresh) {
		isEnablePullRefresh = isPullrefresh;
	}

	/**
	 * @param view
	 *            加载 轮播图view
	 */
	@Override
	public void addHeaderView(View view) {
		// 判断 如果你使用下拉刷新 ，把头布局加下拉刷新的容器中，否则加载原生Listview的中
		if (isEnablePullRefresh) {
			// 启用下拉刷新
			// 轮播图的组件
			lunbotu = view;
			head.addView(view);
		} else {
			// 使用原生的ListView（即 父类listview）
			super.addHeaderView(view);
		}

	}

	/**
	 * 初始化头部组件
	 */
	private void initHead() {

		// View.flate(,,)将布局转化为组件
		head = (LinearLayout) View.inflate(getContext(),
				R.layout.listview_head_container, null);
		// listview刷新头的根布局
		ll_refresh_head_root = (LinearLayout) head
				.findViewById(R.id.ll_listview_head_root);

		// 获取刷新头布局的子组件
		// 刷新状态的文件描述
		tv_state = (TextView) head
				.findViewById(R.id.tv_listview_head_state_dec);
		// 最新的刷新时间

		tv_time = (TextView) head
				.findViewById(R.id.tv_listview_head_refresh_time);

		// 下拉刷新的箭头

		iv_arrow = (ImageView) head.findViewById(R.id.iv_listview_head_arrow);

		// 下拉刷新的进度

		pb_loading = (ProgressBar) head
				.findViewById(R.id.pb_listview_head_loading);

		// 隐藏刷新头的根布局，轮播图还要显示

		// 获取刷新头组件的高度 对view进行测量 测量坐标为（0,0）
		ll_refresh_head_root.measure(0, 0);

		// 获取测量后的高度
		ll_refresh_head_root_Height = ll_refresh_head_root.getMeasuredHeight();

		// 隐藏刷新头的根布局，轮播图还要显示 利用setPadding（）方法实现隐藏
		ll_refresh_head_root.setPadding(0, -ll_refresh_head_root_Height, 0, 0);

		// 加载头组件到 listview
		addHeaderView(head);
	}

}
