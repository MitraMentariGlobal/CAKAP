package co.id.cakap.ui.pickUpDelivery;

import java.util.List;

import co.id.cakap.data.AddressHistoryData;
import co.id.cakap.data.AddressData;

public class PickUpDeliveryActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(AddressData addressData);
        void chooseAddress(AddressHistoryData addressHistoryData);
        void changeAddress(AddressHistoryData addressHistoryData);
        void setProvinsiData(List<String> provinsiDataList, List<String> provinsiIdList);
        void setKotaData(List<String> kotaDataList, List<String> kotaIdList);
    }

    public interface UserActionListener{
        void setView(PickUpDeliveryActivityContract.View view);
        void getProvinsi();
        void getKota(String id);
        void getData();
        void addAddress(String alamat, String kotaId);
        void editAddress(String alamat, String kotaId, String idAlamat);
    }
}
