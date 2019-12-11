package co.id.cakap.ui.networkGenealogy;

public class NetworkGenealogyContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(NetworkGenealogyContract.View view);
    }
}
