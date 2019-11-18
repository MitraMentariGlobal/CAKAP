package co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess;

import java.util.List;

import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;

public class ReqInvoiceToBcSuccessContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ReqInvoiceToBcSuccessData> resultData);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
