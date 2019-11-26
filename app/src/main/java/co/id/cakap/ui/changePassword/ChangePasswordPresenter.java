package co.id.cakap.ui.changePassword;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class ChangePasswordPresenter implements ChangePasswordContract.UserActionListener {
    private ChangePasswordContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ChangePasswordPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ChangePasswordContract.View view){
        mView = view;
    }

    @Override
    public void changeData(String oldPassword, String newPassword, String retypeNewPassword, String pin) {

    }
}
