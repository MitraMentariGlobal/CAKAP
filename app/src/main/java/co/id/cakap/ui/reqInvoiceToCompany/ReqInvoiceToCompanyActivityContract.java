package co.id.cakap.ui.reqInvoiceToCompany;

public class ReqInvoiceToCompanyActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(ReqInvoiceToCompanyActivityContract.View view);
    }
}
