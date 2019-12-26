package co.id.cakap.repository;

import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.ApiResponseLogout;
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
}
