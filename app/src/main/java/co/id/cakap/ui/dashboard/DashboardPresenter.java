package co.id.cakap.ui.dashboard;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;

public class DashboardPresenter implements DashboardContract.UserActionListener{
    private DashboardContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public DashboardPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(DashboardContract.View view){
        mView = view;
    }
}
