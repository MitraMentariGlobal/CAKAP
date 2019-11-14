package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.AddressData;
import co.id.cakap.data.DetailTransaksiData;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private RecyclerView mRecyclerView = null;
    private List<AddressData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mPositionCheck = 0;

    public AddressAdapter(RecyclerView recyclerView, List<AddressData> resultData, Context context) {
        mRecyclerView = recyclerView;
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_address, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddressData addressData = mResultData.get(position);

        holder.context = mContext;
        holder.mTxtCity.setText(addressData.getKota());
        holder.mTxtProvince.setText(addressData.getProvince());
        holder.mTxtAddress.setText(addressData.getAddress());
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_city)
        TextView mTxtCity;
        @BindView(R.id.txt_province)
        TextView mTxtProvince;
        @BindView(R.id.txt_address)
        TextView mTxtAddress;

        CircleImageView mItemCheck;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_check)
        public void userCheck(View view) {
            View recyclerView = null;

            if (getAdapterPosition() == mPositionCheck) {
                recyclerView = mRecyclerView.findViewHolderForAdapterPosition(mPositionCheck).itemView;
                mItemCheck = (CircleImageView) recyclerView.findViewById(R.id.item_check);
                mItemCheck.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_radio_on_button));
                mPositionCheck = getAdapterPosition();
            } else {
                recyclerView = mRecyclerView.findViewHolderForAdapterPosition(mPositionCheck).itemView;
                mItemCheck = (CircleImageView) recyclerView.findViewById(R.id.item_check);
                mItemCheck.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_radio_off_button));

                recyclerView = mRecyclerView.findViewHolderForAdapterPosition(getAdapterPosition()).itemView;
                mItemCheck = (CircleImageView) recyclerView.findViewById(R.id.item_check);
                mItemCheck.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_radio_on_button));
                mPositionCheck = getAdapterPosition();
            }
        }
    }
}
