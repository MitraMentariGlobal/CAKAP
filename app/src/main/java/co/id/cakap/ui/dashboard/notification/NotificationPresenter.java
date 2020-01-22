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

public class NotificationPresenter implements NotificationContract.UserActionListener {
    private static WeakReference<NotificationContract.View> mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;
    private NotificationData mNotificationData;
    private String mTitle = "";
    private String mBody = "";
    private String mDate = "";
    private int mMoveNotification = 0;

    private List<NotificationData> arrayList;

    public NotificationPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
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
        mMoveNotification = sharedPreferences.getInt(Constant.FIREBASE_NOTIFICATION_MOVE, 0);
        mTitle = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_TITLE, "");
        mBody = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_BODY, "");
        mDate = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_DATE, "");

        if (mMoveNotification == 1) {
            mNotificationData = new NotificationData(mTitle, mBody, mDate, false);
            mDataModel.insertNotificationData(mNotificationData);

            SharedPreferences.Editor sharedPrefEd = sharedPreferences.edit();
            sharedPrefEd.putInt(Constant.FIREBASE_NOTIFICATION_MOVE, 0);
            sharedPrefEd.apply();
        }

        arrayList = mDataModel.getAllNotificationData();
        getView().setAdapter(arrayList);
    }

    @Override
    public void deleteAllNotification(NotificationAdapter notificationAdapter) {
        mDataModel.deleteNotificationData();
        notificationAdapter.notifyDataSetChanged();
        getView().hideProgressBar();
    }
}
