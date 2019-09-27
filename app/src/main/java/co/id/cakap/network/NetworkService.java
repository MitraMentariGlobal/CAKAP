package co.id.cakap.network;

import java.util.Map;

import co.id.cakap.helper.Constant;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public interface NetworkService {
    @GET("movie/popular")
    Flowable<ApiResponse> getData(@Query("api_key") String api_key);

    @POST("check_session")
    Flowable<ApiResponseLogin> postCheckLogin(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                         @Body Map<String, Object> param);

    @POST("login")
    Flowable<ApiResponseLogin> postLogin(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                         @Body Map<String, Object> param);

}
