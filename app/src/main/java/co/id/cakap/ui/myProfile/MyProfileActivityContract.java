package co.id.cakap.ui.myProfile;

public class MyProfileActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(MyProfileActivityContract.View view);
    }
}