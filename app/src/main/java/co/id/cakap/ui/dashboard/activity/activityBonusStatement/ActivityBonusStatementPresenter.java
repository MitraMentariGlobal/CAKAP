package co.id.cakap.ui.dashboard.activity.activityBonusStatement;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class ActivityBonusStatementPresenter implements ActivityBonusStatementContract.UserActionListener {
    private ActivityBonusStatementContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public ActivityBonusStatementPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(ActivityBonusStatementContract.View view){
        mView = view;
    }
}
