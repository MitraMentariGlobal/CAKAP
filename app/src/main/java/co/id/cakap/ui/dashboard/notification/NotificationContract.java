package co.id.cakap.ui.dashboard.notification;

import java.util.List;

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
        void getData();
    }
}
