package co.id.cakap.ui.changePin;

import android.content.Context;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseChangePassword;
import co.id.cakap.network.ApiResponseChangePin;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ChangePinPresenter implements ChangePinContract.UserActionListener {
    private ChangePinContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;
    private Context mContext;

    public ChangePinPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ChangePinContract.View view, Context context){
        mView = view;
        mContext = context;
    }

    @Override
    public void changeData(String oldPin, String newPin, String retypeNewPin) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postChangePin(oldPin, newPin, retypeNewPin, mResultDataLogin.getUsername(), mResultDataLogin.getMember_id(), Utils.getGroupId(mContext))
                .subscribe(new ResourceSubscriber<ApiResponseChangePin>() {
                    @Override
                    public void onNext(ApiResponseChangePin apiResponseChangePin) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseChangePin.getMessages());
                        Logger.d("<<<<<=====");

                        try {
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

    @Override
    public void checkData(String oldPin, String newPin, String retypeNewPin) {
        if (oldPin.length() == 0 && newPin.length() == 0 && retypeNewPin.length() == 0) {
            mView.setErrorOldPin(true);
            mView.setErrorNewPin(true, false);
            mView.setErrorRetypeNewPin(true, false);
        } else {
            if (oldPin.length() == 0) {
                mView.setErrorOldPin(true);
            } else {
                mView.setErrorOldPin(false);
            }

            if (newPin.length() == 0 && retypeNewPin.length() == 0) {
                mView.setErrorNewPin(true, false);
                mView.setErrorRetypeNewPin(true, false);
            } else {
                if (!newPin.equals(retypeNewPin)) {
                    mView.setErrorNewPin(true, true);
                    mView.setErrorRetypeNewPin(true, true);
                } else {
                    mView.setErrorNewPin(false, true);
                    mView.setErrorRetypeNewPin(false, true);

                    changeData(oldPin, newPin, retypeNewPin);
                }
            }
        }
    }
}
