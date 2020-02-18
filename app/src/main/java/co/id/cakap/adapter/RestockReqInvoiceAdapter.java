package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.RestockInvoiceData;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoicePresenter;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class RestockReqInvoiceAdapter extends RecyclerView.Adapter<RestockReqInvoiceAdapter.ViewHolder> {
    private List<RestockReqInvoiceData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RestockReqInvoiceAdapter(List<RestockReqInvoiceData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_restock, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RestockReqInvoiceData restockReqInvoiceData = mResultData.get(position);

        holder.context = mContext;
        holder.restockReqInvoiceData = restockReqInvoiceData;
        holder.mTotalPv.setText(restockReqInvoiceData.getTotal_pv());
        holder.mTransactionId.setText(restockReqInvoiceData.getItem_id());
        holder.mTransactionId.setVisibility(View.GONE);
        holder.mDate.setText(restockReqInvoiceData.getDate());
        holder.mStatus.setText(restockReqInvoiceData.getStatus());
        holder.mTotalAmount.setText("IDR " + restockReqInvoiceData.getTotal_amount());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_total_pv)
        TextView mTotalPv;
        @BindView(R.id.txt_transaction_id)
        TextView mTransactionId;
        @BindView(R.id.txt_date)
        TextView mDate;
        @BindView(R.id.txt_status)
        TextView mStatus;
        @BindView(R.id.txt_total_amount)
        TextView mTotalAmount;
        @BindView(R.id.linear_action)
        LinearLayout mLinearAction;

        Context context;
        RestockReqInvoiceData restockReqInvoiceData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mLinearAction.setVisibility(View.GONE);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new RestockReqInvoicePresenter().getView().openDetailTransaction(restockReqInvoiceData);
        }
    }
}
