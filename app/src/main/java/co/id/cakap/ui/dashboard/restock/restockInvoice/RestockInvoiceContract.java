package co.id.cakap.ui.dashboard.restock.restockInvoice;

public class RestockInvoiceContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener {
        void getData();
    }
}
