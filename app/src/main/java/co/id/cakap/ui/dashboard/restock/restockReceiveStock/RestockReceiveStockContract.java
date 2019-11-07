package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

public class RestockReceiveStockContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener {
        void getData();
    }
}
