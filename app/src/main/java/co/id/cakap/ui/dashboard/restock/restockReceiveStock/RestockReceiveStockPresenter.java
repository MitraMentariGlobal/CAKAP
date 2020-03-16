package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RestockReceiveStockData;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActionReceiveStock;
import co.id.cakap.network.ApiResponseActionRekapBnsBcmb;
import co.id.cakap.network.ApiResponseRestockReceiveStock;
import co.id.cakap.network.ApiResponseRestockReqInvoice;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RestockReceiveStockPresenter implements RestockReceiveStockContract.UserActionListener {
    private static WeakReference<RestockReceiveStockContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public RestockReceiveStockPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RestockReceiveStockPresenter() {

    }

    public void setView(RestockReceiveStockContract.View view){
        mView = new WeakReference<>(view);
    }

    public RestockReceiveStockContract.View getView() throws NullPointerException {
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
        mMainRepository.postRestockReceiveStock(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseRestockReceiveStock>() {
                    @Override
                    public void onNext(ApiResponseRestockReceiveStock apiResponseRestockReceiveStock) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseRestockReceiveStock.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseRestockReceiveStock.getData());
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

    @Override
    public void actionTransaction(RestockReceiveStockData restockReceiveStockData, String pin) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postActionReceiveStock(mResultDataLogin.getMember_id(), mResultDataLogin.getUsername(), pin, restockReceiveStockData.getItem_id())
                .subscribe(new ResourceSubscriber<ApiResponseActionReceiveStock>() {
                    @Override
                    public void onNext(ApiResponseActionReceiveStock apiResponseActionReceiveStock) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActionReceiveStock.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getData();
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
