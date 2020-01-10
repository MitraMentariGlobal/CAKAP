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
                                         @Field(Constant.BODY_USER_NAME) String userId,
                                         @Field(Constant.BODY_PASSWORD) String password,
                                         @Field(Constant.BODY_FCM_TOKEN) String fcmToken);

    @FormUrlEncoded
    @POST("logout_mobile")
    Flowable<ApiResponseLogout> postLogout(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                @Field(Constant.BODY_FCM_TOKEN) String fcmToken,
                                                @Field(Constant.BODY_SESSION_TOKEN) String sessionToken);

    @FormUrlEncoded
    @POST("cp_member")
    Flowable<ApiResponseChangePassword> postChangePassword(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                @Field(Constant.BODY_OLD_PASSWORD) String oldPassword,
                                                @Field(Constant.BODY_NEW_PASSWORD) String newPassword,
                                                @Field(Constant.BODY_RETYPE_NEW_PASSWORD) String retypeNewPassword,
                                                @Field(Constant.BODY_PIN) String pin,
                                                @Field(Constant.BODY_USER_NAME) String username,
                                                @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("changepin_member")
    Flowable<ApiResponseChangePin> postChangePin(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                @Field(Constant.BODY_OLD_PIN) String oldPin,
                                                @Field(Constant.BODY_NEW_PIN) String newPin,
                                                @Field(Constant.BODY_RETYPE_NEW_PIN) String retypeNewPin,
                                                @Field(Constant.BODY_USER_NAME) String username,
                                                @Field(Constant.BODY_USER_ID) String userId);

    @GET("member_profile")
    Flowable<ApiResponseProfileData> getProfileData(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                    @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                    @Query("userid") String userId,
                                                    @Query("group_id") String groupId);

}
