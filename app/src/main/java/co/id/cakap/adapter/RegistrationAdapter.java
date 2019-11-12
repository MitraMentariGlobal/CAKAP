package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.R;
import co.id.cakap.data.ActivityCashbillData;
import co.id.cakap.data.RegistrationData;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.registration.RegistrationActivityPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.ViewHolder> implements Filterable {
    private List<RegistrationData> mResultData;
    private List<RegistrationData> mFilteredList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RegistrationAdapter(List<RegistrationData> resultData, Context context){
        mResultData = resultData;
        mFilteredList = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_registration, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RegistrationData registrationData = mFilteredList.get(position);

        holder.context = mContext;
        holder.mVariantOfKit.setText(registrationData.getVariant_of_kit());
        holder.mActivatioinCode.setText(registrationData.getActivation_code());
        holder.mMemberId.setText(registrationData.getMember_id());
        holder.mDate.setText(registrationData.getDate());
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
                    ArrayList<RegistrationData> filteredList = new ArrayList<>();
                    for (RegistrationData activityCashbillData : mResultData) {
                        if (activityCashbillData.getVariant_of_kit().toLowerCase().contains(charString) ||
                                activityCashbillData.getActivation_code().toLowerCase().contains(charString) ||
                                activityCashbillData.getMember_id().toLowerCase().contains(charString)) {
                            filteredList.add(activityCashbillData);
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
                mFilteredList = (ArrayList<RegistrationData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_variant_of_kit)
        TextView mVariantOfKit;
        @BindView(R.id.txt_activation_code)
        TextView mActivatioinCode;
        @BindView(R.id.txt_member_id)
        TextView mMemberId;
        @BindView(R.id.txt_date)
        TextView mDate;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.relative_parent)
        public void openDetail() {
            new RegistrationActivityPresenter().getView().openDetailRegistration();
        }
    }
}
