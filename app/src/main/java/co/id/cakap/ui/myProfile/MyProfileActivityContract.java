package co.id.cakap.ui.myProfile;

import co.id.cakap.data.ProfileData;

public class MyProfileActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setData(ProfileData profileData);
    }

    public interface UserActionListener{
        void setView(MyProfileActivityContract.View view);
        void getJenisKelamiin();
        void getReligion();
        void getProfileData();
    }
}
