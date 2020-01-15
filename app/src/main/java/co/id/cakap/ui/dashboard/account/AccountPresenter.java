package co.id.cakap.ui.dashboard.account;

import java.util.List;

import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseLogout;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class AccountPresenter implements AccountContract.UserActionListener {
    private AccountContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private List<FirebaseTokenData> mFirebaseTokenDataList;
    private List<ResultDataLogin> mResultDataLoginList;

    public AccountPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(AccountContract.View view){
        mView = view;
    }

    @Override
    public void getLoginData() {
        try {
            mResultDataLoginList = mDataModel.getAllResultDataLogin();
            mView.setLoginData(mResultDataLoginList.get(0));
        } catch (Exception e) {
            Logger.e("can't get data from model: " + e.getMessage());
            mView.setSuccessResponse();
        }
    }

    @Override
    public void getData() {
        try {
            mFirebaseTokenDataList = mDataModel.getAllFirebaseTokenData();
            mResultDataLoginList = mDataModel.getAllResultDataLogin();

            actionLogout(mFirebaseTokenDataList.get(0), mResultDataLoginList.get(0));
        } catch (Exception e) {
            Logger.e("can't get data from model: " + e.getMessage());
            mView.setSuccessResponse();
        }
    }

    private void actionLogout(FirebaseTokenData firebaseTokenData, ResultDataLogin resultDataLogin) {
        mMainRepository.postLogout(firebaseTokenData.getFcmToken(), resultDataLogin.getSession_token())
                .subscribe(new ResourceSubscriber<ApiResponseLogout>() {
                    @Override
                    public void onNext(ApiResponseLogout apiResponseLogout) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseLogout.getMessages());
                        Logger.d("<<<<<=====");
                        mView.hideProgressBar();
                        mView.setSuccessResponse();
                        deleteData();
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
                        mView.hideProgressBar();
                        Logger.d("onComplete");
                    }
                });
    }

    public void deleteData() {
        mDataModel.deleteFirebaseTokenData();
        mDataModel.deleteResultDataLogin();
    }
}
