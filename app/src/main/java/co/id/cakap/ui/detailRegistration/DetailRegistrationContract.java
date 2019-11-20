package co.id.cakap.ui.detailRegistration;

import java.util.List;

import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;

public class DetailRegistrationContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(DetailRegistrationContract.View view);
        void getData(String memberId);
    }
}
