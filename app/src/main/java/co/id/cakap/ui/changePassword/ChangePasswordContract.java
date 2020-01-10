package co.id.cakap.ui.changePassword;

public class ChangePasswordContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setSuccessResponse();
        void openDialogPin();
        void setErrorResponse(String message);
        void setErrorOldPassword(boolean isError);
        void setErrorNewPassword(boolean isError, boolean isFilled);
        void setErrorRetypeNewPassword(boolean isError, boolean isFilled);
    }

    public interface UserActionListener{
        void setView(ChangePasswordContract.View view);
        void changeData(String oldPassword, String newPassword, String retypeNewPassword, String pin);
        void checkData(String oldPassword, String newPassword, String retypeNewPassword);
    }
}
