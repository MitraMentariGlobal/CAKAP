package co.id.cakap.ui.dashboard.notification;

import android.content.SharedPreferences;

import java.util.List;

import co.id.cakap.adapter.NotificationAdapter;
import co.id.cakap.data.NotificationData;

public class NotificationContract {
    public interface View {
        void initializeData();
        void setAdapter(List<NotificationData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData(SharedPreferences sharedPreferences);
        void deleteAllNotification(NotificationAdapter notificationAdapter);
    }
}
