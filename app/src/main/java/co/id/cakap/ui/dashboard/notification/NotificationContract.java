package co.id.cakap.ui.dashboard.notification;

import android.content.SharedPreferences;

import java.util.List;

import co.id.cakap.adapter.NotificationAdapter;
import co.id.cakap.data.NotificationApiData;
import co.id.cakap.data.NotificationData;

public class NotificationContract {
    public interface View {
        void initializeData();
        void setAdapter(List<NotificationApiData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void updateList();
        void setEmptyScreen();
    }

    public interface UserActionListener {
        void getData(SharedPreferences sharedPreferences);
//        void deleteAllNotification();
        void changeReadStatus(NotificationData notificationData, int position);
        void readAllData();

        void getListNotification();
        void readItemNotification(String id);
        void readAllNotification();
        void deleteAllNotification();
    }
}
