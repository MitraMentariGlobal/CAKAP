package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import java.util.ArrayList;

import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbContract;

public class ActivityReqInvMbPresenter implements ActivityReqInvMbContract.UserActionListener {
    private ActivityReqInvMbContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    private ArrayList<ActivityReqInvMbData> arrayList;

    public ActivityReqInvMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ActivityReqInvMbContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        arrayList.add(new ActivityReqInvMbData("INV - 789789789789789", "BC123", "Nama Sub Stockist", "IDR 100.000.000", "123", "28 Jan 2020"));
        mView.setAdapter(arrayList);
    }
}
