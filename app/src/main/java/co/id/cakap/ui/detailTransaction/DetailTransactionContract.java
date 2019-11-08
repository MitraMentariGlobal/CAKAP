package co.id.cakap.ui.detailTransaction;

import java.util.List;

import co.id.cakap.data.DetailTransaksiData;

public class DetailTransactionContract {
    public interface View {
        void initializeData();
        void setAdapter(List<DetailTransaksiData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
