package co.id.cakap.ui.dashboard.restock.restockInvoice;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockInvoiceData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseRekapBonusBcmb;
import co.id.cakap.network.ApiResponseRestockInvoice;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RestockInvoicePresenter implements RestockInvoiceContract.UserActionListener {
    private static WeakReference<RestockInvoiceContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<RestockInvoiceData> arrayList;

    public RestockInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RestockInvoicePresenter() {

    }

    public void setView(RestockInvoiceContract.View view){
        mView = new WeakReference<>(view);
    }

    public RestockInvoiceContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String tahun, String bulan) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postRestockInvoice(mResultDataLogin.getMember_id(), tahun, DateHelper.getMonthNumber(bulan))
                .subscribe(new ResourceSubscriber<ApiResponseRestockInvoice>() {
                    @Override
                    public void onNext(ApiResponseRestockInvoice apiResponseRestockInvoice) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseRestockInvoice.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            if (apiResponseRestockInvoice.getData().isEmpty()) {
                                getView().hideProgressBar();
                                getView().setErrorResponse(apiResponseRestockInvoice.getMessages());
                            } else {
                                getView().setAdapter(apiResponseRestockInvoice.getData());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
