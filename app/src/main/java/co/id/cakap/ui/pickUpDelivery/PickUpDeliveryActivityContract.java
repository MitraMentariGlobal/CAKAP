package co.id.cakap.ui.pickUpDelivery;

import java.util.List;

import co.id.cakap.data.AddressData;

public class PickUpDeliveryActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<AddressData> resultData);
        void changeAddress(String kota, String address);
    }

    public interface UserActionListener{
        void setView(PickUpDeliveryActivityContract.View view);
        void getData();
    }
}
