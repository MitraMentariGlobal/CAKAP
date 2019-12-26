package co.id.cakap.network;

import java.util.Map;

import co.id.cakap.helper.Constant;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public interface NetworkService {
    @FormUrlEncoded
    @POST("check_session")
    Flowable<ApiResponseLogin> postCheckLogin(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                @Field(Constant.BODY_FCM_TOKEN) String fcmToken,
                                                @Field(Constant.BODY_SESSION_TOKEN) String sessionToken);

    @FormUrlEncoded
    @POST("login")
    Flowable<ApiResponseLogin> postLogin(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                         @Field(Constant.BODY_USER_ID) String userId,
                                         @Field(Constant.BODY_PASSWORD) String password,
                                         @Field(Constant.BODY_FCM_TOKEN) String fcmToken);

    @FormUrlEncoded
    @POST("logout_mobile")
    Flowable<ApiResponseLogout> postLogout(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                @Field(Constant.BODY_FCM_TOKEN) String fcmToken,
                                                @Field(Constant.BODY_SESSION_TOKEN) String sessionToken);

}
