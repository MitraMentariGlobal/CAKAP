package co.id.cakap.ui.dashboard.home;

import java.util.List;

import co.id.cakap.data.FirebaseTokenData;
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

public class HomePresenter implements HomeContract.UserActionListener {
    private HomeContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;
    private List<FirebaseTokenData> mFirebaseTokenDataList;
    private List<ResultDataLogin> mResultDataLoginList;

    public HomePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(HomeContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mView.showProgressBar();
        try {
            mFirebaseTokenDataList = mDataModel.getAllFirebaseTokenData();
            mResultDataLoginList = mDataModel.getAllResultDataLogin();

            checkSession(mFirebaseTokenDataList.get(0), mResultDataLoginList.get(0));
        } catch (Exception e) {
            Logger.e("can't get data from model: " + e.getMessage());
            getInternalData();
        }
    }

    private void checkSession(FirebaseTokenData firebaseTokenData, ResultDataLogin resultDataLogin) {
        mDataModel.deleteResultDataLogin();
        mMainRepository.postCheckLogin(firebaseTokenData.getFcmToken(), resultDataLogin.getSession_token())
                .subscribe(new ResourceSubscriber<ApiResponseLogin>() {
                    @Override
                    public void onNext(ApiResponseLogin apiResponseLogin) {
                        try {
                            saveData(apiResponseLogin);
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

                        getInternalData();
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

        mView.setAdapter(apiResponseLogin.getResult());
    }

    public void getInternalData() {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mView.setAdapter(mResultDataLogin);
    }
}
