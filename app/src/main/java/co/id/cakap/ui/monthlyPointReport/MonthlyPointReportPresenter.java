package co.id.cakap.ui.monthlyPointReport;

import java.util.ArrayList;

import co.id.cakap.data.MonthlyPointReportData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseDownlineListing;
import co.id.cakap.network.ApiResponseMonthlyPointReport;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MonthlyPointReportPresenter implements MonthlyPointReportContract.UserActionListener {
    private MonthlyPointReportContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public MonthlyPointReportPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MonthlyPointReportContract.View view){
        mView = view;
    }

    @Override
    public void getData(String tahun, String bulan) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postMonthlyPointReport(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan))
                .subscribe(new ResourceSubscriber<ApiResponseMonthlyPointReport>() {
                    @Override
                    public void onNext(ApiResponseMonthlyPointReport apiResponseMonthlyPointReport) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseMonthlyPointReport.getMessages());
                        Logger.d("<<<<<=====");

                        mView.setAdapter(apiResponseMonthlyPointReport.getData());
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
