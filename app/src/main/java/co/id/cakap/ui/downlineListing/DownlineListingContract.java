package co.id.cakap.ui.downlineListing;

public class DownlineListingContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(DownlineListingContract.View view);
    }
}
