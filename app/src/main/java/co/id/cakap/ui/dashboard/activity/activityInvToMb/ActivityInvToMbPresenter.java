package co.id.cakap.ui.dashboard.activity.activityInvToMb;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillContract;

public class ActivityInvToMbPresenter implements ActivityInvToMbContract.UserActionListener {
    private static WeakReference<ActivityInvToMbContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ActivityInvToMbData> arrayList;

    public ActivityInvToMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityInvToMbPresenter() {

    }

    public void setView(ActivityInvToMbContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityInvToMbContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
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
        getView().setAdapter(arrayList);
    }
}
