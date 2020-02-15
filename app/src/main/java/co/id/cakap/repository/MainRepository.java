package co.id.cakap.repository;

import java.util.Map;

import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.data.ReligionData;
import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseBank;
import co.id.cakap.network.ApiResponseBonusStatementData;
import co.id.cakap.network.ApiResponseChangePassword;
import co.id.cakap.network.ApiResponseChangePin;
import co.id.cakap.network.ApiResponseDetailTransaction;
import co.id.cakap.network.ApiResponseDownlineListing;
import co.id.cakap.network.ApiResponseFeeBcmb;
import co.id.cakap.network.ApiResponseInvoiceToMb;
import co.id.cakap.network.ApiResponseItemCashbill;
import co.id.cakap.network.ApiResponseItemInvoiceToMb;
import co.id.cakap.network.ApiResponseJenisKelamin;
import co.id.cakap.network.ApiResponseLevel;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.ApiResponseLogout;
import co.id.cakap.network.ApiResponseMonthlyPointReport;
import co.id.cakap.network.ApiResponseNetworkGeneology;
import co.id.cakap.network.ApiResponseOmset;
import co.id.cakap.network.ApiResponseProfileData;
import co.id.cakap.network.ApiResponseRekapBonusBcmb;
import co.id.cakap.network.ApiResponseReligion;
import co.id.cakap.network.ApiResponseSearchMbInvoice;
import co.id.cakap.network.ApiResponseSearchMember;
import co.id.cakap.network.ApiResponseSearchMemberCashbill;
import co.id.cakap.network.ApiResponseStockReportCard;
import co.id.cakap.network.ApiResponseStockReportCardItem;
import co.id.cakap.network.ApiResponseStockReportUpdate;
import co.id.cakap.network.ApiResponseSubmitCashbill;
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
        return networkService.postSubmitCashbill(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, param)
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

    public Flowable<ApiResponseInvoiceToMb> postRekapInvoiceToMb(String userId, String tahun, String bulan, String groupId) {
        return networkService.postRekapInvoiceToMb(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, userId, tahun, bulan, groupId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
