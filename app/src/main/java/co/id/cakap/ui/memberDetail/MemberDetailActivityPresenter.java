package co.id.cakap.ui.memberDetail;

import co.id.cakap.model.DataModel;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivityContract;

public class MemberDetailActivityPresenter implements MemberDetailActivityContract.UserActionListener {
    private MemberDetailActivityContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public MemberDetailActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(MemberDetailActivityContract.View view){
        mView = view;
    }
}
