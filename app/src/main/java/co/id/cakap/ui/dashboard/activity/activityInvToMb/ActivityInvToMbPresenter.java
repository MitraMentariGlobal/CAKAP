package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;

public class ActivityInvToMbPresenter implements ActivityInvToMbContract.UserActionListener {
    private ActivityInvToMbContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ActivityInvToMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityInvToMbContract.View view){
        mView = view;
    }
}
