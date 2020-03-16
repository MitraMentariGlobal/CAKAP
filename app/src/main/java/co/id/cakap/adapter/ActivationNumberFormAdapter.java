package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.R;
import co.id.cakap.data.ActivationKitData;
import co.id.cakap.data.ActivationNumberFormData;
import co.id.cakap.data.ActivationSubmitItemFormData;
import co.id.cakap.ui.createActivationForm.CreateActivationFormPresenter;
import co.id.cakap.utils.Logger;

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ActivationNumberFormAdapter extends RecyclerView.Adapter<ActivationNumberFormAdapter.ViewHolder> {
    private List<ActivationNumberFormData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String mQty;

    public ActivationNumberFormAdapter(List<ActivationNumberFormData> resultData, Context context, String qty){
        mResultData = resultData;
        mContext = context;
        mQty = qty;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_activation_number_form, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivationNumberFormData activationNumberFormData = mResultData.get(position);

        holder.context = mContext;
        holder.mTxtItemNumberForm.setText("No. Form " + activationNumberFormData.getNumber_form());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        for (int i = 0; i < activationNumberFormData.getList_form().size(); i++) {
            arrayList.add(activationNumberFormData.getList_form().get(i).getMember_id());
        }

        holder.mSpinnerItemForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    Logger.d("item_id : " + activationNumberFormData.getList_form().get(position - 1).getItem_id());
                    Logger.d("member_id : " + activationNumberFormData.getList_form().get(position - 1).getMember_id());
                    Logger.d("number form : " + activationNumberFormData.getNumber_form());
                    Logger.d("position : " + (position - 1));
                    Logger.d("qty : " + mQty);

                    new CreateActivationFormPresenter().getView().insertOrUpdateItemData(
                            new ActivationSubmitItemFormData(
                                    activationNumberFormData.getList_form().get(position - 1).getItem_id(),
                                    activationNumberFormData.getList_form().get(position - 1).getMember_id(),
                                    String.valueOf(activationNumberFormData.getNumber_form()),
                                    mQty
                            )
                    );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(mContext,
                R.layout.item_spinner, android.R.id.text1, arrayList);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.mSpinnerItemForm.setAdapter(stringArrayAdapter);
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_number_form)
        TextView mTxtItemNumberForm;
        @BindView(R.id.item_form_spinner)
        Spinner mSpinnerItemForm;

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
