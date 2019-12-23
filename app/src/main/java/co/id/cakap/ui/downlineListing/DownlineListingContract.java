package co.id.cakap.ui.downlineListing;

import java.util.List;

import co.id.cakap.data.DownlineListingData;

public class DownlineListingContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<DownlineListingData> resultData);
    }

    public interface UserActionListener{
        void setView(DownlineListingContract.View view);
        void getData();
    }
}
