package co.id.cakap.ui.dashboard.activity.activityCashbill;

import java.util.ArrayList;

import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountContract;
import co.id.cakap.ui.dashboard.home.HomeContract;

public class ActivityCashbillPresenter implements ActivityCashbillContract.UserActionListener {
    private ActivityCashbillContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    private ArrayList<ActivityCashbillData> arrayList;

    public ActivityCashbillPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityCashbillContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "0000011", "Nama Member", "IDR 100.000.000", "123", "28 Jan 2020"));
        mView.setAdapter(arrayList);
    }
}
