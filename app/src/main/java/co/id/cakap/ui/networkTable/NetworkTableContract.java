package co.id.cakap.ui.networkTable;

public class NetworkTableContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(NetworkTableContract.View view);
    }
}
