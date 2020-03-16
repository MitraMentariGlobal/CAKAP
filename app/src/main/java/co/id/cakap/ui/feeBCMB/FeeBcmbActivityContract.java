package co.id.cakap.ui.feeBCMB;

import co.id.cakap.data.FeeBCMBData;

public class FeeBcmbActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setData(FeeBCMBData feeBCMBData);
    }

    public interface UserActionListener{
        void setView(FeeBcmbActivityContract.View view);
        void getData(String tahun, String bulan);
    }
}
