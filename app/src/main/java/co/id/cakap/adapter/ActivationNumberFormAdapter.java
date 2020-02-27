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

/**
 * Created by Laksamana Guntur Dzulfikar
 * Android Developer
 */

public class ActivationNumberFormAdapter extends RecyclerView.Adapter<ActivationNumberFormAdapter.ViewHolder> {
    private List<ActivationNumberFormData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ActivationNumberFormAdapter(List<ActivationNumberFormData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
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
        arrayList.add("12345678");
        arrayList.add("87654321");
        arrayList.add("09876543");
        arrayList.add("34567890");
        arrayList.add("11111111");
//        for (int i = 0; i < activationNumberFormData.getList_form().size(); i++) {
//            arrayList.add(activationNumberFormData.getList_form().get(i).getItem_form());
//        }

        holder.mSpinnerItemForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

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
