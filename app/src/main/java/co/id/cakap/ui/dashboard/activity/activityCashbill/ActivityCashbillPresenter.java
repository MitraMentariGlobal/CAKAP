package co.id.cakap.ui.dashboard.activity.activityCashbill;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountContract;
import co.id.cakap.ui.dashboard.home.HomeContract;

public class ActivityCashbillPresenter implements ActivityCashbillContract.UserActionListener {
    private ActivityCashbillContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ActivityCashbillPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityCashbillContract.View view){
        mView = view;
    }
}
