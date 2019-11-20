package co.id.cakap.ui.detailRegistration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class DetailRegistrationPresenter implements DetailRegistrationContract.UserActionListener {
    private static WeakReference<DetailRegistrationContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    public DetailRegistrationPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public DetailRegistrationPresenter() {

    }

    @Override
    public void setView(DetailRegistrationContract.View view) {
        mView = new WeakReference<>(view);
    }

    public DetailRegistrationContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String memberId) {

    }
}
