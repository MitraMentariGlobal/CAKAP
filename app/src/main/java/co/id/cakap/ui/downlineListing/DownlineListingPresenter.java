package co.id.cakap.ui.downlineListing;

import java.util.ArrayList;

import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseDownlineListing;
import co.id.cakap.network.ApiResponseNetworkGeneology;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DownlineListingPresenter implements DownlineListingContract.UserActionListener {
    private DownlineListingContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ArrayList<DownlineListingData> arrayList;
    private ResultDataLogin mResultDataLogin;

    public DownlineListingPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(DownlineListingContract.View view){
        mView = view;
    }

    @Override
    public void getData(String level) {
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
        mView.setAdapter(arrayList);

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
}
