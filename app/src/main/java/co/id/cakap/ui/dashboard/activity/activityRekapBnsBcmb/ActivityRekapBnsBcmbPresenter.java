package co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import co.id.cakap.data.ActivityRekapBnsBcmbData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbContract;

public class ActivityRekapBnsBcmbPresenter implements ActivityRekapBnsBcmbContract.UserActionListener {
    private static WeakReference<ActivityRekapBnsBcmbContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ActivityRekapBnsBcmbData> arrayList;

    public ActivityRekapBnsBcmbPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public ActivityRekapBnsBcmbPresenter() {

    }

    public void setView(ActivityRekapBnsBcmbContract.View view){
        mView = new WeakReference<>(view);
    }

    public ActivityRekapBnsBcmbContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        arrayList.add(new ActivityRekapBnsBcmbData("0000012", "Nama Member", "081912345678", "IDR 100.000.000"));
        getView().setAdapter(arrayList);
    }
}
