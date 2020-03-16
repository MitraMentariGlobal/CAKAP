package co.id.cakap.ui.dashboard.home;

import java.util.List;

import co.id.cakap.data.ResultDataLogin;
import co.id.cakap.network.ApiResponseLogin;

public class HomeContract {
    public interface View{
        void setAdapter(ResultDataLogin resultDataLogin);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void getData();
        void saveData(ApiResponseLogin apiResponseLogin);
    }
}
