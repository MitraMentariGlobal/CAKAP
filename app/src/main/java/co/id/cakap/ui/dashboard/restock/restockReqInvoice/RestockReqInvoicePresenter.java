package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseRestockInvoice;
import co.id.cakap.network.ApiResponseRestockReqInvoice;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RestockReqInvoicePresenter implements RestockReqInvoiceContract.UserActionListener {
    private static WeakReference<RestockReqInvoiceContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public RestockReqInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RestockReqInvoicePresenter() {

    }

    public void setView(RestockReqInvoiceContract.View view){
        mView = new WeakReference<>(view);
    }

    public RestockReqInvoiceContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postRestockReqInvoice(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseRestockReqInvoice>() {
                    @Override
                    public void onNext(ApiResponseRestockReqInvoice apiResponseRestockReqInvoice) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseRestockReqInvoice.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseRestockReqInvoice.getData());
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
