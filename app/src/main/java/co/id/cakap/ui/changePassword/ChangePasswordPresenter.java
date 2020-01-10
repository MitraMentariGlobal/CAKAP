package co.id.cakap.ui.changePassword;

import android.view.View;

import co.id.cakap.R;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseChangePassword;
import co.id.cakap.network.ApiResponseLogin;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ChangePasswordPresenter implements ChangePasswordContract.UserActionListener {
    private ChangePasswordContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public ChangePasswordPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ChangePasswordContract.View view){
        mView = view;
    }

    @Override
    public void changeData(String oldPassword, String newPassword, String retypeNewPassword, String pin) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postChangePassword(oldPassword, newPassword, retypeNewPassword, pin, mResultDataLogin.getUsername(), mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseChangePassword>() {
                    @Override
                    public void onNext(ApiResponseChangePassword apiResponseChangePassword) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseChangePassword.getMessages());
                        Logger.d("<<<<<=====");

                        mView.hideProgressBar();
                        mView.setSuccessResponse();
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

    @Override
    public void checkData(String oldPassword, String newPassword, String retypeNewPassword) {
        if (oldPassword.length() == 0 && newPassword.length() == 0 && retypeNewPassword.length() == 0) {
            mView.setErrorOldPassword(true);
            mView.setErrorNewPassword(true, false);
            mView.setErrorRetypeNewPassword(true, false);
        } else {
            if (oldPassword.length() == 0) {
                mView.setErrorOldPassword(true);
            } else {
                mView.setErrorOldPassword(false);
            }

            if (newPassword.length() == 0 && retypeNewPassword.length() == 0) {
                mView.setErrorNewPassword(true, false);
                mView.setErrorRetypeNewPassword(true, false);
            } else {
                if (!newPassword.equals(retypeNewPassword)) {
                    mView.setErrorNewPassword(true, true);
                    mView.setErrorRetypeNewPassword(true, true);
                } else {
                    mView.setErrorNewPassword(false, true);
                    mView.setErrorRetypeNewPassword(false, true);

                    mView.openDialogPin();
                }
            }
        }
    }
}
