package momo.com.week10_project.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 *
 * 尚未使用
 * ---------------------------
 *
 * 自定义View,用来显示详情界面
 *
 * 界面格式：
 *
 * 标题
 *
 * 来源+时间
 *
 * --------------------------
 *
 * 内容
 */
public class NewsContentView extends LinearLayout{
    public NewsContentView(Context context) {
        super(context);
        init();
    }

    public NewsContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private  void init(){
        //固定垂直方向
        setOrientation(VERTICAL);
    }

    /**
     * 设置数据，绘制界面
     *
     */
    public void setData( String url){

//        if(bean==null){
//            return;
//        }
//
//        //===========绘制标题=============
//        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        textParams.setMargins(0,20,0,20);
//        TextView tv_title = new TextView(getContext());
//        //textsize 默认sp为单位
//        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.house_title_textsize));
//        tv_title.setText(bean.getTitle());
//        tv_title.setTextColor(Color.BLACK);
//        this.addView(tv_title,textParams);
//        //==========来源 时间=============
//        TextView tv_sourcetime = new TextView(getContext());
//        tv_sourcetime.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.house_time_textsize));
//        tv_sourcetime.setText(bean.getSource()+" "+bean.getTime());
//        this.addView(tv_sourcetime,textParams);
//        //===========分割线==============
//        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2);
//        View view = new View(getContext());
//        view.setBackgroundColor(Color.GRAY);
//        this.addView(view,params);
//        //============内容===============
//        List<HouseDetailBean.ContentEntity> content = bean.getContent();
//        for(HouseDetailBean.ContentEntity entity:content){
//            //取出数据，创建控件，设置数据
//            //根据数据类型，决定创建什么控件
//            int type = entity.getType();
//            String value =entity.getValue();
//            switch (type){
//                //文本内容
//                case 1:{
//                    //文本内容
//                    TextView tmp = new TextView(getContext());
//                    tmp.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.house_content_textsize));
//                    //设置文本行间距
//                    tmp.setText(value);
//                    //设置文本行间距
//                    tmp.setLineSpacing(1.8f,1.8f);
//                    //段落之间多空一行
////                    value=value.replaceAll("\\s{3,}","\\n\\n");
//                    addView(tmp,textParams);
//                }break;
//                //图片内容
//                case 2:{
//                    ImageView img = new ImageView(getContext());
//                    //加载图片
//                    Glide.with(getContext()).load(value).into(img);
//                    addView(img,textParams);
//                }break;
//
//            }
//        }


    }

    //把R.dimen中的尺寸转换成-->px
    private float getSize(int id){
        //返回的是px
        return getResources().getDimensionPixelSize(id);
    }
}
