package co.id.cakap.repository;

import java.util.Map;

import co.id.cakap.helper.Constant;
import co.id.cakap.network.ApiResponse;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.NetworkService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainRepository extends BaseRepository {
    public MainRepository(NetworkService networkService) {
        super(networkService);
    }

    /**
     * Get Data
     * @Param Sorting Type
     * */
    public Flowable<ApiResponse> getData() {
        return networkService.getData(Constant.API_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseLogin> postCheckLogin(Map<String, Object> param) {
        return networkService.postCheckLogin(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, param)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<ApiResponseLogin> postLogin(Map<String, Object> param) {
        return networkService.postLogin(Constant.CONTENT_TYPE, Constant.CAKAP_KEY, param)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
