package co.id.cakap.ui.searchMember;

public class SearchMemberActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
    }

    public interface UserActionListener{
        void setView(SearchMemberActivityContract.View view);
    }
}
