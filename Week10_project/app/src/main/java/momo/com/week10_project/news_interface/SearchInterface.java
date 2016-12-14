package momo.com.week10_project.news_interface;

import momo.com.week10_project.entity.SearchContentEntity;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public interface SearchInterface {




    @GET(ManagerApi.SEARCH_CONTENT)
    Call<SearchContentEntity> getSearchContent(@Query("page")int page,
                                                @Query("k")String k);
}
