package co.id.cakap.network;

import java.util.Map;

import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.helper.Constant;
import io.reactivex.Flowable;
import retrofit2.http.Body;
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

    @GET("provinsi")
    Flowable<ApiResponseProvinsi> getProvinsi(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                              @Header(Constant.CAKAP_KEY_TEXT) String authorization);

    @FormUrlEncoded
    @POST("kota")
    Flowable<ApiResponseKota> getKota(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                      @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                      @Field(Constant.BODY_ID) String id);

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

    @FormUrlEncoded
    @POST("search_member_cashbill")
    Flowable<ApiResponseSearchMemberCashbill> postSearchMemberCashbill(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                       @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                       @Field(Constant.BODY_MEMBER_ID) String memberId);

    @FormUrlEncoded
    @POST("item_stc_cashbill")
    Flowable<ApiResponseItemCashbill> postItemCashbill(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                       @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                       @Field(Constant.BODY_USER_ID) String memberId);

    @POST("submit_cashbill_stc")
    Flowable<ApiResponseSubmitCashbill> postSubmitCashbill(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                           @Body Map<String, Object> param);

    @POST("submit_invoice_mb")
    Flowable<ApiResponseSubmitInvoiceToMb> postSubmitInvoiceToMb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                           @Body Map<String, Object> param);

    @POST("submit_invoice_bc")
    Flowable<ApiResponseSubmitInvoiceToBc> postSubmitInvoiceToBc(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                 @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                 @Body Map<String, Object> param);

    @POST("submit_invoice_comp")
    Flowable<ApiResponseSubmitInvoiceToCompany> postSubmitInvoiceToCompany(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                           @Body Map<String, Object> param);

    @FormUrlEncoded
    @POST("search_mb_invoice")
    Flowable<ApiResponseSearchMbInvoice> postSearchMbInvoice(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                             @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                             @Field(Constant.BODY_USER_ID) String userId,
                                                             @Field(Constant.BODY_NO_STC) String noStc);

    @FormUrlEncoded
    @POST("item_stc_invoice")
    Flowable<ApiResponseItemInvoiceToMb> postItemInvoiceToMb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                             @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                             @Field(Constant.BODY_USER_ID) String userId,
                                                             @Field(Constant.BODY_MEMBER_ID2) String memberId);

    @FormUrlEncoded
    @POST("rekap_bonus_bcmb")
    Flowable<ApiResponseRekapBonusBcmb> postRekapBonusBcmb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                           @Field(Constant.BODY_USER_ID) String userId,
                                                           @Field(Constant.BODY_TAHUN) String tahun,
                                                           @Field(Constant.BODY_BULAN) String bulan);

    @FormUrlEncoded
    @POST("invoice_mb")
    Flowable<ApiResponseInvoiceToMb> postInvoiceToMb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                          @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                          @Field(Constant.BODY_USER_ID) String userId,
                                                          @Field(Constant.BODY_TAHUN) String tahun,
                                                          @Field(Constant.BODY_BULAN) String bulan,
                                                          @Field(Constant.GET_GROUP_ID) String groupId);

    @FormUrlEncoded
    @POST("submit_cancel_cashbill")
    Flowable<ApiResponseCancelItemCashbill> postCancelItemCashbill(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                   @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                   @Field(Constant.BODY_USER_ID) String userId,
                                                                   @Field(Constant.BODY_USER_NAME) String username,
                                                                   @Field(Constant.BODY_PIN) String pin,
                                                                   @Field(Constant.BODY_SO_ID) String soId);

    @FormUrlEncoded
    @POST("submit_cancel_invoice")
    Flowable<ApiResponseCancelItemInvoiceToMb> postCancelItemInvoiceToMb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                         @Field(Constant.BODY_USER_ID) String userId,
                                                                         @Field(Constant.BODY_USER_NAME) String username,
                                                                         @Field(Constant.BODY_PIN) String pin,
                                                                         @Field(Constant.BODY_SO_ID) String soId);

    @FormUrlEncoded
    @POST("req_invoice_mb")
    Flowable<ApiResponseActivityReqInvoiceMb> postListReqInvMb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                               @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                               @Field(Constant.BODY_USER_ID) String userId,
                                                               @Field(Constant.GET_GROUP_ID) String groupId);

    @FormUrlEncoded
    @POST("submit_req_invoice_mb")
    Flowable<ApiResponseActionReqInvMb> postActionReqInvMb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                           @Field(Constant.BODY_USER_ID) String userId,
                                                           @Field(Constant.BODY_USER_NAME) String username,
                                                           @Field(Constant.BODY_PIN) String pin,
                                                           @Field(Constant.BODY_ID) String id,
                                                           @Field(Constant.BODY_TIPE) String tipe);

    @FormUrlEncoded
    @POST("submit_rekap_bonus_bcmb_account_valid")
    Flowable<ApiResponseActionRekapBnsBcmb> postActionRekapBnsBcmb(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                   @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                   @Field(Constant.BODY_USER_NAME) String username,
                                                                   @Field(Constant.BODY_PIN) String pin,
                                                                   @Field(Constant.BODY_ID) String id);

    @FormUrlEncoded
    @POST("invoice_company")
    Flowable<ApiResponseRestockInvoice> postRestockInvoice(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                           @Field(Constant.BODY_USER_ID) String userId,
                                                           @Field(Constant.BODY_TAHUN) String tahun,
                                                           @Field(Constant.BODY_BULAN) String bulan);

    @FormUrlEncoded
    @POST("req_invoice_company")
    Flowable<ApiResponseRestockReqInvoice> postRestockReqInvoice(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                 @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                 @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("receive_stok")
    Flowable<ApiResponseRestockReceiveStock> postRestockReceiveStock(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                     @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                     @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("submit_receive_stok")
    Flowable<ApiResponseActionReceiveStock> postActionReceiveStock(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                   @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                   @Field(Constant.BODY_USER_ID) String userId,
                                                                   @Field(Constant.BODY_USER_NAME) String username,
                                                                   @Field(Constant.BODY_PIN) String pin,
                                                                   @Field(Constant.BODY_ID) String id);

    @FormUrlEncoded
    @POST("activation_code")
    Flowable<ApiResponseRegistrationList> postRegistrationList(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                  @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                  @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("search_member")
    Flowable<ApiResponseItemSearchRegistration> postItemSearchRegistration(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                           @Field(Constant.BODY_ID) String id);

    @FormUrlEncoded
    @POST("submit_registrasi")
    Flowable<ApiResponseSubmitRegistration> postRegistrationData(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                 @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                 @Field(Constant.BODY_USER_ID) String userId,
                                                                 @Field(Constant.BODY_USER_NAME) String username,
                                                                 @Field(Constant.BODY_PIN) String pin,
                                                                 @Field(Constant.BODY_RECRUITING) String recId,
                                                                 @Field(Constant.BODY_SPONSOR) String sponsorId,
                                                                 @Field(Constant.BODY_MEMBER_ID2) String memberId,
                                                                 @Field(Constant.BODY_NAME) String name,
                                                                 @Field(Constant.BODY_KTP) String ktp,
                                                                 @Field(Constant.BODY_JENIS_KELAMIN) String gender,
                                                                 @Field(Constant.BODY_TEMPAT_LAHIR) String pob,
                                                                 @Field(Constant.BODY_TGL_LAHIR) String dob,
                                                                 @Field(Constant.BODY_RELIGION) String religion,
                                                                 @Field(Constant.BODY_EMAIL) String email,
                                                                 @Field(Constant.BODY_TELP) String telp,
                                                                 @Field(Constant.BODY_HP) String hp,
                                                                 @Field(Constant.BODY_ALAMAT) String alamat,
                                                                 @Field(Constant.BODY_KOTA_ID) String kotaId,
                                                                 @Field(Constant.BODY_KODE_POS) String kodePos,
                                                                 @Field(Constant.BODY_ACTIVATION) String activation,
                                                                 @Field(Constant.BODY_AHLI_WARIS) String ahliWaris,
                                                                 @Field(Constant.BODY_HUBUNGAN) String hubungan,
                                                                 @Field(Constant.BODY_BANK_ID) String bankId,
                                                                 @Field(Constant.BODY_AREA) String cabang,
                                                                 @Field(Constant.BODY_ACCOUNT_HOLDER) String namaNasabah,
                                                                 @Field(Constant.BODY_NOREK) String norek,
//                                                                 kososng
                                                                 @Field(Constant.BODY_NPWP) String npwp,
                                                                 @Field(Constant.BODY_PEKERJAAN) String pekerjaan,
                                                                 @Field(Constant.BODY_STATUS_PERNIKAHAN) String statusPernikahan,
                                                                 @Field(Constant.BODY_PASANGAN) String pasangan);

    @FormUrlEncoded
    @POST("address_stc")
    Flowable<ApiResponseListAddress> postListAlamat(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                         @Field(Constant.BODY_USER_ID) String userId);

    @FormUrlEncoded
    @POST("submit_alamat")
    Flowable<ApiResponseAddEditAddress> postSubmitAlamat(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                         @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                         @Field(Constant.BODY_USER_ID) String userId,
                                                         @Field(Constant.BODY_ALAMAT) String alamat,
                                                         @Field(Constant.BODY_KOTA_ID) String kotaId,
                                                         @Field(Constant.BODY_USER_NAME) String username);
    @FormUrlEncoded
    @POST("submit_edit_alamat")
    Flowable<ApiResponseAddEditAddress> postEditAlamat(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                       @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                       @Field(Constant.BODY_USER_ID) String userId,
                                                       @Field(Constant.BODY_ALAMAT) String alamat,
                                                       @Field(Constant.BODY_KOTA_ID) String kotaId,
                                                       @Field(Constant.BODY_USER_NAME) String username,
                                                       @Field(Constant.BODY_ID_ALAMAT) String idAlamat);
    @FormUrlEncoded
    @POST("item_req_invoice")
    Flowable<ApiResponseItemInvoiceToCompany> postItemInvoiceToCompany(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                       @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                       @Field(Constant.BODY_TIMUR) String timur);
    @FormUrlEncoded
    @POST("get_activation_form")
    Flowable<ApiResponseActivationFormData> postItemActivationForm(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                   @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                   @Field(Constant.BODY_ID) String id,
                                                                   @Field(Constant.BODY_USER_ID) String userId);
    @FormUrlEncoded
    @POST("asd")
    Flowable<ApiResponseActivationFormSubmitData> postSubmitActivationForm(@Header(Constant.CONTENT_TYPE_TEXT) String contentType,
                                                                           @Header(Constant.CAKAP_KEY_TEXT) String authorization,
                                                                           @Field(Constant.BODY_USER_ID) String userId);
}
