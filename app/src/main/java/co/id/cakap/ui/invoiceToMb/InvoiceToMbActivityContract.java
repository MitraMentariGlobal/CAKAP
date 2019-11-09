package co.id.cakap.ui.invoiceToMb;

public class InvoiceToMbActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(InvoiceToMbActivityContract.View view);
    }
}
