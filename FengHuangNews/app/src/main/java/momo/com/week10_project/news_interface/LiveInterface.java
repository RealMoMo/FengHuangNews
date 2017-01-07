package momo.com.week10_project.news_interface;

import java.util.List;

import momo.com.week10_project.entity.LiveChannelEntity;
import momo.com.week10_project.entity.LiveContentEntity;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by RealMo on 2016-12-11.
 */
public interface LiveInterface {

    @GET(ManagerApi.LIVE_CONTENT)
    Call<List<LiveContentEntity>> getLiveContent(@Query("page")int page);

    @GET(ManagerApi.LIVE_CHANNEL)
    Call<LiveChannelEntity> getLiveChannel();

    @GET(ManagerApi.LIVE_CONTENT)
    Call<String> getLiveContentString(@Query("page")int page);
}
