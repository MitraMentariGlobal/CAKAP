package co.id.cakap.ui.dashboard.notification;

import android.content.SharedPreferences;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import co.id.cakap.adapter.NotificationAdapter;
import co.id.cakap.data.NotificationData;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;

public class NotificationPresenter implements NotificationContract.UserActionListener {
    private static WeakReference<NotificationContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;
    private static NotificationData mNotificationData;
    private static String mTitle = "";
    private static String mBody = "";
    private static String mDate = "";
    private int mMoveNotification = 0;

    private List<NotificationData> arrayList;

    public NotificationPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public NotificationPresenter() {

    }

    public void setView(NotificationContract.View view){
        mView = new WeakReference<>(view);
    }

    public NotificationContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(SharedPreferences sharedPreferences) {
        getView().showProgressBar();

        mMoveNotification = sharedPreferences.getInt(Constant.FIREBASE_NOTIFICATION_MOVE, 0);
        mTitle = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_TITLE, "");
        mBody = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_BODY, "");
        mDate = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_DATE, "");

        if (mMoveNotification == 1) {
            mNotificationData = new NotificationData(
                    (long) mDataModel.getAllNotificationData().size() + 1,
                    mTitle,
                    mBody,
                    mDate,
                    false);
            mDataModel.insertNotificationData(mNotificationData);

            SharedPreferences.Editor sharedPrefEd = sharedPreferences.edit();
            sharedPrefEd.putInt(Constant.FIREBASE_NOTIFICATION_MOVE, 0);
            sharedPrefEd.apply();
        }

        arrayList = mDataModel.getAllNotificationData();
        getView().setAdapter(arrayList);
//
//        arrayList = new ArrayList<>();
//        arrayList.add(new NotificationData((long) 1, "Title Notification 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 2,"Title Notification 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 3,"Title Notification 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 4,"Title Notification 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 5,"Title Notification 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 6,"Title Notification 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 7,"Title Notification 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 8,"Title Notification 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//        arrayList.add(new NotificationData((long) 9,"Title Notification 9", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", "28 Jan 2020", false));
//
//        getView().setAdapter(arrayList);
    }

    @Override
    public void deleteAllNotification() {
        mDataModel.deleteNotificationData();
        getView().updateList();
        getView().hideProgressBar();
    }

    @Override
    public void readAllData() {
        getView().showProgressBar();

        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setIsRead(true);
        }

        getView().updateList();
        getView().hideProgressBar();
    }
}
