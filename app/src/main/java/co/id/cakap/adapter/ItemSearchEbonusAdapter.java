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
import co.id.cakap.data.ItemEbonusCard;
import co.id.cakap.data.ItemStockCard;
import co.id.cakap.ui.ebonus.EbonusPresenter;
import co.id.cakap.ui.stockReport.stockCard.StockCardPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ItemSearchEbonusAdapter extends RecyclerView.Adapter<ItemSearchEbonusAdapter.ViewHolder> implements Filterable {
    private List<ItemEbonusCard> mResultData;
    private List<ItemEbonusCard> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemSearchEbonusAdapter(List<ItemEbonusCard> resultData, Context context) {
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_search_stock_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemEbonusCard itemEbonusCard = mFilteredList.get(position);

        holder.context = mContext;
        holder.itemEbonusCard = itemEbonusCard;

        holder.mItemCode.setText(itemEbonusCard.getItem_code());
        holder.mItemName.setText(itemEbonusCard.getItem_name());
        holder.mAmount.setText(itemEbonusCard.getAmount());
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
                    ArrayList<ItemEbonusCard> filteredList = new ArrayList<>();
                    for (ItemEbonusCard itemEbonusCard : mResultData) {
                        if (itemEbonusCard.getItem_code().toLowerCase().contains(charString) ||
                                itemEbonusCard.getItem_name().toLowerCase().contains(charString) ||
                                itemEbonusCard.getAmount().toLowerCase().contains(charString)) {
                            filteredList.add(itemEbonusCard);
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
                mFilteredList = (ArrayList<ItemEbonusCard>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_code)
        TextView mItemCode;
        @BindView(R.id.txt_item_name)
        TextView mItemName;
        @BindView(R.id.txt_amount)
        TextView mAmount;

        Context context;
        ItemEbonusCard itemEbonusCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new EbonusPresenter().getView().hideDialogSearchData(itemEbonusCard);
        }
    }
}
