package co.id.cakap.ui.dashboard.home;

import java.util.List;

public class HomeContract {
    public interface View{
        void setAdapter();
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void getData();
        void saveData();
    }
}
