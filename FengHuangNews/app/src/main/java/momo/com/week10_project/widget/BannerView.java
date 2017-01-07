package momo.com.week10_project.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import momo.com.week10_project.R;

/**
 * 架包：https://github.com/youth5201314/banner
 *
 * 自定义Banner控件(该自定义控件若要绑定view，需要继承ViewGroup或其子类)
 * <p/>
 * 实现自动轮播效果
 * <p/>
 *
 */
public abstract class BannerView<T> extends FrameLayout{

    private Banner banner;
    private List<T> list;

    public BannerView(Context context,List<T> list) {
        super(context);
        init(list);
    }

    public BannerView(Context context, AttributeSet attrs,List<T> list) {
        super(context, attrs);
        init(list);
    }

    private void init(List<T> list){
        this.list = list;

        //加载布局  参数3：true xml定义banner与本自定义view绑定在一起
        LayoutInflater.from(getContext()).inflate(R.layout.news_banner_layout,this,true);
        //初始化控件
        banner = (Banner)findViewById(R.id.news_banner);
        //设置banner风格
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        setBannerContent();

    }


    class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //把path转换成ImageView
            Glide.with(getContext()).load(path).into(imageView);
        }
    }


    public void setBannerContent(){


    bindData(banner,list);

//        //设置banner的图片集合及标题集合
//        banner.setImages(imgUrls);
//        banner.setBannerTitles(titles);
//        //banner配置完成，开始轮播
//        banner.start();

    }

    /**
     * 设置轮播图片地址集合和标题集合
     * 最后，banner.start();即可
     * @param banner
     * @param list
     */
    public abstract void bindData(Banner banner,List<T> list);
}
