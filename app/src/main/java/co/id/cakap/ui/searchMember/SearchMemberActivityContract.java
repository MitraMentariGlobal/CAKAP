package co.id.cakap.ui.searchMember;

import java.util.List;

import co.id.cakap.data.SearchMemberData;

public class SearchMemberActivityContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void setErrorResponse(String message);
        void setAdapter(List<SearchMemberData> resultData);
        void openDetailDialog(SearchMemberData searchMemberData);
    }

    public interface UserActionListener{
        void setView(SearchMemberActivityContract.View view);
        void getData();
    }
}
