package co.id.cakap.ui.feeBCMB;

import co.id.cakap.data.FeeBCMBData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseFeeBcmb;
import co.id.cakap.network.ApiResponseMonthlyPointReport;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class FeeBcmbActivityPresenter implements FeeBcmbActivityContract.UserActionListener {
    private FeeBcmbActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;
    private FeeBCMBData mFeeBCMBData;

    public FeeBcmbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(FeeBcmbActivityContract.View view){
        mView = view;
    }

    @Override
    public void getData(String tahun, String bulan) {
//        mFeeBCMBData = new FeeBCMBData(
//                "80,399,091",
//                "66,717,273",
//                "17 (paket)",
//                "9 (paket)",
//                "3,335,864",
//                "0",
//                "127,500",
//                "450,000",
//                "14,792,817",
//                "0",
//                "18,706,181"
//        );
//
//        mView.setData(mFeeBCMBData);
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postFeeBcmb(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan))
                .subscribe(new ResourceSubscriber<ApiResponseFeeBcmb>() {
                    @Override
                    public void onNext(ApiResponseFeeBcmb apiResponseFeeBcmb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseFeeBcmb.getMessages());
                        Logger.d("<<<<<=====");

                        mView.setData(apiResponseFeeBcmb.getData());
//                        mView.setAdapter(apiResponseMonthlyPointReport.getData());
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
