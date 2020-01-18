package co.id.cakap.ui.myProfile;

import android.content.Context;

import java.util.List;

import co.id.cakap.data.JenisKelaminData;
import co.id.cakap.data.ProfileData;
import co.id.cakap.data.ReligionData;

public class MyProfileActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void openDialogCheck();
        void openSuccessBottomSheet();
        void setErrorResponse(String message);
        void setProfileData(ProfileData profileData);
        void setJenisKelaminData(List<JenisKelaminData> jenisKelaminDataList);
        void setReligionData(List<String> religionDataList);
        void setBankData(List<String> bankDataList, List<String> bankIdList);
        void setErrorPob(boolean isError);
        void setErrorDob(boolean isError);
        void setErrorEmail(boolean isError, boolean isFilled);
        void setErrorHp(boolean isError);
        void setErrorTelp(boolean isError);
        void setErrorKodePos(boolean isError, boolean isFilled);
        void setErrorNamaPewaris(boolean isError);
        void setErrorHubungan(boolean isError);
        void setErrorCabang(boolean isError);
        void setErrorNorek(boolean isError);
    }

    public interface UserActionListener{
        void setView(MyProfileActivityContract.View view, Context context);
        void getJenisKelamin();
        void getReligion();
        void getBank();
        void getProfileData();
        void checkData(String pob, String dob, String email, String hp, String telp, String kodePos,
                       String namaPewaris, String hubungan, String cabang, String norek,
                       boolean isFilledBank, boolean isFilledDob, boolean isFilledPostalCode);
        void sendProfileData(
                String noKtp, String alamat, String kodePos, String npwp, String statusPernikahan,
                String suami, String religion, String anak, String pekerjaan, String hubungan, String ahliWaris,
                String city, String email, String pob, String gender, String date, String hp, String telp, String fax,
                String kotaId, String province, String bankAcc, String bankId, String norek, String cabang, String area,
                String nama, String pin);
    }
}
