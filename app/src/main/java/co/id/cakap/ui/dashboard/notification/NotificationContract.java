package co.id.cakap.ui.dashboard.notification;

public class NotificationContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {

    }
}
