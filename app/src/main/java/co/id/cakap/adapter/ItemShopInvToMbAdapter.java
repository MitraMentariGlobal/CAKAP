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

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ItemShopData;
import co.id.cakap.helper.Constant;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivityPresenter;
import co.id.cakap.utils.Logger;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ItemShopInvToMbAdapter extends RecyclerView.Adapter<ItemShopInvToMbAdapter.ViewHolder> implements Filterable {
    private List<ItemShopData> mResultData;
    private List<ItemShopData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemShopInvToMbAdapter(List<ItemShopData> resultData, Context context){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
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
        holder.itemShopData = itemShopData;
        holder.position = position;

        holder.mItemCode.setText(itemShopData.getItem_code());
        holder.mStock.setText(itemShopData.getQty());
        holder.mItemName.setText(itemShopData.getItem_name());
        holder.mPrice.setText(itemShopData.getFharga());
        holder.mPv.setText(itemShopData.getPv());

        if (itemShopData.getCart() != null) {
            holder.mQty.setText(itemShopData.getCart());
        } else {
            holder.mQty.setText("0");
        }

        if (!(itemShopData.getImage().equals("0"))) {
            Picasso.with(mContext)
                    .load(Constant.URL_IMAGE_PRODUCT + itemShopData.getImage())
                    .into(holder.mImage);
        } else {
            holder.mImage.setBackground(mContext.getResources().getDrawable(R.drawable.img_item_placeholder));
        }
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
                                itemShopData.getHarga().toLowerCase().contains(charString) ||
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
        @BindView(R.id.txt_stock)
        TextView mStock;
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
        @BindView(R.id.image)
        ImageView mImage;

        ItemShopData itemShopData;
        Context context;
        int position = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.img_minus)
        public void minusItem() {
            int qty = Integer.parseInt(mQty.getText().toString());
            if (qty > 0) {
                qty -= 1;
                itemShopData.setCart(String.valueOf(qty));
                mFilteredList.set(position, itemShopData);
                notifyItemChanged(position);

                new InvoiceToMbActivityPresenter().getView().setCheckoutValue(mFilteredList, itemShopData, 0);
            }
        }

        @OnClick(R.id.img_plus)
        public void plusItem() {
            int qty = Integer.parseInt(mQty.getText().toString());
            if (qty < Integer.parseInt(itemShopData.getQty())) {
                qty += 1;
                itemShopData.setCart(String.valueOf(qty));
                mFilteredList.set(position, itemShopData);
                notifyItemChanged(position);

                new InvoiceToMbActivityPresenter().getView().setCheckoutValue(mFilteredList, itemShopData, 1);
            }
        }
    }
}
