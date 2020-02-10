package co.id.cakap.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import co.id.cakap.R;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockFragment;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceFragment;

public class SectionsRestockMBPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.invoice,
            R.string.req_invoice
    };
    private final Context mContext;

    public SectionsRestockMBPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new RestockInvoiceFragment();
                break;
            case 1:
                fragment = new RestockReqInvoiceFragment();
                break;
        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}