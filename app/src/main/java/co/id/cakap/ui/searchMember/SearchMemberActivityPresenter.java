package co.id.cakap.ui.searchMember;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.data.SearchMemberData;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseOmset;
import co.id.cakap.network.ApiResponseSearchMember;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class SearchMemberActivityPresenter implements SearchMemberActivityContract.UserActionListener {
    private static WeakReference<SearchMemberActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    public SearchMemberActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public SearchMemberActivityPresenter() {

    }

    @Override
    public void setView(SearchMemberActivityContract.View view) {
        mView = new WeakReference<>(view);
    }

    public SearchMemberActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getDataMember(String param) {
        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postSearchMember(mResultDataLogin.getMember_id(), param)
                .subscribe(new ResourceSubscriber<ApiResponseSearchMember>() {
                    @Override
                    public void onNext(ApiResponseSearchMember apiResponseSearchMember) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseSearchMember.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            getView().setAdapter(apiResponseSearchMember.getData());
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
