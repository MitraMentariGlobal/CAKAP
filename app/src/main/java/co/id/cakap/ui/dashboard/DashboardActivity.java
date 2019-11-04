package co.id.cakap.ui.dashboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.cakap.R;
import co.id.cakap.ui.dashboard.account.AccountFragment;
import co.id.cakap.ui.dashboard.favorite.FavoriteFragment;
import co.id.cakap.ui.dashboard.home.HomeFragment;
import co.id.cakap.ui.dashboard.search.SearchFragment;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

//    @BindView(R.id.title)
//    TextView mTitle;
//    @BindView(R.id.date)
//    TextView mDate;
//    @BindView(R.id.time)
//    TextView mTime;
    @BindView(R.id.bn_main)
    BottomNavigationView mBottomNavigationView;

//    Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        initializeData();
    }

    public void initializeData() {
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/museo_sans.otf");
        Typeface typeface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            typeface = getResources().getFont(R.font.museo_sans);
//            mTitle.setTypeface(typeface);
        }

        loadFragment(new HomeFragment());
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

//    public void setDateTime() {
//        Calendar calendar = Calendar.getInstance();
//        Date date = calendar.getTime();
//        String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
//        String dateMonthYear = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
//        String dayDate = day + ", " + dateMonthYear;
//        mDate.setText(dayDate);
//
//        thread = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                                mTime.setText(time);
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//
//                }
//            }
//        };
//
//        thread.start();
//    }

    @Override
    public void onStop() {
//        thread.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        thread.stop();
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.search_menu:
                fragment = new SearchFragment();
                break;
            case R.id.favorite_menu:
                fragment = new FavoriteFragment();
                break;
            case R.id.account_menu:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
