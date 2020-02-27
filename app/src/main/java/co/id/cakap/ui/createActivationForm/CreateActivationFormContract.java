package co.id.cakap.ui.createActivationForm;

import java.util.List;

import co.id.cakap.data.ActivationKitData;
import co.id.cakap.data.ItemSearchRegistrationData;

public class CreateActivationFormContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ActivationKitData> resultData);
        void setSuccessResponse();
    }

    public interface UserActionListener{
        void setView(CreateActivationFormContract.View view);
        void getData(String id);
        void submitData();
    }
}
