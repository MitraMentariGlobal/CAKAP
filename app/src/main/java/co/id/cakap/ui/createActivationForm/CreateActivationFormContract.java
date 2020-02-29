package co.id.cakap.ui.createActivationForm;

import java.util.List;

import co.id.cakap.data.ActivationKitData;
import co.id.cakap.data.ActivationSubmitItemFormData;
import co.id.cakap.data.ActivityInvToMbData;
import co.id.cakap.data.ItemSearchRegistrationData;

public class CreateActivationFormContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<ActivationKitData> resultData);
        void insertOrUpdateItemData(ActivationSubmitItemFormData activationSubmitItemFormData);
        void setSuccessResponse();
        void openPinDialog();
    }

    public interface UserActionListener{
        void setView(CreateActivationFormContract.View view);
        void getData(String id);
        void submitData(String pin, ActivityInvToMbData activityInvToMbData, List<ActivationSubmitItemFormData> submitItemFormDataList);
    }
}
