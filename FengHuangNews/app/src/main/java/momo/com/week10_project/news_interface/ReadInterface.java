package momo.com.week10_project.news_interface;

import momo.com.week10_project.entity.ReadContentEntity;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public interface ReadInterface {

    @GET(ManagerApi.FIND_YOULIKE)
    Call<ReadContentEntity> getReadContent(@Query("action")String action);
}
