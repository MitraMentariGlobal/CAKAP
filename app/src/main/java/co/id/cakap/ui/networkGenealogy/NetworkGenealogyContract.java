package co.id.cakap.ui.networkGenealogy;

import co.id.cakap.data.NetworkGenealogyData;

public class NetworkGenealogyContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setData(NetworkGenealogyData networkGenealogyData);
    }

    public interface UserActionListener{
        void setView(NetworkGenealogyContract.View view);
        void getData(String memberId);
    }
}
