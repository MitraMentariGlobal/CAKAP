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
import co.id.cakap.ui.pickUpDelivery.PickUpDeliveryActivityPresenter;
import co.id.cakap.utils.Logger;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private static RecyclerView mRecyclerView = null;
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
        holder.addressData = addressData;

        holder.mTxtCity.setText(addressData.getKota());
        holder.mTxtProvince.setText(addressData.getProvince());
        holder.mTxtAddress.setText(addressData.getAddress());

        if (position == 0) {
            holder.mLinearChangeAddress.setVisibility(View.GONE);
            holder.mRelativeDefaultAddress.setVisibility(View.VISIBLE);
        }

        if (addressData.getIsCheck()) {
            holder.mItemCheck.setBackground(mContext.getResources().getDrawable(R.drawable.ic_radio_on_button));
        }
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
        @BindView(R.id.linear_change_address)
        LinearLayout mLinearChangeAddress;
        @BindView(R.id.relative_default_address)
        RelativeLayout mRelativeDefaultAddress;
        @BindView(R.id.item_check)
        CircleImageView mItemCheck;

        AddressData addressData;
        CircleImageView itemCheck;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.txt_change_address)
        public void changeAddress(View view) {
            new PickUpDeliveryActivityPresenter().getView().changeAddress(addressData.getProvince(), addressData.getKota(), addressData.getAddress());
        }

        @OnClick(R.id.item_check)
        public void userCheck(View view) {
            View recyclerView = null;

            if (getAdapterPosition() == mPositionCheck) {
                recyclerView = mRecyclerView.findViewHolderForAdapterPosition(mPositionCheck).itemView;
                mItemCheck = (CircleImageView) recyclerView.findViewById(R.id.item_check);
                mItemCheck.setBackground(context.getResources().getDrawable(R.drawable.ic_radio_on_button));
                mPositionCheck = getAdapterPosition();
            } else {
                recyclerView = mRecyclerView.findViewHolderForAdapterPosition(mPositionCheck).itemView;
                mItemCheck = (CircleImageView) recyclerView.findViewById(R.id.item_check);
                mItemCheck.setBackground(context.getResources().getDrawable(R.drawable.ic_radio_off_button));

                recyclerView = mRecyclerView.findViewHolderForAdapterPosition(getAdapterPosition()).itemView;
                mItemCheck = (CircleImageView) recyclerView.findViewById(R.id.item_check);
                mItemCheck.setBackground(context.getResources().getDrawable(R.drawable.ic_radio_on_button));
                mPositionCheck = getAdapterPosition();
            }
        }
    }
}
