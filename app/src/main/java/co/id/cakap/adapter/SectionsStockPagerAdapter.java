package co.id.cakap.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import co.id.cakap.R;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillFragment;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbFragment;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbFragment;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbFragment;
import co.id.cakap.ui.stockReport.stockCard.StockCardFragment;
import co.id.cakap.ui.stockReport.stockUpdate.StockUpdateFragment;

public class SectionsStockPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{
            R.string.stock_update,
            R.string.stock_card
    };
    private final Context mContext;

    public SectionsStockPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new StockUpdateFragment();
                break;
            case 1:
                fragment = new StockCardFragment();
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