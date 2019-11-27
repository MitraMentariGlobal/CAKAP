package co.id.cakap.ui.changePin;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class ChangePinPresenter implements ChangePinContract.UserActionListener {
    private ChangePinContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ChangePinPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ChangePinContract.View view){
        mView = view;
    }

    @Override
    public void changeData(String oldPin, String newPin, String retypeNewPin) {
        mView.setSuccessResponse();
    }
}
