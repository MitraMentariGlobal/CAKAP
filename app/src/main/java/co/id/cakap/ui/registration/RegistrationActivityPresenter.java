package co.id.cakap.ui.registration;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class RegistrationActivityPresenter implements RegistrationActivityContract.UserActionListener {
    private RegistrationActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RegistrationActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(RegistrationActivityContract.View view){
        mView = view;
    }
}
