package co.id.cakap.ui.dashboard.inbox;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.ActivityContract;
import co.id.cakap.ui.dashboard.home.HomeContract;

public class InboxPresenter implements InboxContract.UserActionListener {
    private InboxContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public InboxPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(InboxContract.View view){
        mView = view;
    }
}
