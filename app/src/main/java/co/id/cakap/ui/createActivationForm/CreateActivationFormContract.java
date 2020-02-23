package co.id.cakap.ui.createActivationForm;

import java.util.List;

import co.id.cakap.data.ItemSearchRegistrationData;

public class CreateActivationFormContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(CreateActivationFormContract.View view);
        void getData();
    }
}
