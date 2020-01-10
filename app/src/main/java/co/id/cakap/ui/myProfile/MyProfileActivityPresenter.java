package co.id.cakap.ui.myProfile;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseChangePin;
import co.id.cakap.network.ApiResponseProfileData;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MyProfileActivityPresenter implements MyProfileActivityContract.UserActionListener {
    private MyProfileActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public MyProfileActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MyProfileActivityContract.View view){
        mView = view;
    }

    @Override
    public void getJenisKelamiin() {

    }

    @Override
    public void getReligion() {

    }

    @Override
    public void getProfileData() {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.getProfileData(mResultDataLogin.getMember_id(), Constant.GET_GROUP_ID_MEMBER)
                .subscribe(new ResourceSubscriber<ApiResponseProfileData>() {
                    @Override
                    public void onNext(ApiResponseProfileData apiResponseProfileData) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseProfileData.getMessages());
                        Logger.d("<<<<<=====");

                        mView.hideProgressBar();
                        mView.setData(apiResponseProfileData.getData());
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
}
