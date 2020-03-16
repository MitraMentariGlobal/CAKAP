package co.id.cakap.ui.stockReport.stockUpdate;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.data.StockCardData;
import co.id.cakap.data.StockUpdateData;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseFeeBcmb;
import co.id.cakap.network.ApiResponseStockReportUpdate;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.stockReport.stockCard.StockCardContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class StockUpdatePresenter implements StockUpdateContract.UserActionListener {
    private static WeakReference<StockUpdateContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public StockUpdatePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public StockUpdatePresenter() {

    }

    public void setView(StockUpdateContract.View view){
        mView = new WeakReference<>(view);
    }

    public StockUpdateContract.View getView() throws NullPointerException {
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
        mMainRepository.postStockReportUpdate(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseStockReportUpdate>() {
                    @Override
                    public void onNext(ApiResponseStockReportUpdate apiResponseStockReportUpdate) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseStockReportUpdate.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseStockReportUpdate.getData());
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
