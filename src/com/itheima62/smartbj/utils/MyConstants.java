package com.itheima62.smartbj.utils;

/**
 * @author ltf
 * @创建时间2016-6-8上午9:51:19
 * @工程名SmartBJ
 * @描述              常用 属性   属性有顺序
 * @svn提交者：$Auther$
 * @提交时间：${date}${time}
 * @当前版本：$Rev$
 */
public interface MyConstants 
{
	//apk发布修改该ip ip 或者 域名 www.henhao.com/zhbj/categories.json
	String SERVERURL = "http://10.0.2.2:8080/zhbj";//服务的url
	String NEWSCENTERURL = SERVERURL + "/categories.json";//新闻中心的url
	String PHOTOSURL = SERVERURL + "/photos/photos_1.json";//组图的url
	String CONFIGFILE = "cachevalue";//sp的文件名
	String ISSETUP = "issetup";//是否设置向导界面设置过数据
	String READNEWSIDS = "readnewsids";//保存    读过的新闻id
}
