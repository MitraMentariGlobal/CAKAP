package co.id.cakap.ui.dashboard;

public class DashboardContract {
    public interface View{
        void initializeData();
        void setErrorResponse(String message);
        void moveToActivity(int index);
        void openDialogUpdate(String url, boolean isCancelDialog);
    }

    public interface UserActionListener{
        void setView(DashboardContract.View view);
        void checkVersionUpdate();
    }
}
