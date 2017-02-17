##FengHuangNews

仿凤凰新闻app
最重要的事情放前面说，请用wifi测试,最好也下载凤凰新闻app对比。
---
关于作者：
RealMoMo
> 关于我，欢迎关注  
   微信：[Real_Mo]()  
   邮箱：momo_weiye@126.com
-------------
####开发目的: 
<br>1.练手</br>
<br>2.熟悉抓包</br>

###预览界面

<br>不动态就不放了，有需要可以直接下载MyApk安装运行。</br>


<br>新闻界面</br>
	<br> ![image](https://github.com/RealMoMo/FengHuangNews/blob/master/pic/pic1.png)</br>

<br> 直播界面</br>
 <br> ![image](https://github.com/RealMoMo/FengHuangNews/blob/master/pic/pic2.png)</br>

<br> 搜索界面</br>
  <br> ![image](https://github.com/RealMoMo/FengHuangNews/blob/master/pic/pic3.png)</br>

<br> 视频界面</br>
  <br>  ![image](https://github.com/RealMoMo/FengHuangNews/blob/master/pic/pic4.png)</br>

<br>  发现界面</br>
   <br>  ![image](https://github.com/RealMoMo/FengHuangNews/blob/master/pic/pic5.png)</br>

     
   

###开发环境
Android Studio2.0


### 下载安装
导入项目，重新配置适合你开发环境build.gradle文件
导入里面app模块，重新配置适合你开发环境build.gradle文件
注意：java-->momo包下才是主要代码，其他是第三方登录导进来的包。



###Thanks
Everyone who has contributed code and reported issues and pull requests!



###TODO
 * 1.所有listview点击item，没有写布局展示内容，都是用webview展示的。
 * 2.直播内容的视频接口，没有抓到。求好心人能提供下。
 * 3.新闻界面只写了两个模块--头条和体育。其他模块思路都是一致。（懂就可以了 ^_^）
 * 4.视频没有实现点击进去播放的功能。懂在listview播放视频，估计点击进去播放都是小事。
 * 5.发现模块的没有实现listview item点击功能，接口返回的数据，都不能用WebView展示。需要自己解析内容，写布局进行展示。
 * 6.我的模块只写用mob第三方的登录。
 * 7.各模块的listview，都没有写什么双击头部回到顶部的功能。（懒得写
 * 8.新闻模块添加item 默认添加RealMo的item,实现功能的样子而已。
 * 9.widget包下的iconview nameview共4个自定义，我渣应该用一个抽象类直接弄好就行。   （懒···心塞···）
 * 10.视频播放界面不太友好。


###Version
<br>1.0实现大致功能----2016.12.16</br>
<br>1.1完善注释、精简代码----2016.12.17</br>
<br>1.2修复新闻界面的bug----201701..07</br>
