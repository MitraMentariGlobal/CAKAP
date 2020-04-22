package co.id.cakap.ui.stockReport.stockUpdate;

import java.util.List;

import co.id.cakap.data.StockCardData;
import co.id.cakap.data.StockUpdate;
import co.id.cakap.data.StockUpdateData;

public class StockUpdateContract {
    public interface View {
        void initializeData();
        void setAdapter(StockUpdate resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
