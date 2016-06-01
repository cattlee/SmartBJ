package com.itheima62.smartbj.domain;

import java.util.List;

/**
 * @author ltf
 * @创建时间2016-5-31下午8:56:00
 * @工程名SmartBJ
 * @描述 新闻中心 页签对应的json数据
 * @svn提交者：$Auther$
 * @提交时间：${date ${time}
 * @当前版本：$Rev$
 */
public class TPINewsData {
	public int retcode;
	public TPINewsData_Data data;

	public class TPINewsData_Data {
		public String countcommenturl;
		public String more;//是否有更多的数据
		public String title;
		public List<TPINewsData_Data_ListNewsData> news;
		public List<TPINewsData_Data_TopicData> topic;
		public List<TPINewsData_Data_LunBoData> topnews;

		public class TPINewsData_Data_ListNewsData {
			public String commentlist;
			public String commenturl;
			public String id;
			public String listimage;
			public String pubdate;
			public String title;
			public String type;
			public String url;
		}

		public class TPINewsData_Data_TopicData {

			public String description;
			public String listimage;
			public String id;
			public String sort;
			public String title;
			public String url;
		}

		public class TPINewsData_Data_LunBoData {

			public String comment;
			public String commentlist;
			public String commenturl;
			public String id;
			public String pubdate;
			public String title;
			public String topimage;
			public String type;
			public String url;

		}

	}
}
