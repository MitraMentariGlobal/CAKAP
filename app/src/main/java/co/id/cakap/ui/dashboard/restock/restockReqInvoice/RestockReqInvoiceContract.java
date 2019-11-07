package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

public class RestockReqInvoiceContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener {
        void getData();
    }
}
