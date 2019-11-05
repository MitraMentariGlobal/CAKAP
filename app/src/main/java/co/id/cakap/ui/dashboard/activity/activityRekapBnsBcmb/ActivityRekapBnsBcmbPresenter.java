package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbContract;

public class ActivityRekapBnsBcmbPresenter implements ActivityRekapBnsBcmbContract.UserActionListener {
    private ActivityRekapBnsBcmbContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ActivityRekapBnsBcmbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityRekapBnsBcmbContract.View view){
        mView = view;
    }
}
