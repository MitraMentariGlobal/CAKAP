package co.id.cakap.ui.dashboard.account;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
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

                        try {
                            deleteInternalData(firebaseTokenData.getFirebase_user());
                            mView.hideProgressBar();
                            mView.setSuccessResponse();
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

    private static void deleteFcmData() {
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                    Logger.d("Fcm token deleted.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // Call your Activity where you want to land after log out
            }
        }.execute();
    }

    private void deleteInternalData(String uid) {
        mDataModel.deleteFirebaseTokenData();
        mDataModel.deleteResultDataLogin();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Logger.d("User account deleted.");
                        }
                    }
                });

        deleteFcmData();
    }
}
