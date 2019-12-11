package co.id.cakap.ui.bonusStatement;

public class BonusStatementContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(BonusStatementContract.View view);
    }
}
