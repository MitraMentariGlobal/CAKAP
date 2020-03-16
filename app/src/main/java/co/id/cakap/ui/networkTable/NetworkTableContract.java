package co.id.cakap.ui.networkTable;

import java.util.List;

import co.id.cakap.data.NetworkTableData;

public class NetworkTableContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<NetworkTableData> resultData);
    }

    public interface UserActionListener{
        void setView(NetworkTableContract.View view);
        void getData();
    }
}
