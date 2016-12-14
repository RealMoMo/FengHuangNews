package momo.com.week10_project.entity;

import java.util.List;

/**
 * 解析视频的实体Entity
 *
 * 只有精选视频TypesEntity
 */
public class VideoEntity {

    /**
     * types : [{"id":"clientvideo_9","name":"娱乐","chType":"video","position":"down"},{"id":"clientvideo_18","name":"美食","chType":"video","position":"down"},{"id":"clientvideo_22","name":"锵锵三人行","chType":"video","position":"down"},{"id":"clientvideo_5","name":"军事","chType":"video","position":"up"},{"id":"clientvideo_24","name":"综艺","chType":"video","position":"up"},{"id":"clientvideo_10","name":"体育","chType":"video","position":"up"},{"id":"clientvideo_8","name":"生活","chType":"video","position":"up"},{"id":"clientvideo_25","name":"纪录片","chType":"video","position":"up"},{"id":"clientvideo_4","name":"社会","chType":"video","position":"up"},{"id":"clientvideo_27","name":"萌萌哒","chType":"video","position":"up"},{"id":"clientvideo_2","name":"段子","chType":"video","position":"down"},{"id":"clientvideo_3","name":"历史","chType":"video","position":"up"},{"id":"clientvideo_1","name":"美女","chType":"video","position":"down"},{"id":"clientvideo_23","name":"鲁豫有约 ","chType":"video","position":"down"},{"id":"fm","type":"fm","name":"音频","api":"http://api.3g.ifeng.com/api_fm_homepage","chType":"other","position":"up"},{"id":"phtv","type":"normal","name":"凤凰卫视","api":"http://newsvcsp.ifeng.com/vcsp/appData/news/recommend.do","chType":"other","position":"up"},{"id":"live","type":"normal","name":"直播","api":"http://api.iclient.ifeng.com/ClientNews?id=ZBPD","chType":"other","position":"up"}]
     * totalPage : 25
     * currentPage : 1
     * type : list
     * item : [{"praise":"5","tread":"5","playTime":"17","documentId":"video_0139852b-d6f9-4ac6-b671-454d10020b50","title":"普京见日媒前逗了逗安倍送他的狗 日方人员表情大亮","image":"http://d.ifengimg.com/w640_h360_q80/p1.ifengimg.com/a/2016_51/0d9882784e53c23.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p1.ifengimg.com/a/2016_51/0d9882784e53c23.jpg","guid":"0139852b-d6f9-4ac6-b671-454d10020b50","commentsall":204,"duration":76,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0139852b-d6f9-4ac6-b671-454d10020b50","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0139852b-d6f9-4ac6-b671-454d10020b50","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413998-102-008-1010.mp4","video_size":5124},{"praise":"64","tread":"2","playTime":"371203","documentId":"video_0144cbc3-a2b0-4fa6-bb19-9e17ba012893","title":"普京放话安倍：日本竟敢这样做 你们和中国比不了","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/bf461e611000f86.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/bf461e611000f86.jpg","guid":"0144cbc3-a2b0-4fa6-bb19-9e17ba012893","commentsall":46,"duration":127,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0144cbc3-a2b0-4fa6-bb19-9e17ba012893","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0144cbc3-a2b0-4fa6-bb19-9e17ba012893","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/13/4413275-102-078-2330.mp4","video_size":14572},{"praise":"6","tread":"0","playTime":"859","documentId":"video_016cbf6e-d535-45bf-996c-40874b4e7712","title":"杜特尔特开金嗓演绎男女对唱 还有点小羞涩呢","image":"http://d.ifengimg.com/w640_h360_q80/p3.ifengimg.com/a/2016_51/959f936fc58e04b.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p3.ifengimg.com/a/2016_51/959f936fc58e04b.jpg","guid":"016cbf6e-d535-45bf-996c-40874b4e7712","commentsall":19,"duration":218,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=016cbf6e-d535-45bf-996c-40874b4e7712","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=016cbf6e-d535-45bf-996c-40874b4e7712","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4414054-102-008-1040.mp4","video_size":14664},{"praise":"0","tread":"0","playTime":"26","documentId":"video_01400e9f-73d9-4092-999b-f03e9e762e73","title":"国台办批\u201c台独\u201d：甘当外国筹码 心态可耻","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/a35232f21205de1.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/a35232f21205de1.jpg","guid":"01400e9f-73d9-4092-999b-f03e9e762e73","commentsall":0,"duration":226,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01400e9f-73d9-4092-999b-f03e9e762e73","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01400e9f-73d9-4092-999b-f03e9e762e73","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4414050-102-078-1035.mp4","video_size":25868},{"praise":"0","tread":"1","playTime":"42","documentId":"video_01a5f9e8-700b-41a5-8cac-0f7c841376ac","title":"这都行？女子买冥币当美元存银行 成功取出9100元","image":"http://d.ifengimg.com/w640_h360_q80/p3.ifengimg.com/a/2016_51/16a96daf337ab1f.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p3.ifengimg.com/a/2016_51/16a96daf337ab1f.jpg","guid":"01a5f9e8-700b-41a5-8cac-0f7c841376ac","commentsall":49,"duration":46,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01a5f9e8-700b-41a5-8cac-0f7c841376ac","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01a5f9e8-700b-41a5-8cac-0f7c841376ac","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413877-102-008-0927.mp4","video_size":3087},{"praise":"0","tread":"0","playTime":"0","documentId":"video_b8921ed8-f216-444e-83e4-cd0925c7d99c","title":"Facebook年终盘点短片：2016是艰难的一年","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/ee15ca5651ab62b.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/ee15ca5651ab62b.jpg","guid":"b8921ed8-f216-444e-83e4-cd0925c7d99c","commentsall":10,"duration":157,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=b8921ed8-f216-444e-83e4-cd0925c7d99c","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=b8921ed8-f216-444e-83e4-cd0925c7d99c","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/13/1120724-102-998767-144346.mp4","video_size":10467},{"praise":"2165","tread":"21","playTime":"142110","documentId":"video_01c1d4a9-c4af-44f9-9b7d-32ec06917bef","title":"实拍台统派团体暴揍\u201c港独\u201d分子 大骂汉奸卖国贼","image":"http://d.ifengimg.com/w640_h360_q80/p2.ifengimg.com/a/2016_51/960d3053a3af78b.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p2.ifengimg.com/a/2016_51/960d3053a3af78b.jpg","guid":"01c1d4a9-c4af-44f9-9b7d-32ec06917bef","commentsall":4811,"duration":108,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01c1d4a9-c4af-44f9-9b7d-32ec06917bef","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01c1d4a9-c4af-44f9-9b7d-32ec06917bef","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413549-102-008-0705.mp4","video_size":7288},{"praise":"12","tread":"1","playTime":"31714","documentId":"video_0171cdf7-c145-11e6-8c88-002590c2aaeb","title":"台湾疯传蔡83项\"政绩\" 有几项堪称地表最强拍马屁","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/ab595177dac91b1.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/ab595177dac91b1.jpg","guid":"0171cdf7-c145-11e6-8c88-002590c2aaeb","commentsall":160,"duration":160,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0171cdf7-c145-11e6-8c88-002590c2aaeb","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0171cdf7-c145-11e6-8c88-002590c2aaeb","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/13/4413253-102-008-2312.mp4","video_size":10724},{"praise":"64","tread":"3","playTime":"50210","documentId":"video_01d1ac40-df7d-43b4-b76a-78d4df152b16","title":"性骚扰在当今社会无处不在 大家觉得很正常","image":"http://d.ifengimg.com/w640_h360_q80/p2.ifengimg.com/a/2016_51/071b6636f31782f.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p2.ifengimg.com/a/2016_51/071b6636f31782f.jpg","guid":"01d1ac40-df7d-43b4-b76a-78d4df152b16","commentsall":36,"duration":1497,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01d1ac40-df7d-43b4-b76a-78d4df152b16","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01d1ac40-df7d-43b4-b76a-78d4df152b16","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413661-102-798765-0756.mp4","video_size":102528},{"praise":"28","tread":"1","playTime":"80778","documentId":"video_016188f0-9cff-41bd-baf2-32eae7f65dff","title":"李连杰近况曝光 老态毕现竟自称离死亡不远了","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/4ba569b3bed5df1.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/4ba569b3bed5df1.jpg","guid":"016188f0-9cff-41bd-baf2-32eae7f65dff","commentsall":33,"duration":74,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=016188f0-9cff-41bd-baf2-32eae7f65dff","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=016188f0-9cff-41bd-baf2-32eae7f65dff","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413763-102-086-0835.mp4","video_size":8517},{"praise":"0","tread":"0","playTime":"17","documentId":"video_01f38344-956a-44f2-acd9-374f19f7be90","title":"嫩模靠和男性干这事免费环游世界 衣食住行全包了！","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/d02a50f805a4fd5.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/d02a50f805a4fd5.jpg","guid":"01f38344-956a-44f2-acd9-374f19f7be90","commentsall":15,"duration":93,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01f38344-956a-44f2-acd9-374f19f7be90","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01f38344-956a-44f2-acd9-374f19f7be90","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413789-102-008-0849.mp4","video_size":6286},{"praise":"11","tread":"8","playTime":"63668","documentId":"video_0148ef04-9856-422c-b919-8c993395e098","title":"下一秒她就被巨鳄拖进水里 友人失控尖叫","image":"http://d.ifengimg.com/w640_h360_q80/p1.ifengimg.com/a/2016_51/3cd0c344855fc1c.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p1.ifengimg.com/a/2016_51/3cd0c344855fc1c.jpg","guid":"0148ef04-9856-422c-b919-8c993395e098","commentsall":34,"duration":25,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0148ef04-9856-422c-b919-8c993395e098","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0148ef04-9856-422c-b919-8c993395e098","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413805-102-008-0853.mp4","video_size":1674},{"praise":"13","tread":"24","playTime":"8745","documentId":"video_01e2e51c-d694-4ac3-9130-04605baf98fb","title":"小伙把铁球烧红放到iphone上 神奇的一幕发生了","image":"http://d.ifengimg.com/w640_h360_q80/p0.ifengimg.com/a/2016_51/7c5f7adc8ae8408.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p0.ifengimg.com/a/2016_51/7c5f7adc8ae8408.jpg","guid":"01e2e51c-d694-4ac3-9130-04605baf98fb","commentsall":157,"duration":107,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01e2e51c-d694-4ac3-9130-04605baf98fb","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01e2e51c-d694-4ac3-9130-04605baf98fb","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/13/4413065-102-008-2059.mp4","video_size":7264},{"praise":"0","tread":"0","playTime":"2","documentId":"video_0120452e-96b6-4466-ae4a-7b62bf72ae67","title":"圣诞老人送福利！这是每个男生都爱的礼物吧","image":"http://d.ifengimg.com/w640_h360_q80/p3.ifengimg.com/a/2016_51/04acecd83f2b9b8.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p3.ifengimg.com/a/2016_51/04acecd83f2b9b8.jpg","guid":"0120452e-96b6-4466-ae4a-7b62bf72ae67","commentsall":4,"duration":89,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0120452e-96b6-4466-ae4a-7b62bf72ae67","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0120452e-96b6-4466-ae4a-7b62bf72ae67","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413667-102-008-0759.mp4","video_size":5919},{"praise":"1","tread":"0","playTime":"365","documentId":"video_015182da-ecd6-456e-a5d7-ed616f9c07e9","title":"3分钟一道养生菜：冬季进补良品药膳鸡汤","image":"http://d.ifengimg.com/w640_h360_q80/p1.ifengimg.com/a/2016_51/2c13e2b3f8e6899.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p1.ifengimg.com/a/2016_51/2c13e2b3f8e6899.jpg","guid":"015182da-ecd6-456e-a5d7-ed616f9c07e9","commentsall":1,"duration":161,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=015182da-ecd6-456e-a5d7-ed616f9c07e9","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=015182da-ecd6-456e-a5d7-ed616f9c07e9","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/12/4410516-102-086-1731.mp4","video_size":18553},{"praise":"14","tread":"2","playTime":"34302","documentId":"video_01ddab2f-c38d-4a82-a36c-b64e4587ddf7","title":"外国观众：我有5家酒店 王健林笑了笑：我有100家五星级","image":"http://d.ifengimg.com/w640_h360_q80/p3.ifengimg.com/a/2016_51/f28bc94eba97510.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p3.ifengimg.com/a/2016_51/f28bc94eba97510.jpg","guid":"01ddab2f-c38d-4a82-a36c-b64e4587ddf7","commentsall":97,"duration":232,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01ddab2f-c38d-4a82-a36c-b64e4587ddf7","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01ddab2f-c38d-4a82-a36c-b64e4587ddf7","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/13/4412934-102-008-1944.mp4","video_size":15554},{"praise":"0","tread":"0","playTime":"0","documentId":"video_0132b6a7-4221-4ab5-a639-c058682fab36","title":"专家：中国千万不能上日本当 日本就想中美干架","image":"http://d.ifengimg.com/w640_h360_q80/p1.ifengimg.com/a/2016_51/92694961095efc1.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p1.ifengimg.com/a/2016_51/92694961095efc1.jpg","guid":"0132b6a7-4221-4ab5-a639-c058682fab36","commentsall":5,"duration":265,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0132b6a7-4221-4ab5-a639-c058682fab36","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0132b6a7-4221-4ab5-a639-c058682fab36","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/08/04/126158-102-009-022959.mp4","video_size":17723},{"praise":"1","tread":"1","playTime":"20981","documentId":"video_0117a577-c146-11e6-9807-002590c2aaeb","title":"中国空军远海训练释一信号 震慑美日顺带敲打台湾","image":"http://d.ifengimg.com/w640_h360_q80/p3.ifengimg.com/a/2016_51/b05e45e68d2470e.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p3.ifengimg.com/a/2016_51/b05e45e68d2470e.jpg","guid":"0117a577-c146-11e6-9807-002590c2aaeb","commentsall":166,"duration":90,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0117a577-c146-11e6-9807-002590c2aaeb","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=0117a577-c146-11e6-9807-002590c2aaeb","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/13/4413261-102-008-2323.mp4","video_size":6036},{"praise":"0","tread":"0","playTime":"74","documentId":"video_01a4959b-84d4-40c5-a8a9-0d3198e7ca75","title":"特朗普要选普京铁哥们当国务卿 美鹰派巨头急眼","image":"http://d.ifengimg.com/w640_h360_q80/p2.ifengimg.com/a/2016_51/11a57024013e7bb.jpg","thumbnail":"http://d.ifengimg.com/w132_h94_q80/p2.ifengimg.com/a/2016_51/11a57024013e7bb.jpg","guid":"01a4959b-84d4-40c5-a8a9-0d3198e7ca75","commentsall":15,"duration":113,"shareUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01a4959b-84d4-40c5-a8a9-0d3198e7ca75","commentsUrl":"http://share.iclient.ifeng.com/sharenews.f?guid=01a4959b-84d4-40c5-a8a9-0d3198e7ca75","video_url":"https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413592-102-0725.mp4","video_size":9407}]
     */

    private int totalPage;
    private String currentPage;
    private String type;
    private List<TypesEntity> types;
    private List<ItemEntity> item;

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypes(List<TypesEntity> types) {
        this.types = types;
    }

    public void setItem(List<ItemEntity> item) {
        this.item = item;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public String getType() {
        return type;
    }

    public List<TypesEntity> getTypes() {
        return types;
    }

    public List<ItemEntity> getItem() {
        return item;
    }

    public static class TypesEntity {
        /**
         * id : clientvideo_9
         * name : 娱乐
         * chType : video
         * position : down
         */

        private String id;
        private String name;
        private String chType;
        private String position;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setChType(String chType) {
            this.chType = chType;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getChType() {
            return chType;
        }

        public String getPosition() {
            return position;
        }
    }

    public static class ItemEntity {
        /**
         * praise : 5
         * tread : 5
         * playTime : 17
         * documentId : video_0139852b-d6f9-4ac6-b671-454d10020b50
         * title : 普京见日媒前逗了逗安倍送他的狗 日方人员表情大亮
         * image : http://d.ifengimg.com/w640_h360_q80/p1.ifengimg.com/a/2016_51/0d9882784e53c23.jpg
         * thumbnail : http://d.ifengimg.com/w132_h94_q80/p1.ifengimg.com/a/2016_51/0d9882784e53c23.jpg
         * guid : 0139852b-d6f9-4ac6-b671-454d10020b50
         * commentsall : 204
         * duration : 76
         * shareUrl : http://share.iclient.ifeng.com/sharenews.f?guid=0139852b-d6f9-4ac6-b671-454d10020b50
         * commentsUrl : http://share.iclient.ifeng.com/sharenews.f?guid=0139852b-d6f9-4ac6-b671-454d10020b50
         * video_url : https://ips.ifeng.com/video19.ifeng.com/video09/2016/12/14/4413998-102-008-1010.mp4
         * video_size : 5124
         */

        private String praise;
        private String tread;
        private String playTime;
        private String documentId;
        private String title;
        private String image;
        private String thumbnail;
        private String guid;
        private String commentsall;
        private String duration;
        private String shareUrl;
        private String commentsUrl;
        private String video_url;
        private int video_size;

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public void setTread(String tread) {
            this.tread = tread;
        }

        public void setPlayTime(String playTime) {
            this.playTime = playTime;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public void setCommentsall(String commentsall) {
            this.commentsall = commentsall;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setVideo_size(int video_size) {
            this.video_size = video_size;
        }

        public String getPraise() {
            return praise;
        }

        public String getTread() {
            return tread;
        }

        public String getPlayTime() {
            return playTime;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getGuid() {
            return guid;
        }

        public String getCommentsall() {
            return commentsall;
        }

        public String getDuration() {
            return duration;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public String getVideo_url() {
            return video_url;
        }

        public int getVideo_size() {
            return video_size;
        }
    }
}
