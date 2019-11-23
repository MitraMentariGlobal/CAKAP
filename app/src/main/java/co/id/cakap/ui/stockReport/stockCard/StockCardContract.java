package co.id.cakap.ui.stockReport.stockCard;

import java.util.List;

import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.StockCardData;

public class StockCardContract {
    public interface View {
        void initializeData();
        void setAdapter(List<StockCardData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
