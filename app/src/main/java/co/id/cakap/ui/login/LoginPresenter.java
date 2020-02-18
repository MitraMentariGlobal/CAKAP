package co.id.cakap.ui.login;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LoginPresenter implements LoginContract.UserActionListener {
    private LoginContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private FirebaseTokenData mFirebaseTokenData;

    public LoginPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
        mFirebaseTokenData = new FirebaseTokenData();
    }

    @Override
    public void getNotificationToken(String userId, String password) {
        mView.showProgressBar();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Logger.w("getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = "Token : " + token;
                        Logger.d(msg);

                        mFirebaseTokenData.setFcmToken(token);
                        mDataModel.insertFirebaseTokenData(mFirebaseTokenData);
                        actionLogin(userId, password, mFirebaseTokenData.getFcmToken());
                    }
                });
    }

    private void actionLogin(String userId, String password, String fcmToken) {
        mDataModel.deleteResultDataLogin();
        mMainRepository.postLogin(userId, password, fcmToken)
                .subscribe(new ResourceSubscriber<ApiResponseLogin>() {
                    @Override
                    public void onNext(ApiResponseLogin apiResponseLogin) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseLogin.getMessages());
                        Logger.d("role : " + apiResponseLogin.getResult().getRole());
                        Logger.d("url : " + apiResponseLogin.getResult().getUrl());
                        Logger.d("session token : " + apiResponseLogin.getResult().getSession_token());
                        Logger.d("<<<<<=====");

                        try {
                            saveData(apiResponseLogin);
                            mView.hideProgressBar();
                            mView.setSuccessResponse(apiResponseLogin.getResult().getUrl());
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

    @Override
    public void saveData(ApiResponseLogin apiResponseLogin) {
        Constant.LOGIN_DATA = apiResponseLogin.getResult().getRole();
        mDataModel.insertResultDataLogin(apiResponseLogin.getResult());
    }
}
