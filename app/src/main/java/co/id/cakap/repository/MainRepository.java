package co.id.cakap.repository;

import java.util.Map;

import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.data.ReligionData;
import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponseActionReceiveStock;
import co.id.cakap.network.ApiResponseActionRekapBnsBcmb;
import co.id.cakap.network.ApiResponseActionReqInvMb;
import co.id.cakap.network.ApiResponseActivationFormData;
import co.id.cakap.network.ApiResponseActivationFormSubmitData;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseActivityReqInvoiceMb;
import co.id.cakap.network.ApiResponseAddEditAddress;
import co.id.cakap.network.ApiResponseBank;
import co.id.cakap.network.ApiResponseBonusStatementData;
import co.id.cakap.network.ApiResponseCancelItemCashbill;
import co.id.cakap.network.ApiResponseCancelItemInvoiceToMb;
import co.id.cakap.network.ApiResponseChangePassword;
import co.id.cakap.network.ApiResponseChangePin;
import co.id.cakap.network.ApiResponseDetailTransaction;
import co.id.cakap.network.ApiResponseDownlineListing;
import co.id.cakap.network.ApiResponseFeeBcmb;
import co.id.cakap.network.ApiResponseInvoiceToMb;
import co.id.cakap.network.ApiResponseItemCashbill;
import co.id.cakap.network.ApiResponseItemInvoiceToCompany;
import co.id.cakap.network.ApiResponseItemInvoiceToMb;
import co.id.cakap.network.ApiResponseItemSearchRegistration;
import co.id.cakap.network.ApiResponseJenisKelamin;
import co.id.cakap.network.ApiResponseKota;
import co.id.cakap.network.ApiResponseLevel;
import co.id.cakap.network.ApiResponseListAddress;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.ApiResponseLogout;
import co.id.cakap.network.ApiResponseMonthlyPointReport;
import co.id.cakap.network.ApiResponseNetworkGeneology;
import co.id.cakap.network.ApiResponseOmset;
import co.id.cakap.network.ApiResponseProfileData;
import co.id.cakap.network.ApiResponseProvinsi;
import co.id.cakap.network.ApiResponseRegistrationList;
import co.id.cakap.network.ApiResponseRekapBonusBcmb;
import co.id.cakap.network.ApiResponseReligion;
import co.id.cakap.network.ApiResponseRestockInvoice;
import co.id.cakap.network.ApiResponseRestockReceiveStock;
import co.id.cakap.network.ApiResponseRestockReqInvoice;
import co.id.cakap.network.ApiResponseSearchMbInvoice;
import co.id.cakap.network.ApiResponseSearchMember;
import co.id.cakap.network.ApiResponseSearchMemberCashbill;
import co.id.cakap.network.ApiResponseStockReportCard;
import co.id.cakap.network.ApiResponseStockReportCardItem;
import co.id.cakap.network.ApiResponseStockReportUpdate;
import co.id.cakap.network.ApiResponseSubmitCashbill;
import co.id.cakap.network.ApiResponseSubmitInvoiceToBc;
import co.id.cakap.network.ApiResponseSubmitInvoiceToCompany;
import co.id.cakap.network.ApiResponseSubmitInvoiceToMb;
import co.id.cakap.network.ApiResponseSubmitRegistration;
import co.id.cakap.network.ApiResponseUpdateProfile;
import co.id.cakap.network.NetworkService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class MainRepository extends BaseRepository {
    public MainRepository(NetworkService networkService) {
        super(networkService);
    }

    public Flowable<ApiResponseLogin> postCheckLogin(String fcmToken, String sessionToken) {
        return networkService.postCheckLogin(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, fcmToken, sessionToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseLogin> postLogin(String userId, String password, String fcmToken) {
        return networkService.postLogin(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, password, fcmToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseLogout> postLogout(String fcmToken, String sessionToken) {
        return networkService.postLogout(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, fcmToken, sessionToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseChangePassword> postChangePassword(String oldPassword, String newPassword, String retypeNewPassword, String pin, String username, String userId) {
        return networkService.postChangePassword(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, oldPassword, newPassword, retypeNewPassword, pin, username, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseChangePin> postChangePin(String oldPin, String newPin, String retypeNewPin, String username, String userId) {
        return networkService.postChangePin(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, oldPin, newPin, retypeNewPin, username, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseProfileData> getProfileData(String userId, String groupId) {
        return networkService.getProfileData(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, groupId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseJenisKelamin> getJenisKelamin() {
        return networkService.getJenisKelamin(Constant.CONTENT_TYPE, Constant.CAKAP_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseReligion> getReligion() {
        return networkService.getReligion(Constant.CONTENT_TYPE, Constant.CAKAP_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseBank> getBank() {
        return networkService.getBank(Constant.CONTENT_TYPE, Constant.CAKAP_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseProvinsi> getProvinsi() {
        return networkService.getProvinsi(Constant.CONTENT_TYPE, Constant.CAKAP_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseKota> getKota(String id) {
        return networkService.getKota(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseUpdateProfile> postUpdateProfile(
            String noKtp, String alamat, String kodePos, String npwp, String statusPernikahan, String suami,
            String religion, String anak, String pekerjaan, String hubungan, String ahliWaris, String city, String email,
            String pob, String gender, String date, String hp, String telp, String fax, String kotaId, String province, String bankAcc,
            String bankId, String norek, String cabang, String area, String username, String nama, String pin) {
        return networkService.postUpdateProfile(
                Constant.CONTENT_TYPE, Constant.CAKAP_KEY, noKtp, alamat, kodePos, npwp, statusPernikahan, suami, religion,
                anak, pekerjaan, hubungan, ahliWaris, city, email, pob, gender, date, hp, telp, fax, kotaId, province, bankAcc,
                bankId, norek, cabang, area, username, nama, pin)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActivityCashbill> postActivityCashbill(String userId, String tahun, String bulan, String groupId) {
        return networkService.postActivityCashbill(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan, groupId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseDetailTransaction> postDetailTransaction(String url, String id) {
        return networkService.postDetailTransaction(url, Constant.CONTENT_TYPE, Constant.CAKAP_KEY, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseBonusStatementData> postActivityBonusStatement(String userId, String tahun, String bulan) {
        return networkService.postActivityBonusStatement(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseNetworkGeneology> postNetworkGenealogy(String userId, String memberId) {
        return networkService.postNetworkGenealogy(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, memberId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseLevel> postLevel(String userId) {
        return networkService.postLevel(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseDownlineListing> postDownlineListing(String userId, String level) {
        return networkService.postDownlineListing(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, level)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseMonthlyPointReport> postMonthlyPointReport(String userId, String tahun, String bulan) {
        return networkService.postMonthlyPointReport(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseFeeBcmb> postFeeBcmb(String userId, String tahun, String bulan) {
        return networkService.postFeeBcmb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseStockReportUpdate> postStockReportUpdate(String userId) {
        return networkService.postStockReportUpdate(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, "su")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseStockReportCard> postStockReportCard(String userId, String tahun, String bulan, String itemId, String price) {
        return networkService.postStockReportCard(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, "sc", tahun, bulan, itemId, price)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseStockReportCardItem> postStockReportCardItem(String userId) {
        return networkService.postStockReportCardItem(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseOmset> postOmset(String userId, String tahun, String bulan) {
        return networkService.postOmset(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSearchMember> postSearchMember(String userId, String keyword) {
        return networkService.postSearchMember(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, keyword)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSearchMemberCashbill> postSearchMemberCashbill(String memberId) {
        return networkService.postSearchMemberCashbill(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, memberId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseItemCashbill> postItemCashbill(String userId) {
        return networkService.postItemCashbill(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSubmitCashbill> postSubmitCashbill(Map<String, Object> param) {
        return networkService.postSubmitCashbill(Constant.CONTENT_TYPE_JSON, Constant.CAKAP_KEY, param)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSubmitInvoiceToMb> postSubmitInvoiceToMb(Map<String, Object> param) {
        return networkService.postSubmitInvoiceToMb(Constant.CONTENT_TYPE_JSON, Constant.CAKAP_KEY, param)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSubmitInvoiceToBc> postSubmitInvoiceToBc(Map<String, Object> param) {
        return networkService.postSubmitInvoiceToBc(Constant.CONTENT_TYPE_JSON, Constant.CAKAP_KEY, param)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSubmitInvoiceToCompany> postSubmitInvoiceToCompany(Map<String, Object> param) {
        return networkService.postSubmitInvoiceToCompany(Constant.CONTENT_TYPE_JSON, Constant.CAKAP_KEY, param)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSearchMbInvoice> postSearchMbInvoice(String userId, String noStc) {
        return networkService.postSearchMbInvoice(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, noStc)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseItemInvoiceToMb> postItemInvoiceToMb(String userId, String memberId) {
        return networkService.postItemInvoiceToMb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, memberId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseRekapBonusBcmb> postRekapBonusBcmb(String userId, String tahun, String bulan) {
        return networkService.postRekapBonusBcmb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseInvoiceToMb> postInvoiceToMb(String userId, String tahun, String bulan, String groupId) {
        return networkService.postInvoiceToMb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan, groupId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseCancelItemCashbill> postCancelItemCashbill(String userId, String username, String pin, String soId) {
        return networkService.postCancelItemCashbill(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, username, pin, soId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseCancelItemInvoiceToMb> postCancelItemInvoiceToMb(String userId, String username, String pin, String soId) {
        return networkService.postCancelItemInvoiceToMb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, username, pin, soId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActivityReqInvoiceMb> postListReqInvMb(String userId, String groupId) {
        return networkService.postListReqInvMb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, groupId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActionReqInvMb> postActionReqInvMb(String userId, String username, String pin, String id, String tipe) {
        return networkService.postActionReqInvMb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, username, pin, id, tipe)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActionRekapBnsBcmb> postActionRekapBnsBcmb(String username, String pin, String id) {
        return networkService.postActionRekapBnsBcmb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, username, pin, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseRestockInvoice> postRestockInvoice(String userId, String tahun, String bulan) {
        return networkService.postRestockInvoice(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseRestockReqInvoice> postRestockReqInvoice(String userId) {
        return networkService.postRestockReqInvoice(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseRestockReceiveStock> postRestockReceiveStock(String userId) {
        return networkService.postRestockReceiveStock(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActionReceiveStock> postActionReceiveStock(String userId, String username, String pin, String id) {
        return networkService.postActionReceiveStock(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, username, pin, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseRegistrationList> postRegistrationList(String userId) {
        return networkService.postRegistrationList(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseItemSearchRegistration> postItemSearchRegistration(String id) {
        return networkService.postItemSearchRegistration(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseSubmitRegistration> postRegistrationData(
            String userId, String userName, String pin, String recId, String sponsorId, String memberId,
            String name, String ktp, String gender, String pob, String dob, String religion, String email,
            String noTelp, String noHp, String alamat, String kotaId, String kodePos, String activation,
            String pewaris, String hubungan, String bankId, String cabang, String namaNasabah, String norek
    ) {
        return networkService.postRegistrationData(
                Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, userName, pin,
                recId, sponsorId, memberId, name, ktp, gender, pob, dob, religion, email, noTelp, noHp, alamat,
                kotaId, kodePos, activation, pewaris, hubungan, bankId, cabang, namaNasabah, norek,
                "", "", "", "")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseListAddress> postListAlamat(String userId) {
        return networkService.postListAlamat(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseAddEditAddress> postSubmitAlamat(String userId, String alamat, String kotaId, String username) {
        return networkService.postSubmitAlamat(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, alamat, kotaId, username)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseAddEditAddress> postEditAlamat(String userId, String alamat, String kotaId, String username, String idAlamat) {
        return networkService.postEditAlamat(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, alamat, kotaId, username, idAlamat)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseItemInvoiceToCompany> postItemInvoiceToCompany(String timur) {
        return networkService.postItemInvoiceToCompany(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, timur)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActivationFormData> postItemActivationForm(String id, String userId) {
        return networkService.postItemActivationForm(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, id, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseActivationFormSubmitData> postSubmitActivationForm(String userId) {
        return networkService.postSubmitActivationForm(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
