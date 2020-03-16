package co.id.cakap.ui.detailTransaction;

import java.util.List;

import co.id.cakap.data.DetailTransactionData;
import co.id.cakap.network.ApiResponseDetailTransaction;

public class DetailTransactionContract {
    public interface View {
        void initializeData();
        void setAdapter(ApiResponseDetailTransaction apiResponseDetailTransaction);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData(String endpoint, String itemId, String kodeUnik);
    }
}
