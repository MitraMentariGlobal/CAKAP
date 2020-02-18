package co.id.cakap.ui.networkGenealogy;

import co.id.cakap.data.NetworkGenealogyData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseBonusStatementData;
import co.id.cakap.network.ApiResponseNetworkGeneology;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.utils.DateHelper;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class NetworkGenealogyPresenter implements NetworkGenealogyContract.UserActionListener {
    private NetworkGenealogyContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public NetworkGenealogyPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(NetworkGenealogyContract.View view){
        mView = view;
    }


    @Override
    public void getData(String memberId) {
        mView.showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postNetworkGenealogy(mResultDataLogin.getMember_id(), memberId)
                .subscribe(new ResourceSubscriber<ApiResponseNetworkGeneology>() {
                    @Override
                    public void onNext(ApiResponseNetworkGeneology apiResponseNetworkGeneology) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseNetworkGeneology.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            mView.hideProgressBar();
                            mView.setData(apiResponseNetworkGeneology.getData());
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
}
