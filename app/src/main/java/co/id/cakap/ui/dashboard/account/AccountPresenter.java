package co.id.cakap.ui.dashboard.account;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class AccountPresenter implements AccountContract.UserActionListener {
    private AccountContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public AccountPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(AccountContract.View view){
        mView = view;
    }
}
