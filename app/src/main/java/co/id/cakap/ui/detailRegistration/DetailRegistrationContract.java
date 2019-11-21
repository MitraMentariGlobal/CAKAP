package co.id.cakap.ui.detailRegistration;

import java.util.List;

import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.data.OperationUserStatusData;

public class DetailRegistrationContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDialogSearchData(List<ItemSearchRegistrationData> resultData, int idFrom);
        void hideDialogSearchData(ItemSearchRegistrationData itemSearchRegistrationData, int idFrom);
    }

    public interface UserActionListener{
        void setView(DetailRegistrationContract.View view);
        void getData(String memberId);
        void getDataRecId(String param);
        void getDataSponsorId(String param);
    }
}
