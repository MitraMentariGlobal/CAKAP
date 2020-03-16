package co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess;

import java.util.ArrayList;

import co.id.cakap.data.ReqInvoiceToBcSuccessData;
import co.id.cakap.data.ReqInvoiceToCompanySuccessData;
import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessContract;

public class ReqInvoiceToCompanySuccessPresenter implements ReqInvoiceToCompanySuccessContract.UserActionListener {
    private static ReqInvoiceToCompanySuccessContract.View mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    private ArrayList<ReqInvoiceToCompanySuccessData> arrayList;

    public ReqInvoiceToCompanySuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(ReqInvoiceToCompanySuccessContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
//        arrayList = new ArrayList<>();
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT01", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT02", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT03", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT04", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT05", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT06", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT07", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT08", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT09", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        arrayList.add(new ReqInvoiceToCompanySuccessData("BT010", "Blesstea Botol", "IDR 120.000", "25", "10", "IDR 1.200.000", "250"));
//        mView.setAdapter(arrayList);

        mView.setAdapter(
                mDataModel.getAllReqInvoiceToCompanySuccessData(),
                mDataModel.getAllBankInfoData()
        );
    }
}
