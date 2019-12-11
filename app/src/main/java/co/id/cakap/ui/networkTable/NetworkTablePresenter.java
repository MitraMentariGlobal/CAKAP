package co.id.cakap.ui.networkTable;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class NetworkTablePresenter implements NetworkTableContract.UserActionListener {
    private NetworkTableContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public NetworkTablePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(NetworkTableContract.View view){
        mView = view;
    }
}
