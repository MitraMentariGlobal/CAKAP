package co.id.cakap.ui.dashboard.activity.activityCashbill;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountContract;
import co.id.cakap.ui.dashboard.home.HomeContract;

public class ActivityCashbillPresenter implements ActivityCashbillContract.UserActionListener {
    private static WeakReference<ActivityCashbillContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ActivityCashbillData> arrayList;

    public ActivityCashbillPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityCashbillPresenter() {

    }

    public void setView(ActivityCashbillContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityCashbillContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
//        arrayList.add(new ActivityCashbillData("INV - 123123123123123", "2342432", "Nama Member 1", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 456789", "4566575643", "Nama Member 2", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 1111111111111", "74456544", "Nama Member 3", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 2222222222", "564578687", "Nama Member 4", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 3333333333333", "2497868", "Nama Member 5", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 444444444", "8567675", "Nama Member 6", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 5555555", "454654", "Nama Member 7", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 66666666", "45676879", "Nama Member 8", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 77777777777", "900897897", "Nama Member 9", "IDR 100.000.000", "123", "28 Jan 2020"));
//        arrayList.add(new ActivityCashbillData("INV - 888888888", "78987078", "Nama Member 10", "IDR 100.000.000", "123", "28 Jan 2020"));
        getView().setAdapter(arrayList);
    }
}
