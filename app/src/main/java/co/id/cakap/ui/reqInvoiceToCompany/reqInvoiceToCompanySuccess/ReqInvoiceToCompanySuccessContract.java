package co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess;

import java.util.List;

import co.id.cakap.data.BankInfoData;
import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.data.ReqInvoiceToCompanySuccessData;

public class ReqInvoiceToCompanySuccessContract {
    public interface View {
        void initializeData();
        void setAdapter(List<ReqInvoiceToCompanySuccessData> resultData, List<BankInfoData> bankInfoDataList);
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener {
        void getData();
    }
}
