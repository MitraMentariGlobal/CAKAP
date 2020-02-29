package co.id.cakap.ui.registration.registrationSuccess;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class RegistrationSuccessPresenter implements RegistrationSuccessContract.UserActionListener {
    private RegistrationSuccessContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ResultDataLogin mResultDataLogin;

    public RegistrationSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(RegistrationSuccessContract.View view){
        mView = view;
    }

    @Override
    public void getLoginData() {
        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mView.successInputData(mResultDataLogin.getMember_id(), mResultDataLogin.getNama());
    }
}
