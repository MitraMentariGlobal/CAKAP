package co.id.cakap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.id.cakap.R;
import co.id.cakap.data.ResultData;
import co.id.cakap.helper.Constant;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ResultData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(List<ResultData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultData resultData = mResultData.get(position);

        System.err.println(Constant.URL_IMAGE + resultData.getPosterPath());
        Picasso.with(mContext)
                .load(Constant.URL_IMAGE + resultData.getPosterPath())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_thumbnail);
        }
    }
}
