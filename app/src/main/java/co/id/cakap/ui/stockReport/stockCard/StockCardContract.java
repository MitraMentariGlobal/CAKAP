package co.id.cakap.ui.stockReport.stockCard;

import java.util.List;

import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.data.StockCardData;

public class StockCardContract {
    public interface View {
        void initializeData();
        void setAdapter(List<StockCardData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDialogSearchData(List<ItemStockCard> resultData);
        void hideDialogSearchData(ItemStockCard stockCardData);
    }

    public interface UserActionListener {
        void getData(String tahun, String bulan, String itemId, String price);
        void getItemProduct(String param);
    }
}
