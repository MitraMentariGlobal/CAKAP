package co.id.cakap.ui.invoiceToMb.invoiceToMbSuccess;

import java.util.List;

import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.InvoiceToMbSuccessData;

public class InvoiceToMbSuccessContract {
    public interface View {
        void initializeData();
        void setAdapter(List<InvoiceToMbSuccessData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
