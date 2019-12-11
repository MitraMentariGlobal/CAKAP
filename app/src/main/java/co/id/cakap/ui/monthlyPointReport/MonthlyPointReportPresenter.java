package co.id.cakap.ui.monthlyPointReport;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class MonthlyPointReportPresenter implements MonthlyPointReportContract.UserActionListener {
    private MonthlyPointReportContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public MonthlyPointReportPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MonthlyPointReportContract.View view){
        mView = view;
    }
}
