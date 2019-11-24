package co.id.cakap.ui.omset;

import java.util.List;

import co.id.cakap.data.OmsetData;

public class OmsetActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<OmsetData> resultData);
    }

    public interface UserActionListener{
        void setView(OmsetActivityContract.View view);
        void getData();
    }
}
