package co.id.cakap.ui.dashboard.restock.restockReceiveStock;

import java.util.List;

import co.id.cakap.data.RestockReceiveStockData;

public class RestockReceiveStockContract {
    public interface View {
        void initializeData();
        void setAdapter(List<RestockReceiveStockData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction(RestockReceiveStockData restockReceiveStockData);
        void openPinDialog(RestockReceiveStockData restockReceiveStockData);
    }

    public interface UserActionListener {
        void getData();
        void actionTransaction(RestockReceiveStockData restockReceiveStockData, String pin);
    }
}
