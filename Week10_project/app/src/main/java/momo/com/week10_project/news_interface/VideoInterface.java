package momo.com.week10_project.news_interface;

import java.util.List;

import momo.com.week10_project.entity.SearchEntity;
import momo.com.week10_project.entity.VideoEntity;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public interface VideoInterface {

    @GET(ManagerApi.VIDEO_JX)
    Call<List<VideoEntity>> getVideoEntity(@Query("page")int page);


    @GET(ManagerApi.VIDEO_OTHER)
    Call<List<VideoEntity>> getVideoOtherEntity(@Query("page")int page
                                            ,@Query("typeid")String typeid);

    @GET(ManagerApi.VIDEO_JX)
    Call<List<SearchEntity>> getTestoEntity(@Query("page")int page);



}
