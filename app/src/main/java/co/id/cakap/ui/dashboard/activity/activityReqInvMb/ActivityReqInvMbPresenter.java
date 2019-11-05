package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbContract;

public class ActivityReqInvMbPresenter implements ActivityReqInvMbContract.UserActionListener {
    private ActivityReqInvMbContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ActivityReqInvMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityReqInvMbContract.View view){
        mView = view;
    }
}
