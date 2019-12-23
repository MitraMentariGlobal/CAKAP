package co.id.cakap.ui.monthlyPointReport;

import java.util.ArrayList;

import co.id.cakap.data.MonthlyPointReportData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;

public class MonthlyPointReportPresenter implements MonthlyPointReportContract.UserActionListener {
    private MonthlyPointReportContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private ArrayList<MonthlyPointReportData> arrayList;

    public MonthlyPointReportPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MonthlyPointReportContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        arrayList.add(new MonthlyPointReportData("0000020", "BAMBANG ROYADI", "DTR*5", "110", "110", "31,625", "ACTIVE"));
        mView.setAdapter(arrayList);
    }
}
