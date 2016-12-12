package momo.com.week10_project.utils;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class ManagerApi {

    //主机路径
    public static final String BASEURL="http://api.iclient.ifeng.com/";

    //新闻头条api (aciton  timestamp<--可省略)
    public static final String NEWS_TOP="ClientNews?id=SYLB10,SYDT10,SYRECOMMEND&gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";


    //直播内容展示api(page)
    public static final String LIVE_CONTENT="ClientNews?id=ZBPD,ZBPDNS&gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";
    //直播界面电视台的api
    public static final String LIVE_CHANNEL="livechannel_logoinfo?gv=5.4.0&av=5.4.0&uid=866500027180423&deviceid=866500027180423&proid=ifengnews&os=android_23&df=androidphone&vt=5&screen=1080x1920&publishid=6001&nw=wifi";


}
