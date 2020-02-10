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
import co.id.cakap.data.ItemStockCard;
import co.id.cakap.data.StockCardData;
import co.id.cakap.ui.detailRegistration.DetailRegistrationPresenter;
import co.id.cakap.ui.stockReport.stockCard.StockCardPresenter;
import co.id.cakap.utils.Utils;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ItemSearchStockCardAdapter extends RecyclerView.Adapter<ItemSearchStockCardAdapter.ViewHolder> implements Filterable {
    private List<ItemStockCard> mResultData;
    private List<ItemStockCard> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemSearchStockCardAdapter(List<ItemStockCard> resultData, Context context) {
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
        ItemStockCard itemStockCard = mFilteredList.get(position);

        holder.context = mContext;
        holder.itemStockCard = itemStockCard;

        holder.mItemCode.setText(itemStockCard.getItem_code());
        holder.mItemName.setText(itemStockCard.getItem_name());
        holder.mAmount.setText(Utils.priceFromString(itemStockCard.getAmount()));
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
                    ArrayList<ItemStockCard> filteredList = new ArrayList<>();
                    for (ItemStockCard itemStockCard : mResultData) {
                        if (itemStockCard.getItem_code().toLowerCase().contains(charString) ||
                                itemStockCard.getItem_name().toLowerCase().contains(charString) ||
                                itemStockCard.getAmount().toLowerCase().contains(charString)) {
                            filteredList.add(itemStockCard);
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
                mFilteredList = (ArrayList<ItemStockCard>) filterResults.values;
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
        ItemStockCard itemStockCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new StockCardPresenter().getView().hideDialogSearchData(itemStockCard);
        }
    }
}
