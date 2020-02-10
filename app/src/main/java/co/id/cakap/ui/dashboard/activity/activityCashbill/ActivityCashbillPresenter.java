package co.id.cakap.ui.dashboard.activity.activityCashbill;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseChangePin;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountContract;
import co.id.cakap.ui.dashboard.home.HomeContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ActivityCashbillPresenter implements ActivityCashbillContract.UserActionListener {
    private static WeakReference<ActivityCashbillContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public ActivityCashbillPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityCashbillPresenter() {

    }

    public void setView(ActivityCashbillContract.View view) {
        mView = new WeakReference<>(view);
    }

    public ActivityCashbillContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(Context context, String tahun, String bulan) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postActivityCashbill(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan), Utils.getGroupId(context))
                .subscribe(new ResourceSubscriber<ApiResponseActivityCashbill>() {
                    @Override
                    public void onNext(ApiResponseActivityCashbill apiResponseActivityCashbill) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActivityCashbill.getMessages());
                        Logger.d("<<<<<=====");

                        if (apiResponseActivityCashbill.getData().isEmpty()) {
                            getView().hideProgressBar();
                            getView().setErrorResponse(apiResponseActivityCashbill.getMessages());
                        } else {
                            getView().setAdapter(apiResponseActivityCashbill.getData());
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
