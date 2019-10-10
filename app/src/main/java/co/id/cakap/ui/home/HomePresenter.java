package co.id.cakap.ui.home;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.List;

import co.id.cakap.data.FirebaseTokenData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.network.ApiResponseLogout;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class HomePresenter implements HomeContract.UserActionListener {
    private HomeContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private FirebaseTokenData mFirebaseTokenData;
    private List<FirebaseTokenData> mFirebaseTokenDataList;
    private List<ResultDataLogin> mResultDataLoginList;

    public HomePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
        mFirebaseTokenData = new FirebaseTokenData();
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
