package co.id.cakap.ui.registration;

public class RegistrationActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(RegistrationActivityContract.View view);
    }
}
