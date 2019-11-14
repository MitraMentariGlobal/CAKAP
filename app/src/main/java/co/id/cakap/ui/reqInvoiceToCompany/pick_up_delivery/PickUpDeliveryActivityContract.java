package co.id.cakap.ui.reqInvoiceToCompany.pick_up_delivery;

import java.util.List;

import co.id.cakap.data.AddressData;

public class PickUpDeliveryActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<AddressData> resultData);
    }

    public interface UserActionListener{
        void setView(PickUpDeliveryActivityContract.View view);
        void getData();
    }
}
