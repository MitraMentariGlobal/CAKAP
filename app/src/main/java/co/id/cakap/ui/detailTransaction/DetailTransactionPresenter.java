package co.id.cakap.ui.detailTransaction;

import java.util.ArrayList;

import co.id.cakap.data.DetailTransactionData;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseDetailTransaction;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DetailTransactionPresenter implements DetailTransactionContract.UserActionListener {
    private static DetailTransactionContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<DetailTransactionData> arrayList;

    public DetailTransactionPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(DetailTransactionContract.View view){
        mView = view;
    }

    @Override
    public void getData(String endpoint, String itemId) {
        mMainRepository.postDetailTransaction(endpoint, itemId)
                .subscribe(new ResourceSubscriber<ApiResponseDetailTransaction>() {
                    @Override
                    public void onNext(ApiResponseDetailTransaction apiResponseDetailTransaction) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseDetailTransaction.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            mView.setAdapter(apiResponseDetailTransaction);
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
