package co.id.cakap.ui.searchMember;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.cakap.CoreApp;
import co.id.cakap.R;
import co.id.cakap.adapter.SearchMemberAdapter;
import co.id.cakap.data.SearchMemberData;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.cashbill.CashbillActivityContract;
import co.id.cakap.ui.cashbill.CashbillActivityPresenter;
import co.id.cakap.utils.Logger;
import co.id.cakap.utils.dialog.DetailSearchMemberDialog;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class SearchMemberActivity extends AppCompatActivity implements SearchMemberActivityContract.View{
    @Inject
    SearchMemberActivityPresenter mSearchMemberActivityPresenter;

    @BindView(R.id.relative_progress_bar)
    RelativeLayout mRelativeProgressBar;
    @BindView(R.id.title_toolbar)
    TextView mTitle;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_search)
    EditText mSearchEditText;
    @BindView(R.id.et_member_id)
    EditText mEtMemberId;
    @BindView(R.id.relative_member_id)
    RelativeLayout mRelativeMemberId;
    @BindView(R.id.txt_error_member_id)
    TextView mTxtErrorMemberId;
    @BindView(R.id.linear_search)
    LinearLayout mLinearSearch;

    private SearchMemberAdapter mListAdapter;
    private SearchMemberActivityContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_member);
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
        mUserActionListener = mSearchMemberActivityPresenter;
        mSearchMemberActivityPresenter.setView(this);
        hideProgressBar();

        mTitle.setText(getString(R.string.search_member).toUpperCase());
        mLinearSearch.setVisibility(View.GONE);
    }

    @OnClick(R.id.linear_search_member_id)
    public void searchMemberId(View view) {
        if (mEtMemberId.getText().length() < 3) {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_red_background_style));
            mTxtErrorMemberId.setVisibility(View.VISIBLE);
        } else {
            mRelativeMemberId.setBackgroundDrawable(getResources().getDrawable(R.drawable.et_gray_background_style));
            mTxtErrorMemberId.setVisibility(View.GONE);
            mUserActionListener.getDataMember(mEtMemberId.getText().toString());
            mEtMemberId.setInputType(0);
        }
    }

    @Override
    public void showProgressBar() {
        mRelativeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mRelativeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdapter(List<SearchMemberData> resultData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mListAdapter = new SearchMemberAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);

        setupOnFocusListener(mSearchEditText);
        hideProgressBar();
    }

    @Override
    public void openDetailDialog(SearchMemberData searchMemberData) {
        DetailSearchMemberDialog utils = new DetailSearchMemberDialog();
        Dialog dialog = utils.showDialog(this);

        TextView txtMemberId = dialog.findViewById(R.id.txt_member_id);
        TextView txtMemberName = dialog.findViewById(R.id.txt_member_name);
        TextView txtKtp = dialog.findViewById(R.id.txt_ktp);
        TextView txtCity = dialog.findViewById(R.id.txt_city);
        TextView txtStatus = dialog.findViewById(R.id.txt_status);
        TextView txtRecId = dialog.findViewById(R.id.txt_rec_id);
        TextView txtRecName = dialog.findViewById(R.id.txt_rec_name);
        TextView txtSponsorId = dialog.findViewById(R.id.txt_sponsor_id);
        TextView txtSponsorName = dialog.findViewById(R.id.txt_sponsor_name);

        txtMemberId.setText(searchMemberData.getMember_id());
        txtMemberName.setText(searchMemberData.getNama_member());
        txtKtp.setText(searchMemberData.getKtp());
        txtCity.setText(searchMemberData.getKota());
        txtRecId.setText(searchMemberData.getRecruiting_id());
        txtRecName.setText(searchMemberData.getRecruiting_name());
        txtSponsorId.setText(searchMemberData.getSponsor_id());
        txtSponsorName.setText(searchMemberData.getSponsor_name());

        if (searchMemberData.getStatus().equals("INACTIVE")) {
            txtStatus.setTextColor(getResources().getColor(R.color.red));
        }

        txtStatus.setText(searchMemberData.getStatus());

    }

    @OnClick(R.id.arrow_back)
    public void arrowBack(View view) {
        super.onBackPressed();
    }

    private void setupOnFocusListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                Logger.d("arg0 : " + arg0);
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                if (mListAdapter != null) mListAdapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }
}
