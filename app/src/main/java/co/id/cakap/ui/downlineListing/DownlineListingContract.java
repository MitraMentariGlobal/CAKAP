package co.id.cakap.ui.downlineListing;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.id.cakap.data.DownlineListingData;
import co.id.cakap.data.LevelData;

public class DownlineListingContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(LevelData levelData, List<DownlineListingData> resultData, RecyclerView recyclerView, TextView txtTitle);
        void setAdapterDropdown(List<LevelData> levelDataList);
    }

    public interface UserActionListener{
        void setView(DownlineListingContract.View view);
        void getData(LevelData levelData, RecyclerView recyclerView, TextView txtTitle);
        void getDataDropdown();
    }
}
