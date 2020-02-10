package co.id.cakap.repository;

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
import co.id.cakap.network.ApiResponseJenisKelamin;
import co.id.cakap.network.ApiResponseLevel;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.ApiResponseLogout;
import co.id.cakap.network.ApiResponseMonthlyPointReport;
import co.id.cakap.network.ApiResponseNetworkGeneology;
import co.id.cakap.network.ApiResponseProfileData;
import co.id.cakap.network.ApiResponseReligion;
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
}
