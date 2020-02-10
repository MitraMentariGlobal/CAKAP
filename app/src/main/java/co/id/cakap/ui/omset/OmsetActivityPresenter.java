package co.id.cakap.ui.omset;

import java.util.ArrayList;

import co.id.cakap.data.OmsetData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseOmset;
import co.id.cakap.network.ApiResponseStockReportUpdate;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class OmsetActivityPresenter implements OmsetActivityContract.UserActionListener {
    private OmsetActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public OmsetActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(OmsetActivityContract.View view){
        mView = view;
    }

    @Override
    public void getData(String tahun, String bulan) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postOmset(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan))
                .subscribe(new ResourceSubscriber<ApiResponseOmset>() {
                    @Override
                    public void onNext(ApiResponseOmset apiResponseOmset) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseOmset.getMessages());
                        Logger.d("<<<<<=====");

                        mView.setAdapter(apiResponseOmset.getData());
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
