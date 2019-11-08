package co.id.cakap.ui.dashboard.restock.restockReqInvoice;

import java.util.List;

import co.id.cakap.data.RestockReqInvoiceData;

public class RestockReqInvoiceContract {
    public interface View {
        void initializeData();
        void setAdapter(List<RestockReqInvoiceData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void openDetailTransaction();
    }

    public interface UserActionListener {
        void getData();
    }
}
