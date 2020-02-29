package co.id.cakap.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.BankInfoData;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;
import co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess.ReqInvoiceToCompanySuccessActivity;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class BankInfoInvoiceCompanyAdapter extends RecyclerView.Adapter<BankInfoInvoiceCompanyAdapter.ViewHolder> {
    private List<BankInfoData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public BankInfoInvoiceCompanyAdapter(List<BankInfoData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_info_bank2, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BankInfoData bankInfoData = mResultData.get(position);

        holder.context = mContext;
        holder.bankInfoData = bankInfoData;
        holder.mTxtTransferVia.setText("Transfer via BCA " + (position + 1));
        holder.mTxtNomorRekening.setText(bankInfoData.getAccountno());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_transfer_via)
        TextView mTxtTransferVia;
        @BindView(R.id.txt_nomor_rekening)
        TextView mTxtNomorRekening;
        @BindView(R.id.txt_copy_rekening)
        TextView mTxtCopyRekening;

        Context context;
        BankInfoData bankInfoData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.txt_copy_rekening)
        public void copyRekening() {
            String noRek = bankInfoData.getAccountno();
            if (noRek.contains(",")) {
                noRek = noRek.replace(",", "");
            } else if (noRek.contains(".")) {
                noRek = noRek.replace(".", "");
            }

            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", noRek);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
        }
    }
}
