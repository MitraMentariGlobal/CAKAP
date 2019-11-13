package co.id.cakap.ui.memberDetail;

public class MemberDetailActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(MemberDetailActivityContract.View view);
    }
}
