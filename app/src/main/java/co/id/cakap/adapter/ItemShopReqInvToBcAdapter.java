package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ItemShopCompanyData;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivityPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ItemShopReqInvToBcAdapter extends RecyclerView.Adapter<ItemShopReqInvToBcAdapter.ViewHolder> implements Filterable {
    private List<ItemShopCompanyData> mResultData;
    private List<ItemShopCompanyData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemShopReqInvToBcAdapter(List<ItemShopCompanyData> resultData, Context context){
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
        ItemShopCompanyData itemShopCompanyData = mFilteredList.get(position);

        holder.context = mContext;
        holder.itemShopCompanyData = itemShopCompanyData;
        holder.position = position;

        holder.mItemCode.setText(itemShopCompanyData.getItem_code());
        holder.mItemName.setText(itemShopCompanyData.getItem_name());
        holder.mPrice.setText(itemShopCompanyData.getPrice());
        holder.mPv.setText(itemShopCompanyData.getPv());
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
                    ArrayList<ItemShopCompanyData> filteredList = new ArrayList<>();
                    for (ItemShopCompanyData itemShopCompanyData : mResultData) {
                        if (itemShopCompanyData.getItem_code().toLowerCase().contains(charString) ||
                                itemShopCompanyData.getItem_name().toLowerCase().contains(charString) ||
                                itemShopCompanyData.getPrice().toLowerCase().contains(charString) ||
                                itemShopCompanyData.getPv().toLowerCase().contains(charString)) {
                            filteredList.add(itemShopCompanyData);
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
                mFilteredList = (ArrayList<ItemShopCompanyData>) filterResults.values;
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
        @BindView(R.id.linear_stock)
        LinearLayout mLinearStock;

        ItemShopCompanyData itemShopCompanyData;
        Context context;
        int qty = 0;
        int position = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mLinearStock.setVisibility(View.GONE);
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
                itemShopCompanyData.setQty(String.valueOf(qty));
                mResultData.set(position, itemShopCompanyData);
                new ReqInvoiceToBcActivityPresenter().getView().setCheckoutValue(mResultData, itemShopCompanyData, 0);
            }
        }

        @OnClick(R.id.img_plus)
        public void plusItem() {
            setZeroQty();
            qty += 1;
            mQty.setText(String.valueOf(qty));
            itemShopCompanyData.setQty(String.valueOf(qty));
            mResultData.set(position, itemShopCompanyData);
            new ReqInvoiceToBcActivityPresenter().getView().setCheckoutValue(mResultData, itemShopCompanyData, 1);
        }
    }
}
