package co.id.cakap.ui.downlineListing;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseDownlineListing;
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
    private static ArrayList<DownlineListingData> arrayList;
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
    public void getData(int level, RecyclerView recyclerView, TextView txtTitle) {
        getView().showProgressBar();

        arrayList = new ArrayList<>();
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
        getView().setAdapter(level, arrayList, recyclerView, txtTitle);

//        mView.showProgressBar();
//
//        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
//        mMainRepository.postDownlineListing(mResultDataLogin.getMember_id(), level)
//                .subscribe(new ResourceSubscriber<ApiResponseDownlineListing>() {
//                    @Override
//                    public void onNext(ApiResponseDownlineListing apiResponseDownlineListing) {
//                        Logger.d("=====>>>>>");
//                        Logger.d("message : " + apiResponseDownlineListing.getMessages());
//                        Logger.d("<<<<<=====");
//
//                        mView.hideProgressBar();
//                        mView.setAdapter(apiResponseDownlineListing.getData());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        String errorResponse = "";
//                        t.printStackTrace();
//                        if (t instanceof HttpException) {
//                            ResponseBody responseBody = ((HttpException)t).response().errorBody();
//                            errorResponse = Utils.getErrorMessage(responseBody);
//                            Logger.e("error HttpException: " + errorResponse);
//                        }
//
//                        mView.hideProgressBar();
//                        mView.setErrorResponse(errorResponse);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.d("onComplete");
//                    }
//                });
//        arrayList = new ArrayList<>();
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        arrayList.add(new DownlineListingData("0000012", "ALBERT PANGEMANAN", "081315405345", "2008-03-24", "0000011"));
//        mView.setAdapter(arrayList);

//        mView.showProgressBar();
//
//        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
//        mMainRepository.postDownlineListing(mResultDataLogin.getMember_id(), level)
//                .subscribe(new ResourceSubscriber<ApiResponseDownlineListing>() {
//                    @Override
//                    public void onNext(ApiResponseDownlineListing apiResponseDownlineListing) {
//                        Logger.d("=====>>>>>");
//                        Logger.d("message : " + apiResponseDownlineListing.getMessages());
//                        Logger.d("<<<<<=====");
//
//                        mView.hideProgressBar();
//                        mView.setAdapter(apiResponseDownlineListing.getData());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        String errorResponse = "";
//                        t.printStackTrace();
//                        if (t instanceof HttpException) {
//                            ResponseBody responseBody = ((HttpException)t).response().errorBody();
//                            errorResponse = Utils.getErrorMessage(responseBody);
//                            Logger.e("error HttpException: " + errorResponse);
//                        }
//
//                        mView.hideProgressBar();
//                        mView.setErrorResponse(errorResponse);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.d("onComplete");
//                    }
//                });
    }

    @Override
    public void getDataDropdown() {
        getView().showProgressBar();
        getView().setAdapterDropdown(24);
    }
}
