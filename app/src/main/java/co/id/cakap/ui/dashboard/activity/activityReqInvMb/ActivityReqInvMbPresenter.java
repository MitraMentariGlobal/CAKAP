package co.id.cakap.ui.dashboard.activity.activityReqInvMb;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityReqInvMbData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbContract;

public class ActivityReqInvMbPresenter implements ActivityReqInvMbContract.UserActionListener {
    private static WeakReference<ActivityReqInvMbContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ActivityReqInvMbData> arrayList;

    public ActivityReqInvMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityReqInvMbPresenter() {

    }

    public void setView(ActivityReqInvMbContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityReqInvMbContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
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
        getView().setAdapter(arrayList);
    }
}
