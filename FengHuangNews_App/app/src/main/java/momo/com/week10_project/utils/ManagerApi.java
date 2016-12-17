package momo.com.week10_project.utils;

/**
 * 根据主机路径分类
 */
public class ManagerApi {

    //主机路径
    public static final String BASEURL="http://api.iclient.ifeng.com/";

    //新闻头条api (aciton  timestamp<--可省略)
    public static final String NEWS_TOP="ClientNews?id=SYLB10,SYDT10,SYRECOMMEND&gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";
    //新闻体育api(page=1 ++)
    public static final String NEWS_SPORT="ClientNews?id=TY43,FOCUSTY43,TYLIVE,TYTOPIC&gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";

    //直播内容展示api(page=1 ++)
    public static final String LIVE_CONTENT="ClientNews?id=ZBPD,ZBPDNS&gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";
    //直播界面电视台的api
    public static final String LIVE_CHANNEL="livechannel_logoinfo?gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";

    //精选视频api(page=1 ++)
    public static final String VIDEO_JX="ifengvideoList?gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";

    //除精选视频的api(page=1 ++  typeid=clientvideo_1 根据精选接口进行返回)
    public static final String VIDEO_OTHER="ifengvideoList?listtype=list&gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";

//=========================================================

    //搜索模块的主机路径
    public static final String SEARCH_BASEURL="http://api.3g.ifeng.com/";

    //进入搜索获取热门搜索内容的api(尚未写)
    public static final String SEARCH_HOTCONTENT="client_search_hotword?gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";

    //搜索的api（page=1 += k= 搜索的内容)  --不带凤凰号
    public static final String SEARCH_CONTENT="client_search_list?gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";

    //发现模块的我的订阅api  (page=1 ++)
    public static final String FIND_VAMPIRE="api_vampire_categroy_recommend?parentid=0&pagesize=20";


//=================================================

    //发现模块的主机路径
    public static final String FIND_BASEURL="http://api.irecommend.ifeng.com/";

    //发现模块的猜你喜欢api (action= up or down)
    public static final String FIND_YOULIKE="read.php?gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";
}
