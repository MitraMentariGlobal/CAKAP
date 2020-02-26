package co.id.cakap.ui.createActivationForm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RegistrationData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivationFormData;
import co.id.cakap.network.ApiResponseActivationFormSubmitData;
import co.id.cakap.network.ApiResponseListAddress;
import co.id.cakap.network.ApiResponseRegistrationList;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.registration.RegistrationActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class CreateActivationFormPresenter implements CreateActivationFormContract.UserActionListener {
    private static WeakReference<CreateActivationFormContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public CreateActivationFormPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public CreateActivationFormPresenter() {

    }

    @Override
    public void setView(CreateActivationFormContract.View view){
        mView = new WeakReference<>(view);
    }

    public CreateActivationFormContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postItemActivationForm(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseActivationFormData>() {
                    @Override
                    public void onNext(ApiResponseActivationFormData apiResponseActivationFormData) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActivationFormData.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseActivationFormData.getData());
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

                        getView().hideProgressBar();
                        getView().setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }

    @Override
    public void submitData() {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postSubmitActivationForm(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseActivationFormSubmitData>() {
                    @Override
                    public void onNext(ApiResponseActivationFormSubmitData apiResponseActivationFormSubmitData) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActivationFormSubmitData.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setSuccessResponse();
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

                        getView().hideProgressBar();
                        getView().setErrorResponse(errorResponse);
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }
}
