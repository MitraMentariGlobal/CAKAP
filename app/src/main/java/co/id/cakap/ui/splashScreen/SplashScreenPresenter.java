package co.id.cakap.ui.splashScreen;

import android.content.SharedPreferences;

import java.util.List;

import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.NotificationData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseLogin;
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
        mDataModel.deleteResultDataLogin();
        mMainRepository.postCheckLogin(firebaseTokenData.getFcmToken(), resultDataLogin.getSession_token())
                .subscribe(new ResourceSubscriber<ApiResponseLogin>() {
                    @Override
                    public void onNext(ApiResponseLogin apiResponseLogin) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseLogin.getMessages());
                        Logger.d("url : " + apiResponseLogin.getResult().getUrl());
                        Logger.d("role : " + apiResponseLogin.getResult().getRole());
                        Logger.d("session token : " + apiResponseLogin.getResult().getSession_token());
                        Logger.d("username : " + apiResponseLogin.getResult().getUsername());
                        Logger.d("member id : " + apiResponseLogin.getResult().getMember_id());
                        Logger.d("<<<<<=====");

                        saveData(apiResponseLogin);
                        mView.goToHome(apiResponseLogin.getResult().getUrl());
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

    @Override
    public void saveData(ApiResponseLogin apiResponseLogin) {
        Constant.LOGIN_DATA = apiResponseLogin.getResult().getRole();
        mDataModel.insertResultDataLogin(apiResponseLogin.getResult());
    }
}
