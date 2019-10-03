package co.id.cakap.ui.splash_screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.ApiResponseSession;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class SplashScreenPresenter implements SplashScreenContract.UserActionListener{
    private SplashScreenContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private List<FirebaseTokenData> mFirebaseTokenDataList;
    private List<ResultDataLogin> mResultDataLoginList;

    public SplashScreenPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(SplashScreenContract.View view) {
        mView = view;
    }

    @Override
    public void getData() {
        try {
            mFirebaseTokenDataList = mDataModel.getAllFirebaseTokenData();
            mResultDataLoginList = mDataModel.getAllResultDataLogin();

            checkSession(mFirebaseTokenDataList.get(0), mResultDataLoginList.get(0));
        } catch (Exception e) {
            Logger.e("can't get data from model: " + e.getMessage());
            mView.goToLogin();
        }
    }

    private void checkSession(FirebaseTokenData firebaseTokenData, ResultDataLogin resultDataLogin) {
        mMainRepository.postCheckLogin(firebaseTokenData.getFcmToken(), resultDataLogin.getSession_token())
                .subscribe(new ResourceSubscriber<ApiResponseSession>() {
                    @Override
                    public void onNext(ApiResponseSession apiResponseSession) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSession.getMessages());
                        Logger.d("url : " + apiResponseSession.getResult().getUrl());
                        Logger.d("<<<<<=====");
                        mView.goToHome(apiResponseSession.getResult().getUrl());
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

                        mDataModel.deleteFirebaseTokenData();
                        mView.setErrorResponse(errorResponse);
                        mView.goToLogin();
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }
}
