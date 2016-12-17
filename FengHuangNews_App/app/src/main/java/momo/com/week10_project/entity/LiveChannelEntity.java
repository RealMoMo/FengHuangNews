package momo.com.week10_project.entity;

import java.util.List;

/**
 * 直播--电视台
 */
public class LiveChannelEntity {


    /**
     * liveInfo : [{"title":"info","cName":"资讯台","channelId":"4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5","video":"http://zv.3gv.ifeng.com/live/zixun.m3u8","videoInReview":"http://zv.3gv.ifeng.com/live/zixunV2.m3u8","videoH":"http://zv.3gv.ifeng.com/live/zixun800k.m3u8","videoM":"http://zv.3gv.ifeng.com/live/zixun500k.m3u8","videoL":"http://zv.3gv.ifeng.com/live/zixun350k.m3u8","audio":"http://zv.3gv.ifeng.com/live/zixun64kaudio.m3u8","bigIconURL":"http://y1.ifengimg.com/a/2014_10/fe6c5bb8cd6b425.png","smallIconURL":"","description":"凤凰卫视资讯台于2001年1月1日启播，是凤凰卫视全日24小时播放凤凰台来自全球各地时事新闻与财经资讯的频道。重点在两岸以至全球华人地区的新闻资讯报道及评论。","pcUrl":"http://v.ifeng.com/live/#4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5","mUrl":"http://v.ifeng.com/live/#4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5","img490_490":"http://y1.ifengimg.com/a/2016_16/94eb9bbac81aa7d.png","shareUrl":"http://share.iclient.ifeng.com/sharephtv?cid=4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5"},{"title":"chinese","cName":"中文台","channelId":"270DE943-3CDF-45E1-8445-9403F93E80C4","video":"http://zv.3gv.ifeng.com/live/zhongwen.m3u8","videoInReview":"http://zv.3gv.ifeng.com/live/zhongwenV2.m3u8","videoH":"http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8","videoM":"http://zv.3gv.ifeng.com/live/zhongwen500k.m3u8","videoL":"http://zv.3gv.ifeng.com/live/zhongwen350k.m3u8","audio":"http://zv.3gv.ifeng.com/live/zhongwen64kaudio.m3u8","bigIconURL":"http://y2.ifengimg.com/a/2014_10/cbf7297a47b1d6a.png","smallIconURL":"","description":"凤凰卫视旗下品牌。一九九六年三月三十一日启播，以\u201c拉近全球华人距离\u201d为宗旨，不断创新\u2022超越自己\u2022是凤凰卫视中文台节目制作的目标，全力为全世界华人提供高质素的华语电视节目。","pcUrl":"http://v.ifeng.com/live/#270DE943-3CDF-45E1-8445-9403F93E80C4","mUrl":"http://v.ifeng.com/live/#270DE943-3CDF-45E1-8445-9403F93E80C4","img490_490":"http://y2.ifengimg.com/a/2016_16/3c47ac7abc05e8b.png","shareUrl":"http://share.iclient.ifeng.com/sharephtv?cid=270DE943-3CDF-45E1-8445-9403F93E80C4"},{"title":"hongkong","cName":"香港台","channelId":"2c942450-2165-4750-80de-7dff9c224153","video":"http://zv.3gv.ifeng.com/live/hongkong.m3u8","videoInReview":"http://zv.3gv.ifeng.com/live/hongkongV2.m3u8","videoH":"http://zv.3gv.ifeng.com/live/hongkong800k.m3u8","videoM":"http://zv.3gv.ifeng.com/live/hongkong500k.m3u8","videoL":"http://zv.3gv.ifeng.com/live/hongkong350k.m3u8","audio":"http://zv.3gv.ifeng.com/live/hongkong64kaudio.m3u8","bigIconURL":"http://y0.ifengimg.com/a/2014_10/bb899c2b0bbaf6a.png","smallIconURL":"","description":"香港台是以一批土生土长、又熟悉内地情况的凤凰香港同事为主导，并会招聘香港精英，增加香港本地的新闻报道内容和记者力量；以更贴近港人口味，让内地领导及官员更准确地掌握香港情况，加快两地的融合。","pcUrl":"http://v.ifeng.com/live/#2c942450-2165-4750-80de-7dff9c224153","mUrl":"http://v.ifeng.com/live/#2c942450-2165-4750-80de-7dff9c224153","img490_490":"http://y0.ifengimg.com/a/2016_16/1711140b6b2491f.png","shareUrl":"http://share.iclient.ifeng.com/sharephtv?cid=2c942450-2165-4750-80de-7dff9c224153"},{"title":"tjws","cName":"天津卫视","channelId":"35383695-26c3-4ce5-b535-0001abce11e4","video":"http://zv.3gv.ifeng.com/live/CQWS.m3u8","videoInReview":"http://zv.3gv.ifeng.com/live/CQWS.m3u8","videoH":"","videoM":"http://zv.3gv.ifeng.com/live/CQWS.m3u8","videoL":"","audio":"http://zv.3gv.ifeng.com/live/166.m3u8","bigIconURL":"http://y0.ifengimg.com/a/2014_10/70d66af793ea9d1.png","smallIconURL":"","description":"天津卫视，定位是\u201c立足天津，面向三北，放眼全国，走向世界\u201d；让世界更好地了解天津，让天津进一步走向世界。","pcUrl":"http://v.ifeng.com/live/#35383695-26c3-4ce5-b535-0001abce11e4","mUrl":"http://v.ifeng.com/live/#35383695-26c3-4ce5-b535-0001abce11e4","img490_490":"http://y2.ifengimg.com/a/2016_16/18d2325f353f669.png","shareUrl":"http://share.iclient.ifeng.com/sharephtv?cid=35383695-26c3-4ce5-b535-0001abce11e4"}]
     */

    private List<LiveInfoEntity> liveInfo;

    public void setLiveInfo(List<LiveInfoEntity> liveInfo) {
        this.liveInfo = liveInfo;
    }

    public List<LiveInfoEntity> getLiveInfo() {
        return liveInfo;
    }

    public static class LiveInfoEntity {
        /**
         * title : info
         * cName : 资讯台
         * channelId : 4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5
         * video : http://zv.3gv.ifeng.com/live/zixun.m3u8
         * videoInReview : http://zv.3gv.ifeng.com/live/zixunV2.m3u8
         * videoH : http://zv.3gv.ifeng.com/live/zixun800k.m3u8
         * videoM : http://zv.3gv.ifeng.com/live/zixun500k.m3u8
         * videoL : http://zv.3gv.ifeng.com/live/zixun350k.m3u8
         * audio : http://zv.3gv.ifeng.com/live/zixun64kaudio.m3u8
         * bigIconURL : http://y1.ifengimg.com/a/2014_10/fe6c5bb8cd6b425.png
         * smallIconURL :
         * description : 凤凰卫视资讯台于2001年1月1日启播，是凤凰卫视全日24小时播放凤凰台来自全球各地时事新闻与财经资讯的频道。重点在两岸以至全球华人地区的新闻资讯报道及评论。
         * pcUrl : http://v.ifeng.com/live/#4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5
         * mUrl : http://v.ifeng.com/live/#4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5
         * img490_490 : http://y1.ifengimg.com/a/2016_16/94eb9bbac81aa7d.png
         * shareUrl : http://share.iclient.ifeng.com/sharephtv?cid=4AC51C17-9FBE-47F2-8EE0-8285A66EAFF5
         */

        private String title;
        private String cName;
        private String channelId;
        private String video;
        private String videoInReview;
        private String videoH;
        private String videoM;
        private String videoL;
        private String audio;
        private String bigIconURL;
        private String smallIconURL;
        private String description;
        private String pcUrl;
        private String mUrl;
        private String img490_490;
        private String shareUrl;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCName(String cName) {
            this.cName = cName;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public void setVideoInReview(String videoInReview) {
            this.videoInReview = videoInReview;
        }

        public void setVideoH(String videoH) {
            this.videoH = videoH;
        }

        public void setVideoM(String videoM) {
            this.videoM = videoM;
        }

        public void setVideoL(String videoL) {
            this.videoL = videoL;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public void setBigIconURL(String bigIconURL) {
            this.bigIconURL = bigIconURL;
        }

        public void setSmallIconURL(String smallIconURL) {
            this.smallIconURL = smallIconURL;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPcUrl(String pcUrl) {
            this.pcUrl = pcUrl;
        }

        public void setMUrl(String mUrl) {
            this.mUrl = mUrl;
        }

        public void setImg490_490(String img490_490) {
            this.img490_490 = img490_490;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getCName() {
            return cName;
        }

        public String getChannelId() {
            return channelId;
        }

        public String getVideo() {
            return video;
        }

        public String getVideoInReview() {
            return videoInReview;
        }

        public String getVideoH() {
            return videoH;
        }

        public String getVideoM() {
            return videoM;
        }

        public String getVideoL() {
            return videoL;
        }

        public String getAudio() {
            return audio;
        }

        public String getBigIconURL() {
            return bigIconURL;
        }

        public String getSmallIconURL() {
            return smallIconURL;
        }

        public String getDescription() {
            return description;
        }

        public String getPcUrl() {
            return pcUrl;
        }

        public String getMUrl() {
            return mUrl;
        }

        public String getImg490_490() {
            return img490_490;
        }

        public String getShareUrl() {
            return shareUrl;
        }
    }
}
