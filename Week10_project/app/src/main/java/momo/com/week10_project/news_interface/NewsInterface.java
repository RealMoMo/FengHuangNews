package momo.com.week10_project.news_interface;

import java.util.List;

import momo.com.week10_project.entity.NewsTopEntity;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by RealMo on 2016-12-11.
 */
public interface NewsInterface {

    @GET(ManagerApi.NEWS_TOP)
    Call<List<NewsTopEntity>> getTopEntity(@Query("action")String action);
}
