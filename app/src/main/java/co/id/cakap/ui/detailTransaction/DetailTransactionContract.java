package co.id.cakap.ui.detailTransaction;

public class DetailTransactionContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
