package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseRekapBonusBcmb;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ActivityRekapBnsBcmbPresenter implements ActivityRekapBnsBcmbContract.UserActionListener {
    private static WeakReference<ActivityRekapBnsBcmbContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<ActivityRekapBnsBcmbData> arrayList;

    public ActivityRekapBnsBcmbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityRekapBnsBcmbPresenter() {

    }

    public void setView(ActivityRekapBnsBcmbContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityRekapBnsBcmbContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String tahun, String bulan) {
//        arrayList = new ArrayList<>();
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
//        getView().setAdapter(arrayList);

        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postRekapBonusBcmb(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan))
                .subscribe(new ResourceSubscriber<ApiResponseRekapBonusBcmb>() {
                    @Override
                    public void onNext(ApiResponseRekapBonusBcmb apiResponseRekapBonusBcmb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseRekapBonusBcmb.getMessages());
                        Logger.d("<<<<<=====");

                        if (apiResponseRekapBonusBcmb.getData().isEmpty()) {
                            getView().hideProgressBar();
                            getView().setErrorResponse(apiResponseRekapBonusBcmb.getMessages());
                        } else {
                            getView().setAdapter(apiResponseRekapBonusBcmb.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        String errorResponse = "";
                        t.printStackTrace();
                        if (t instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException)t).response().errorBody();
                            errorResponse = Utils.getErrorMessage(responseBody);
                            Logger.e("error HttpException: " + errorResponse);
                        }

                        getView().hideProgressBar();
                        getView().setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }
}
