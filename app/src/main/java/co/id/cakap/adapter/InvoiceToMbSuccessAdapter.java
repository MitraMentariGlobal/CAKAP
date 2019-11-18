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
import co.id.cakap.data.InvoiceToMbSuccessData;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class InvoiceToMbSuccessAdapter extends RecyclerView.Adapter<InvoiceToMbSuccessAdapter.ViewHolder> {
    private List<InvoiceToMbSuccessData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public InvoiceToMbSuccessAdapter(List<InvoiceToMbSuccessData> resultData, Context context){
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
        InvoiceToMbSuccessData invoiceToMbSuccessData = mResultData.get(position);

        holder.context = mContext;
        holder.mItemCode.setText(invoiceToMbSuccessData.getItem_code());
        holder.mItemName.setText(invoiceToMbSuccessData.getItem_name());
        holder.mPrice.setText(invoiceToMbSuccessData.getPrice());
        holder.mTotalPv.setText(invoiceToMbSuccessData.getPv());
        holder.mQty.setText(invoiceToMbSuccessData.getQty());
        holder.mSubTotal.setText(invoiceToMbSuccessData.getSub_total());
        holder.mSubTotalPv.setText(invoiceToMbSuccessData.getSub_total_pv());
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
