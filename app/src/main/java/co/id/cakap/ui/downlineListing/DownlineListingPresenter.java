package co.id.cakap.ui.downlineListing;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.LevelData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseDownlineListing;
import co.id.cakap.network.ApiResponseLevel;
import co.id.cakap.network.ApiResponseNetworkGeneology;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DownlineListingPresenter implements DownlineListingContract.UserActionListener {
    private static WeakReference<DownlineListingContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public DownlineListingPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public DownlineListingPresenter() {

    }

    @Override
    public void setView(DownlineListingContract.View view){
        mView = new WeakReference<>(view);
    }

    public DownlineListingContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(LevelData levelData, RecyclerView recyclerView, TextView txtTitle) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postDownlineListing(mResultDataLogin.getMember_id(), levelData.getId())
                .subscribe(new ResourceSubscriber<ApiResponseDownlineListing>() {
                    @Override
                    public void onNext(ApiResponseDownlineListing apiResponseDownlineListing) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseDownlineListing.getMessages());
                        Logger.d("<<<<<=====");

                        getView().setAdapter(levelData, apiResponseDownlineListing.getData(), recyclerView, txtTitle);
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
    public void getDataDropdown() {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postLevel(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseLevel>() {
                    @Override
                    public void onNext(ApiResponseLevel apiResponseLevel) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseLevel.getMessages());
                        Logger.d("<<<<<=====");

                        getView().setAdapterDropdown(apiResponseLevel.getData());
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
