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
import co.id.cakap.data.ReqInvoiceToBcSuccessData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ReqInvoiceToBcSuccessAdapter extends RecyclerView.Adapter<ReqInvoiceToBcSuccessAdapter.ViewHolder> {
    private List<ReqInvoiceToBcSuccessData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ReqInvoiceToBcSuccessAdapter(List<ReqInvoiceToBcSuccessData> resultData, Context context){
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
        ReqInvoiceToBcSuccessData reqInvoiceToBcSuccessData = mResultData.get(position);

        holder.context = mContext;
        holder.mItemCode.setText(reqInvoiceToBcSuccessData.getItem_code());
        holder.mItemName.setText(reqInvoiceToBcSuccessData.getItem_name());
        holder.mPrice.setText(reqInvoiceToBcSuccessData.getPrice());
        holder.mTotalPv.setText(reqInvoiceToBcSuccessData.getPv());
        holder.mQty.setText(reqInvoiceToBcSuccessData.getQty());
        holder.mSubTotal.setText(reqInvoiceToBcSuccessData.getSub_total());
        holder.mSubTotalPv.setText(reqInvoiceToBcSuccessData.getSub_total_pv());
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
