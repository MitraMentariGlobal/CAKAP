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
        void changeAddress(String province, String kota, String address);
        void setProvinsiData(List<String> provinsiDataList, List<String> provinsiIdList);
        void setKotaData(List<String> kotaDataList, List<String> kotaIdList);
    }

    public interface UserActionListener{
        void setView(PickUpDeliveryActivityContract.View view);
        void getProvinsi();
        void getKota(String id);
        void getData();
    }
}
