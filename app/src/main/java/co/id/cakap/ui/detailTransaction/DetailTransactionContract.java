package co.id.cakap.ui.detailTransaction;

import java.util.List;

import co.id.cakap.data.DetailTransactionData;

public class DetailTransactionContract {
    public interface View {
        void initializeData();
        void setAdapter(List<DetailTransactionData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData(String itemId);
    }
}
