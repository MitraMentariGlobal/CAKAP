package co.id.cakap.ui.dashboard;

public class DashboardContract {
    public interface View{
        void initializeData();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(DashboardContract.View view);
    }
}
