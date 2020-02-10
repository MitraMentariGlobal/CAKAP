package co.id.cakap.ui.ebonus;

import java.util.List;

import co.id.cakap.data.EbonusData;
import co.id.cakap.data.ItemEbonusCard;
import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.StockCardData;

public class EbonusContract {
    public interface View {
        void initializeData();
        void setAdapter(List<EbonusData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDialogSearchData(List<ItemEbonusCard> resultData);
        void hideDialogSearchData(ItemEbonusCard stockCardData);
    }

    public interface UserActionListener {
        void setView(EbonusContract.View view);
        void getData();
        void getItemProduct(String param);
    }
}
