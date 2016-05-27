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
 * @����ʱ��2016-5-24����4:50:14
 * @������SmartBJ
 * @����TODO
 * @git�ύ�ߣ�$Auther$
 * @�ύʱ�䣺${date ${time}
 * @��ǰ�汾��$Rev$
 */
public abstract class BaseFragment extends Fragment {

	protected MainActivity mainActivity;// ������

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mainActivity = (MainActivity) getActivity();// ��ȡfragment���ڵ�Activity
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = initView();//view
		return root;
	}

	/**
	 * ���븲�Ǵ˷��� ��ɽ������ʾ
	 * @return
	 */
	public abstract View initView();
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// ��ʼ���¼�������
		super.onActivityCreated(savedInstanceState);
		initData();//��ʼ������
		initEvent();//��ʼ���¼�
	}
	/**
	 * ���า�Ǵ˷���������ݵĳ�ʼ����
	 */
	public void initData(){
		
	};
	/**
	 * ���า�Ǵ˷�������¼������
	 */
	public void initEvent(){
		
	};

}
