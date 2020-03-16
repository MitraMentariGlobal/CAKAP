package co.id.cakap.ui.registration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.RegistrationData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.ApiResponseRegistrationList;
import co.id.cakap.network.ApiResponseRestockReceiveStock;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.Utils;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RegistrationActivityPresenter implements RegistrationActivityContract.UserActionListener {
    private static WeakReference<RegistrationActivityContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static ResultDataLogin mResultDataLogin;

    private ArrayList<RegistrationData> arrayList;

    public RegistrationActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public RegistrationActivityPresenter() {

    }

    @Override
    public void setView(RegistrationActivityContract.View view){
        mView = new WeakReference<>(view);
    }

    public RegistrationActivityContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
//        arrayList = new ArrayList<>();
//        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Lengkap", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Lengkap", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Khusus V-Bless", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Khusus V-Bless", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Khusus V-Bless", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        arrayList.add(new RegistrationData("Starter Kit Basic", "0099488", "0f4abe9495d1", "28 Jan 2020", "not used"));
//        getView().setAdapter(arrayList);

        getView().showProgressBar();

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMainRepository.postRegistrationList(mResultDataLogin.getMember_id())
                .subscribe(new ResourceSubscriber<ApiResponseRegistrationList>() {
                    @Override
                    public void onNext(ApiResponseRegistrationList apiResponseRegistrationList) {
                        Logger.d("=====>>>>>");
                        Logger.d("message : " + apiResponseRegistrationList.getMessages());
                        Logger.d("<<<<<=====");

                        try {
                            if (apiResponseRegistrationList.getData().isEmpty()) {
                                getView().hideProgressBar();
                                getView().setErrorResponse(apiResponseRegistrationList.getMessages());
                            } else {
                                getView().setAdapter(apiResponseRegistrationList.getData());
                            }
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
