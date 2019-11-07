package co.id.cakap.ui.dashboard.restock.restockInvoice;

import java.util.List;

import co.id.cakap.data.RestockInvoiceData;

public class RestockInvoiceContract {
    public interface View {
        void initializeData();
        void setAdapter(List<RestockInvoiceData> resultData);
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener {
        void getData();
    }
}
