package co.id.cakap.ui.omset;

public class OmsetActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(OmsetActivityContract.View view);
    }
}
