package co.id.cakap.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public interface NetworkService {
    @GET("movie/popular")
    Flowable<ApiResponse> getData(@Query("api_key") String api_key);

}
