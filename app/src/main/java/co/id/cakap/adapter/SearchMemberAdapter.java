package co.id.cakap.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.data.SearchMemberData;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.searchMember.SearchMemberActivityPresenter;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class SearchMemberAdapter extends RecyclerView.Adapter<SearchMemberAdapter.ViewHolder> implements Filterable {
    private List<SearchMemberData> mResultData;
    private List<SearchMemberData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public SearchMemberAdapter(List<SearchMemberData> resultData, Context context){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_search_member, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchMemberData searchMemberData = mFilteredList.get(position);

        holder.context = mContext;
        holder.searchMemberData = searchMemberData;

        holder.mMemberId.setText(searchMemberData.getMember_id());
        holder.mName.setText(searchMemberData.getNama_member());
        holder.mCity.setText(searchMemberData.getKota());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = mResultData;
                } else {
                    ArrayList<SearchMemberData> filteredList = new ArrayList<>();
                    for (SearchMemberData searchMemberData : mResultData) {
                        if (searchMemberData.getMember_id().toLowerCase().contains(charString) ||
                                searchMemberData.getNama_member().toLowerCase().contains(charString) ||
                                searchMemberData.getKota().toLowerCase().contains(charString)) {
                            filteredList.add(searchMemberData);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<SearchMemberData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_member_id)
        TextView mMemberId;
        @BindView(R.id.txt_member_name)
        TextView mName;
        @BindView(R.id.txt_city)
        TextView mCity;

        Context context;
        SearchMemberData searchMemberData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new SearchMemberActivityPresenter().getView().openDetailDialog(searchMemberData);
        }
    }
}
