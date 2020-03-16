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
        void successInputData();

        void openConfirmationDialog();
        void setErrorRecId(boolean isError);
        void setErrorSponsorId(boolean isError);
        void setErrorName(boolean isError);
        void setErrorKtp(boolean isError);
        void setErrorReligion(boolean isError);
        void setErrorAlamat(boolean isError);
        void setErrorProvinsi(boolean isError);
        void setErrorKota(boolean isError);
        void setErrorNamaPewaris(boolean isError);
        void setErrorHubungan(boolean isError);

        void setReligionData(List<String> religionDataList);
        void setBankData(List<String> bankDataList, List<String> bankIdList);
        void setProvinsiData(List<String> provinsiDataList, List<String> provinsiIdList);
        void setKotaData(List<String> kotaDataList, List<String> kotaIdList);
    }

    public interface UserActionListener{
        void setView(DetailRegistrationContract.View view);
        void getData(String memberId, int idFrom);
        void getDataRecId(String param);
        void getDataSponsorId(String param);
        void getReligion();
        void getProvinsi();
        void getKota(String id);
        void getBank();
        void checkData(String recId, String sponsorId, String name, String ktp, String religion, String alamat,
                       String provinsi, String kota, String pewaris, String hubungan);
        void sendRegistrationData(String pin, String recId, String sponsorId, String memberId, String name,
                                  String ktp, String gender, String pob, String dob, String religion, String email,
                                  String noTelp, String noHp, String alamat, String kotaId, String kodePos,
                                  String activation, String pewaris, String hubungan, String bankId, String cabang,
                                  String namaNasabah, String norek);

    }
}
