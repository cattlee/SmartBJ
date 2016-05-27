package com.itheima62.smartbj.view;

import com.itheima62.smartbj.activity.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ltf
 * @创建时间2016-5-24下午4:50:14
 * @工程名SmartBJ
 * @描述TODO
 * @git提交者：$Auther$
 * @提交时间：${date ${time}
 * @当前版本：$Rev$
 */
public abstract class BaseFragment extends Fragment {

	protected MainActivity mainActivity;// 上下文

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mainActivity = (MainActivity) getActivity();// 获取fragment所在的Activity
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = initView();//view
		return root;
	}

	/**
	 * 必须覆盖此方法 完成界面的显示
	 * @return
	 */
	public abstract View initView();
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// 初始化事件和数据
		super.onActivityCreated(savedInstanceState);
		initData();//初始化数据
		initEvent();//初始化事件
	}
	/**
	 * 子类覆盖此方法完成数据的初始化。
	 */
	public void initData(){
		
	};
	/**
	 * 子类覆盖此方法完成事件的添加
	 */
	public void initEvent(){
		
	};

}
