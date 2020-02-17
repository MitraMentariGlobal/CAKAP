package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseActionReqInvMb;
import co.id.cakap.network.ApiResponseActivityCashbill;
import co.id.cakap.network.ApiResponseActivityReqInvoiceMb;
import co.id.cakap.network.ApiResponseCancelItemCashbill;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ActivityReqInvMbPresenter implements ActivityReqInvMbContract.UserActionListener {
    private static WeakReference<ActivityReqInvMbContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<ActivityReqInvMbData> arrayList;

    public ActivityReqInvMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityReqInvMbPresenter() {

    }

    public void setView(ActivityReqInvMbContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityReqInvMbContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(Context context) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postListReqInvMb(mResultDataLogin.getMember_id(), Utils.getGroupId(context))
//        mMainRepository.postListReqInvMb("0000160", Utils.getGroupId(context))
                .subscribe(new ResourceSubscriber<ApiResponseActivityReqInvoiceMb>() {
                    @Override
                    public void onNext(ApiResponseActivityReqInvoiceMb apiResponseActivityReqInvoiceMb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActivityReqInvoiceMb.getMessages());
                        Logger.d("<<<<<=====");

                        if (apiResponseActivityReqInvoiceMb.getData().isEmpty()) {
                            getView().hideProgressBar();
                            getView().setErrorResponse(apiResponseActivityReqInvoiceMb.getMessages());
                        } else {
                            getView().setAdapter(apiResponseActivityReqInvoiceMb.getData());
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
    public void actionTransaction(ActivityReqInvMbData activityReqInvMbData, String pin, Context context, String action) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postActionReqInvMb(mResultDataLogin.getMember_id(), mResultDataLogin.getUsername(), pin, activityReqInvMbData.getItem_id(), action)
//        mMainRepository.postActionReqInvMb("0000160", "hanna", pin, activityReqInvMbData.getItem_id(), action)
                .subscribe(new ResourceSubscriber<ApiResponseActionReqInvMb>() {
                    @Override
                    public void onNext(ApiResponseActionReqInvMb apiResponseActionReqInvMb) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseActionReqInvMb.getMessages());
                        Logger.d("<<<<<=====");

                        getData(context);
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
