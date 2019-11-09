package co.id.cakap.ui.reqInvoiceToBc;

public class ReqInvoiceToBcActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToBcActivityContract.View view);
    }
}
