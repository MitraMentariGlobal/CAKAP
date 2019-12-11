package co.id.cakap.ui.ebonus;

public class EbonusContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(EbonusContract.View view);
    }
}
