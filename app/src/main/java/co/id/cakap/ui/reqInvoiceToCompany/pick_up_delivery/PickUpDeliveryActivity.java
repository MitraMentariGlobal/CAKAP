package co.id.cakap.ui.reqInvoiceToCompany.pick_up_delivery;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.AddressAdapter;
import co.id.cakap.data.AddressData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.myProfile.MyProfileActivityContract;
import co.id.cakap.ui.myProfile.MyProfileActivityPresenter;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class PickUpDeliveryActivity extends AppCompatActivity implements PickUpDeliveryActivityContract.View, AdapterView.OnItemSelectedListener {
    @Inject
    PickUpDeliveryActivityPresenter mPickUpDeliveryActivityPresenter;

    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.pick_up_delivery_spinner)
    Spinner mPickUpDeliverySpinner;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.fab_next)
    FloatingActionButton mFabNext;

    private AddressAdapter mListAdapter;
    private PickUpDeliveryActivityContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_delivery);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mPickUpDeliveryActivityPresenter;
        mPickUpDeliveryActivityPresenter.setView(this);
//        mRecyclerView.setNestedScrollingEnabled(false);
        mUserActionListener.getData();

        mFab.hide();
        mTitle.setText(getString(R.string.req_invoice_to_com).toUpperCase());
        initSpinner();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<AddressData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new AddressAdapter(mRecyclerView, resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy<0 && !mFabNext.isShown()) {
                    mFabNext.show();
                } else if(dy>0 && mFabNext.isShown()) {
                    mFabNext.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        hideProgressBar();
    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    public void initSpinner() {
        mPickUpDeliverySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.pick_up_delivery, R.layout.item_spinner);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPickUpDeliverySpinner.setAdapter(monthAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
