package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import java.util.ArrayList;

import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;

public class ActivityInvToMbPresenter implements ActivityInvToMbContract.UserActionListener {
    private ActivityInvToMbContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    private ArrayList<ActivityInvToMbData> arrayList;

    public ActivityInvToMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityInvToMbContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityInvToMbData("INV - 456456456456456", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        mView.setAdapter(arrayList);
    }
}
