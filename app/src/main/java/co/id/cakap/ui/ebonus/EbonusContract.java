package co.id.cakap.ui.ebonus;

import java.util.List;

import co.id.cakap.data.EbonusData;
import co.id.cakap.data.ItemEbonusCard;
import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.StockCardData;
import co.id.cakap.network.ApiResponseEbonusMember;

public class EbonusContract {
    public interface View {
        void initializeData();
        void setAdapter(ApiResponseEbonusMember apiResponseEbonusMember);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDialogSearchData(List<ItemEbonusCard> resultData);
        void hideDialogSearchData(ItemEbonusCard stockCardData);
    }

    public interface UserActionListener {
        void setView(EbonusContract.View view);
        void getData(String tahun, String bulan);
        void getItemProduct(String param);
    }
}
