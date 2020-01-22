package co.id.cakap.ui.dashboard.activity.activityBonusStatement;

import android.content.Context;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseBonusStatementData;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ActivityBonusStatementPresenter implements ActivityBonusStatementContract.UserActionListener {
    private ActivityBonusStatementContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public ActivityBonusStatementPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ActivityBonusStatementContract.View view){
        mView = view;
    }

    @Override
    public void getData(String tahun, String bulan) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postActivityBonusStatement(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan))
                .subscribe(new ResourceSubscriber<ApiResponseBonusStatementData>() {
                    @Override
                    public void onNext(ApiResponseBonusStatementData apiResponseBonusStatementData) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseBonusStatementData.getMessages());
                        Logger.d("<<<<<=====");

                        mView.hideProgressBar();
                        mView.checkPermissionStorage(apiResponseBonusStatementData.getData());

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

                        mView.hideProgressBar();
                        mView.setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }
}
