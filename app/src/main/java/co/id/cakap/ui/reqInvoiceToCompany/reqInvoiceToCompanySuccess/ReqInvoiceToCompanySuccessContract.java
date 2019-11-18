package co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess;

import java.util.List;

import co.id.cakap.data.ReqInvoiceToBcSuccessData;

public class ReqInvoiceToCompanySuccessContract {
    public interface View {
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
