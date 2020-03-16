package co.id.cakap.ui.createActivationForm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.id.cakap.data.ActivationSubmitItemFormData;
import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.RegistrationData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActivationFormData;
import co.id.cakap.network.ApiResponseActivationFormSubmitData;
import co.id.cakap.network.ApiResponseListAddress;
import co.id.cakap.network.ApiResponseRegistrationList;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.registration.RegistrationActivityContract;
import co.id.cakap.utils.DateHelper;
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
    private static String mKodeId = "";

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
    public void getData(String id) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postItemActivationForm(id, mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseActivationFormData>() {
                    @Override
                    public void onNext(ApiResponseActivationFormData apiResponseActivationFormData) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActivationFormData.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            mKodeId = apiResponseActivationFormData.getKode_id();
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
    public void submitData(String pin, ActivityInvToMbData activityInvToMbData, List<ActivationSubmitItemFormData> submitItemFormDataList) {
        getView().showProgressBar();

        mMainRepository.postSubmitActivationForm(getParam(pin, activityInvToMbData, submitItemFormDataList))
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

    private Map<String, Object> getParam(String pin, ActivityInvToMbData activityInvToMbData, List<ActivationSubmitItemFormData> submitItemFormDataList) {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);

        List<Object> dataList = new ArrayList<>();
        Map<String, Object> mParam = new HashMap<>();
        for (ActivationSubmitItemFormData data : submitItemFormDataList) {
            Map<String, Object> mDetail = new HashMap<>();

            mDetail.put(Constant.BODY_ITEM_ID, data.getItemId());
            mDetail.put(Constant.BODY_MEMBER_ID2, data.getMemberId());
            mDetail.put(Constant.BODY_QTY, data.getQty());

            dataList.add(mDetail);
        }

        mParam.put(Constant.BODY_PIN, pin);
        mParam.put(Constant.BODY_USER_ID, mResultDataLogin.getMember_id());
        mParam.put(Constant.BODY_USER_NAME, mResultDataLogin.getUsername());
        mParam.put(Constant.BODY_ID_RO, activityInvToMbData.getItem_id());
        mParam.put(Constant.BODY_KODE_ID, mKodeId);
        mParam.put(Constant.BODY_DETAIL, dataList);

        return mParam;
    }
}
