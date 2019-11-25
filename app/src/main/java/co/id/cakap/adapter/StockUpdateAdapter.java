package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.R;
import co.id.cakap.data.OmsetData;
import co.id.cakap.data.StockUpdateData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class StockUpdateAdapter extends RecyclerView.Adapter<StockUpdateAdapter.ViewHolder> {
    private List<StockUpdateData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public StockUpdateAdapter(List<StockUpdateData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_stock_update, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StockUpdateData stockUpdateData = mResultData.get(position);

        holder.context = mContext;
        holder.mItemCode.setText(stockUpdateData.getItem_code());
        holder.mItemName.setText(stockUpdateData.getProduct_name());
        holder.mQty.setText(stockUpdateData.getQty());
        holder.mAmount.setText(stockUpdateData.getAmount());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_code)
        TextView mItemCode;
        @BindView(R.id.txt_item_name)
        TextView mItemName;
        @BindView(R.id.txt_qty)
        TextView mQty;
        @BindView(R.id.txt_amount)
        TextView mAmount;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
