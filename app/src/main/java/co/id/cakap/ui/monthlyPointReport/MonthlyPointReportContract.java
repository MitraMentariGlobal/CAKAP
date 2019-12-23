package co.id.cakap.ui.monthlyPointReport;

import java.util.List;

import co.id.cakap.data.MonthlyPointReportData;

public class MonthlyPointReportContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<MonthlyPointReportData> resultData);
    }

    public interface UserActionListener{
        void setView(MonthlyPointReportContract.View view);
        void getData();
    }
}
