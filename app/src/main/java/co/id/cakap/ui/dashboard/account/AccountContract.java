package co.id.cakap.ui.dashboard.account;

public class AccountContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setSuccessResponse();
    }

    public interface UserActionListener {
        void getData();
    }
}
