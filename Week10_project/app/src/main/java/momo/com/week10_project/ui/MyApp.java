package momo.com.week10_project.ui;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 *
 * @author RealMo
 * @version 1.1
 *
 * 最重要的事情说只说一遍，请用wifi测试。
 *
 * 仿凤凰新闻app:
 * ToDo:
 * 1.本app没有做缓存功能。
 * 2.listview点击item，没有写布局展示内容，都是用webview展示的。
 * 3.直播内容的视频接口，没有抓到。求好心人能提供下。
 * 4.新闻界面只写了两个模块--头条和体育。其他模块思路都是一致。（懂就可以了 ^_^）
 * 5.视频没有实现点击进去播放的功能。懂在listview播放视频，估计点击进去播放都是小事。
 * 6.发现模块的没有实现listview item点击功能，接口返回的数据，都不能用WebView展示。需要自己解析内容，写布局进行展示。
 * 7.我的模块只写用mob第三方的登录。
 * 8.各模块的listview，都没有写什么双击头部回到顶部的功能。（懒得写
 * 9.新闻模块添加item 默认添加RealMo的item,实现功能的样子而已。
 * 10.webview的重定向的方法，我没有重写。若跳转自己手机浏览器，自己重写该方法即可。
 * 11.该app没怎么考虑性能优化问题。
 * 12.widget包下的iconview nameview共4个自定义，我渣应该用一个抽象类直接弄好就行。   （懒···心塞···）
 *
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化第三方登录(初始化一次即可，所以在Application进行)
        ShareSDK.initSDK(this);
    }
}
