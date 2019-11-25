package co.id.cakap.ui.changePin;

public class ChangePinContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(ChangePinContract.View view);
    }
}
