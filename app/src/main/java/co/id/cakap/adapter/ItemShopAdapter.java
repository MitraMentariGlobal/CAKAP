package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import co.id.cakap.data.ItemShopData;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ItemShopAdapter extends RecyclerView.Adapter<ItemShopAdapter.ViewHolder> implements Filterable {
    private List<ItemShopData> mResultData;
    private List<ItemShopData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemShopAdapter(List<ItemShopData> resultData, Context context){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_shop, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemShopData itemShopData = mFilteredList.get(position);

        holder.context = mContext;
        holder.mItemCode.setText(itemShopData.getItem_code());
        holder.mItemName.setText(itemShopData.getItem_name());
        holder.mPrice.setText(itemShopData.getPrice());
        holder.mPv.setText(itemShopData.getPv());
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
                    ArrayList<ItemShopData> filteredList = new ArrayList<>();
                    for (ItemShopData itemShopData : mResultData) {
                        if (itemShopData.getItem_code().toLowerCase().contains(charString) ||
                                itemShopData.getItem_name().toLowerCase().contains(charString) ||
                                itemShopData.getPrice().toLowerCase().contains(charString) ||
                                itemShopData.getPv().toLowerCase().contains(charString)) {
                            filteredList.add(itemShopData);
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
                mFilteredList = (ArrayList<ItemShopData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_code)
        TextView mItemCode;
        @BindView(R.id.txt_item_name)
        TextView mItemName;
        @BindView(R.id.txt_price)
        TextView mPrice;
        @BindView(R.id.txt_pv)
        TextView mPv;
        @BindView(R.id.img_minus)
        ImageView mMinus;
        @BindView(R.id.et_qty)
        EditText mQty;
        @BindView(R.id.img_plus)
        ImageView mPlus;

        Context context;
        int qty = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            setZeroQty();
        }

        public void setZeroQty() {
            if (mQty.getText().toString().length() == 0) {
                mQty.setText("0");
            }
            qty = Integer.parseInt(mQty.getText().toString());
        }

        @OnClick(R.id.img_minus)
        public void minusItem() {
            setZeroQty();
            if (qty > 0) {
                qty -= 1;
                mQty.setText(String.valueOf(qty));
            }
        }

        @OnClick(R.id.img_plus)
        public void plusItem() {
            setZeroQty();
            qty += 1;
            mQty.setText(String.valueOf(qty));
        }
    }
}