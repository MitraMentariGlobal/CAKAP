package co.id.cakap.ui.myProfile;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class MyProfileActivityPresenter implements MyProfileActivityContract.UserActionListener {
    private MyProfileActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public MyProfileActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MyProfileActivityContract.View view){
        mView = view;
    }
}
