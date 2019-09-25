package co.id.cakap.ui.login;

import java.util.List;

import co.id.cakap.data.ResultData;
import co.id.cakap.network.ApiResponse;

public class LoginContract {
    public interface View{
        void initializeData();
    }

    public interface UserActionListener{
        void setView(LoginContract.View view);
    }
}
