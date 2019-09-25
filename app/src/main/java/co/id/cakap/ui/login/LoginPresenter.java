package co.id.cakap.ui.login;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class LoginPresenter implements LoginContract.UserActionListener {
    private LoginContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public LoginPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }
}
