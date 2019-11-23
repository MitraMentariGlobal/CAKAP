package co.id.cakap.ui.registration.registrationSuccess;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class RegistrationSuccessPresenter implements RegistrationSuccessContract.UserActionListener {
    private RegistrationSuccessContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RegistrationSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(RegistrationSuccessContract.View view){
        mView = view;
    }
}
