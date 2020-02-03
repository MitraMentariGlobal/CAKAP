package co.id.cakap.ui.downlineListing;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.id.cakap.data.DownlineListingData;

public class DownlineListingContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(int level, List<DownlineListingData> resultData, RecyclerView recyclerView, TextView txtTitle);
        void setAdapterDropdown(int totalDropdown);
    }

    public interface UserActionListener{
        void setView(DownlineListingContract.View view);
        void getData(int level, RecyclerView recyclerView, TextView txtTitle);
        void getDataDropdown();
    }
}
