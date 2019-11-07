package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

import java.util.List;

import co.id.cakap.data.RestockReceiveStockData;

public class RestockReceiveStockContract {
    public interface View {
        void initializeData();
        void setAdapter(List<RestockReceiveStockData> resultData);
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener {
        void getData();
    }
}
