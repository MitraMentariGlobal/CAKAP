package co.id.cakap.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.RestockReceiveStockData;
import co.id.cakap.data.RestockReqInvoiceData;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockPresenter;
import co.id.cakap.utils.dialog.UserConfirmationDialog;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class RestockReceiveStockAdapter extends RecyclerView.Adapter<RestockReceiveStockAdapter.ViewHolder> {
    private List<RestockReceiveStockData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RestockReceiveStockAdapter(List<RestockReceiveStockData> resultData, Context context){
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
        RestockReceiveStockData restockReceiveStockData = mResultData.get(position);

        holder.context = mContext;
        holder.restockReceiveStockData = restockReceiveStockData;
        holder.mTotalPv.setText(restockReceiveStockData.getTotal_pv());
        holder.mTransactionId.setText(restockReceiveStockData.getTransaction_id());
        holder.mDate.setText(restockReceiveStockData.getDate());
        holder.mTotalAmount.setText("IDR " + restockReceiveStockData.getTotal_amount());
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
        @BindView(R.id.item_verified)
        ImageView mItemVerified;

        Context context;
        RestockReceiveStockData restockReceiveStockData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mStatus.setVisibility(View.GONE);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new RestockReceiveStockPresenter().getView().openDetailTransaction(restockReceiveStockData);
        }

        @OnClick(R.id.item_verified)
        public void actionApprove() {
            UserConfirmationDialog utils = new UserConfirmationDialog();
            Dialog dialog = utils.showDialog(context);
            utils.setTitleDialog("Approve");
            utils.setPositiveAction();

            dialog.findViewById(R.id.no_act_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.findViewById(R.id.yes_act_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    new RestockReceiveStockPresenter().getView().openPinDialog(restockReceiveStockData);
                }
            });
        }
    }
}
