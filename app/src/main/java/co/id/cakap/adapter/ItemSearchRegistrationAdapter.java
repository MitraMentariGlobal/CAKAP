package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ItemSearchRegistrationData;
import co.id.cakap.data.RegistrationData;
import co.id.cakap.ui.detailRegistration.DetailRegistrationPresenter;
import co.id.cakap.ui.registration.RegistrationActivityPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ItemSearchRegistrationAdapter extends RecyclerView.Adapter<ItemSearchRegistrationAdapter.ViewHolder> implements Filterable {
    private List<ItemSearchRegistrationData> mResultData;
    private List<ItemSearchRegistrationData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mIdForm = 0;

    public ItemSearchRegistrationAdapter(List<ItemSearchRegistrationData> resultData, Context context, int idFrom){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIdForm = idFrom;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_search_registration_data, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemSearchRegistrationData itemSearchRegistrationData = mFilteredList.get(position);

        holder.context = mContext;
        holder.itemSearchRegistrationData = itemSearchRegistrationData;
        holder.idFrom = mIdForm;

        holder.mMemberId.setText(itemSearchRegistrationData.getMember_id());
        holder.mName.setText(itemSearchRegistrationData.getName());
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
                    ArrayList<ItemSearchRegistrationData> filteredList = new ArrayList<>();
                    for (ItemSearchRegistrationData itemSearchRegistrationData : mResultData) {
                        if (itemSearchRegistrationData.getMember_id().toLowerCase().contains(charString) ||
                                itemSearchRegistrationData.getName().toLowerCase().contains(charString)) {
                            filteredList.add(itemSearchRegistrationData);
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
                mFilteredList = (ArrayList<ItemSearchRegistrationData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_member_id)
        TextView mMemberId;
        @BindView(R.id.txt_name)
        TextView mName;

        Context context;
        ItemSearchRegistrationData itemSearchRegistrationData;
        int idFrom = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new DetailRegistrationPresenter().getView().hideDialogSearchData(itemSearchRegistrationData, idFrom);
        }
    }
}
