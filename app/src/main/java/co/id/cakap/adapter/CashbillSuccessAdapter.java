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
import co.id.cakap.data.CashbillSuccessData;
import co.id.cakap.data.DetailTransaksiData;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class CashbillSuccessAdapter extends RecyclerView.Adapter<CashbillSuccessAdapter.ViewHolder> {
    private List<CashbillSuccessData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CashbillSuccessAdapter(List<CashbillSuccessData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_detail_transaksi, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CashbillSuccessData cashbillSuccessData = mResultData.get(position);

        holder.context = mContext;
        holder.mItemCode.setText(cashbillSuccessData.getItem_code());
        holder.mItemName.setText(cashbillSuccessData.getItem_name());
        holder.mPrice.setText(cashbillSuccessData.getPrice());
        holder.mTotalPv.setText(cashbillSuccessData.getPv());
        holder.mQty.setText(cashbillSuccessData.getQty());
        holder.mSubTotal.setText(cashbillSuccessData.getSub_total());
        holder.mSubTotalPv.setText(cashbillSuccessData.getSub_total_pv());
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
        @BindView(R.id.txt_price)
        TextView mPrice;
        @BindView(R.id.txt_total_pv)
        TextView mTotalPv;
        @BindView(R.id.txt_qty)
        TextView mQty;
        @BindView(R.id.txt_sub_total)
        TextView mSubTotal;
        @BindView(R.id.txt_sub_total_pv)
        TextView mSubTotalPv;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
