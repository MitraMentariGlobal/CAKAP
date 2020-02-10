package co.id.cakap.network;

import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.helper.Constant;
import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

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
                                                    @Query(Constant.BODY_USER_ID) String userId,
                                                    @Query(Constant.GET_GROUP_ID) String groupId);

    @GET("jk")
    Flowable<ApiResponseJenisKelamin> getJenisKelamin(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                      @Header(Constant.CAKAP_KEY_TEXT) String authorization);

    @GET("religion")
    Flowable<ApiResponseReligion> getReligion(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                              @Header(Constant.CAKAP_KEY_TEXT) String authorization);

    @GET("bank")
    Flowable<ApiResponseBank> getBank(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                      @Header(Constant.CAKAP_KEY_TEXT) String authorization);

    @FormUrlEncoded
    @POST("update_profile")
    Flowable<ApiResponseUpdateProfile> postUpdateProfile(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                         @Field(Constant.BODY_NO_KTP) String noKtp,
                                                         @Field(Constant.BODY_ALAMAT) String alamat,
                                                         @Field(Constant.BODY_KODE_POS) String kodePos,
                                                         @Field(Constant.BODY_NPWP) String npwp,
                                                         @Field(Constant.BODY_STATUS_PERNIKAHAN) String statusPernikahan,
                                                         @Field(Constant.BODY_SUAMI) String suami,
                                                         @Field(Constant.BODY_RELIGION) String religion,
                                                         @Field(Constant.BODY_ANAK) String anak,
                                                         @Field(Constant.BODY_PEKERJAAN) String pekerjaan,
                                                         @Field(Constant.BODY_HUBUNGAN) String hubungan,
                                                         @Field(Constant.BODY_AHLI_WARIS) String ahliWaris,
                                                         @Field(Constant.BODY_CITY) String city,
                                                         @Field(Constant.BODY_EMAIL) String email,
                                                         @Field(Constant.BODY_TEMPAT_LAHIR) String pob,
                                                         @Field(Constant.BODY_JENIS_KELAMIN) String gender,
                                                         @Field(Constant.BODY_DATE) String date,
                                                         @Field(Constant.BODY_HP) String hp,
                                                         @Field(Constant.BODY_TELP) String telp,
                                                         @Field(Constant.BODY_FAX) String fax,
                                                         @Field(Constant.BODY_KOTA_ID) String kotaId,
                                                         @Field(Constant.BODY_PROVINCE) String province,
                                                         @Field(Constant.BODY_BANK_ACC) String bankAcc,
                                                         @Field(Constant.BODY_BANK_ID) String bankId,
                                                         @Field(Constant.BODY_NOREK) String norek,
                                                         @Field(Constant.BODY_CABANG) String cabang,
                                                         @Field(Constant.BODY_AREA) String area,
                                                         @Field(Constant.BODY_USER_NAME) String username,
                                                         @Field(Constant.BODY_NAMA) String nama,
                                                         @Field(Constant.BODY_PIN) String pin);

    @FormUrlEncoded
    @POST("cashbill")
    Flowable<ApiResponseActivityCashbill> postActivityCashbill(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                               @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                               @Field(Constant.BODY_USER_ID) String userId,
                                                               @Field(Constant.BODY_TAHUN) String tahun,
                                                               @Field(Constant.BODY_BULAN) String bulan,
                                                               @Field(Constant.GET_GROUP_ID) String groupId);

    @FormUrlEncoded
    @POST()
    Flowable<ApiResponseDetailTransaction> postDetailTransaction(@Url String url,
                                                                 @Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                 @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                 @Field(Constant.BODY_ID) String id);

    @FormUrlEncoded
    @POST("bonus_statement")
    Flowable<ApiResponseBonusStatementData> postActivityBonusStatement(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                       @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                       @Field(Constant.BODY_USER_ID) String userId,
                                                                       @Field(Constant.BODY_TAHUN) String tahun,
                                                                       @Field(Constant.BODY_BULAN) String bulan);

    @FormUrlEncoded
    @POST("network_geneology")
    Flowable<ApiResponseNetworkGeneology> postNetworkGenealogy(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                               @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                               @Field(Constant.BODY_USER_ID) String userId,
                                                               @Field(Constant.BODY_MEMBER_ID) String memberId);

    @FormUrlEncoded
    @POST("level")
    Flowable<ApiResponseLevel> postLevel(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                         @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("downline_listing")
    Flowable<ApiResponseDownlineListing> postDownlineListing(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                             @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                             @Field(Constant.BODY_USER_ID) String userId,
                                                             @Field(Constant.BODY_LEVEL) String level);

    @FormUrlEncoded
    @POST("report_bulanan")
    Flowable<ApiResponseMonthlyPointReport> postMonthlyPointReport(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                   @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                   @Field(Constant.BODY_USER_ID) String userId,
                                                                   @Field(Constant.BODY_TAHUN) String tahun,
                                                                   @Field(Constant.BODY_BULAN) String bulan);

    @FormUrlEncoded
    @POST("feestc")
    Flowable<ApiResponseFeeBcmb> postFeeBcmb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                             @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                             @Field(Constant.BODY_USER_ID) String userId,
                                             @Field(Constant.BODY_TAHUN) String tahun,
                                             @Field(Constant.BODY_BULAN) String bulan);

    @FormUrlEncoded
    @POST("stock_stc")
    Flowable<ApiResponseStockReportUpdate> postStockReportUpdate(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                 @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                 @Field(Constant.BODY_USER_ID) String userId,
                                                                 @Field(Constant.BODY_TIPE) String tipe);

    @FormUrlEncoded
    @POST("stock_stc")
    Flowable<ApiResponseStockReportCard> postStockReportCard(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                             @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                             @Field(Constant.BODY_USER_ID) String userId,
                                                             @Field(Constant.BODY_TIPE) String tipe,
                                                             @Field(Constant.BODY_TAHUN) String tahun,
                                                             @Field(Constant.BODY_BULAN) String bulan,
                                                             @Field(Constant.BODY_ITEM_ID) String itemId,
                                                             @Field(Constant.BODY_PRICE) String price);

    @FormUrlEncoded
    @POST("item_stc")
    Flowable<ApiResponseStockReportCardItem> postStockReportCardItem(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                     @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                     @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("omset_stc")
    Flowable<ApiResponseOmset> postOmset(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                         @Field(Constant.BODY_USER_ID) String userId,
                                         @Field(Constant.BODY_TAHUN) String tahun,
                                         @Field(Constant.BODY_BULAN) String bulan);

    @FormUrlEncoded
    @POST("search_member_stc")
    Flowable<ApiResponseSearchMember> postSearchMember(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                       @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                       @Field(Constant.BODY_USER_ID) String userId,
                                                       @Field(Constant.BODY_KEYWORD) String keyword);


}
