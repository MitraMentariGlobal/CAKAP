package co.id.cakap.ui.monthlyPointReport;

public class MonthlyPointReportContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(MonthlyPointReportContract.View view);
    }
}
