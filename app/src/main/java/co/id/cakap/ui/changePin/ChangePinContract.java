package co.id.cakap.ui.changePin;

import android.content.Context;

public class ChangePinContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setSuccessResponse();
        void setErrorResponse(String message);
        void setErrorOldPin(boolean isError);
        void setErrorNewPin(boolean isError, boolean isFilled);
        void setErrorRetypeNewPin(boolean isError, boolean isFilled);
    }

    public interface UserActionListener{
        void setView(ChangePinContract.View view, Context context);
        void changeData(String oldPin, String newPin, String retypeNewPin);
        void checkData(String oldPin, String newPin, String retypeNewPin);
    }
}
