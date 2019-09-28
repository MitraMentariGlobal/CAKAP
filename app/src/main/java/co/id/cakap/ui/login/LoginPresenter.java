package co.id.cakap.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponse;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import io.reactivex.subscribers.ResourceSubscriber;

public class LoginPresenter implements LoginContract.UserActionListener {
    private LoginContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public LoginPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void getNotificationToken(String userId, String password) {
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
                        actionLogin(userId, password, token);
                    }
                });
    }

    public void actionLogin(String userId, String password, String fcm_token) {
        Map<String, Object> param = new HashMap<>();
        param.put(Constant.BODY_USER_ID, userId);
        param.put(Constant.BODY_PASSWORD, password);
        param.put(Constant.BODY_FCM_TOKEN, fcm_token);

        mDataModel.deleteResultDataLogin();
        mView.showProgressBar();
        mMainRepository.postLogin(param)
                .subscribe(new ResourceSubscriber<ApiResponseLogin>() {
                    @Override
                    public void onNext(ApiResponseLogin apiResponseLogin) {
                        Logger.d("=====>>>>>");
                        Logger.d("param : " + param.toString());
                        Logger.d("message : " + apiResponseLogin.getMessages());
                        Logger.d("url : " + apiResponseLogin.getResult().getUrl());
                        Logger.d("session token : " + apiResponseLogin.getResult().getSession_token());
                        Logger.d("<<<<<=====");
                        mView.hideProgressBar();
                        saveData(apiResponseLogin);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        Logger.e("error : " + t.getMessage());
                        mView.hideProgressBar();
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void saveData(ApiResponseLogin apiResponseLogin) {
        mDataModel.insertResultDataLogin(apiResponseLogin.getResult());
    }
}
