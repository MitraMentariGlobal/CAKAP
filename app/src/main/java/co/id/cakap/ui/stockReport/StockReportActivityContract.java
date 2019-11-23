package co.id.cakap.ui.stockReport;

public class StockReportActivityContract {
    public interface View{
        void initializeData();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(StockReportActivityContract.View view);
    }
}
