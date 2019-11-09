package co.id.cakap.ui.feeBCMB;

public class FeeBcmbActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(FeeBcmbActivityContract.View view);
    }
}
