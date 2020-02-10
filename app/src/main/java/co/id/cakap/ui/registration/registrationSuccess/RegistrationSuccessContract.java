package co.id.cakap.ui.registration.registrationSuccess;

public class RegistrationSuccessContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(RegistrationSuccessContract.View view);
    }
}
