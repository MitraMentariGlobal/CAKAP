package co.id.cakap.ui.dashboard.notification;

import android.content.SharedPreferences;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import co.id.cakap.adapter.NotificationAdapter;
import co.id.cakap.data.NotificationData;
import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.utils.Logger;

public class NotificationPresenter implements NotificationContract.UserActionListener {
    private static WeakReference<NotificationContract.View> mView;
    private static MainRepository mMainRepository;
    private static ResultDataLogin mResultDataLogin;
    private static DataModel mDataModel;
    private static NotificationData mNotificationData;
    private static String mTitle = "";
    private static String mBody = "";
    private static String mDate = "";
    private int mMoveNotification = 0;

    private List<NotificationData> arrayList = new ArrayList<>();
    private List<NotificationData> arrayWillSet = new ArrayList<>();

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

        mResultDataLogin = mDataModel.getAllResultDataLogin().get(0);
        mMoveNotification = sharedPreferences.getInt(Constant.FIREBASE_NOTIFICATION_MOVE, 0);
        mTitle = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_TITLE, "");
        mBody = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_BODY, "");
        mDate = sharedPreferences.getString(Constant.FIREBASE_NOTIFICATION_DATE, "");

        if (mMoveNotification == 1) {
            mNotificationData = new NotificationData(
                    (long) mDataModel.getAllNotificationData().size() + 1,
                    mResultDataLogin.getMember_id(),
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
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getMember_id().contains(mResultDataLogin.getMember_id())) {
                arrayWillSet.add(arrayList.get(i));
            }
        }

        getView().setAdapter(arrayWillSet);
    }

    @Override
    public void deleteAllNotification() {
        mDataModel.deleteNotificationData();
        getView().setEmptyScreen();
        getView().updateList();
        getView().hideProgressBar();
    }

    @Override
    public void changeReadStatus(NotificationData notificationData, int position) {
        mDataModel.updateNotificationData(notificationData);
        getView().updateList();
    }

    @Override
    public void readAllData() {
        getView().showProgressBar();

        for (int i = 0; i < arrayWillSet.size(); i++) {
            if (!(arrayWillSet.get(i).getIsRead())) {
                arrayWillSet.get(i).setIsRead(true);
                mDataModel.updateNotificationData(arrayWillSet.get(i));
            }
        }

        getView().updateList();
        getView().hideProgressBar();
    }
}
