package co.id.cakap.ui.main;

import java.util.List;

import co.id.cakap.data.ResultData;
import co.id.cakap.network.ApiResponse;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainContract {
    public interface View{
        void setAdapter(List<ResultData> resultData);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void setView(MainContract.View view);
        void getData();
        void saveData(ApiResponse apiResponse);
    }
}
