package co.id.cakap.ui.registration;

import java.util.List;

import co.id.cakap.data.RegistrationData;

public class RegistrationActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<RegistrationData> resultData);
        void openDetailRegistration(String activationCode, String member_id);
    }

    public interface UserActionListener{
        void setView(RegistrationActivityContract.View view);
        void getData();
    }
}
