package co.id.cakap.ui.stockReport;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.cashbill.CashbillActivityContract;

public class StockReportActivityPresenter implements StockReportActivityContract.UserActionListener {
    private StockReportActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public StockReportActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(StockReportActivityContract.View view){
        mView = view;
    }
}
