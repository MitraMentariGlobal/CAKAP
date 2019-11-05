package co.id.cakap.di.module;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.id.cakap.CoreApp;
import co.id.cakap.data.DaoMaster;
import co.id.cakap.data.DaoSession;
import co.id.cakap.di.scope.ActivityScope;
import co.id.cakap.helper.Constant;
import co.id.cakap.model.DataModel;
import co.id.cakap.network.NetworkService;
import co.id.cakap.repository.MainRepository;
import co.id.cakap.ui.dashboard.account.AccountFragment;
import co.id.cakap.ui.dashboard.account.AccountPresenter;
import co.id.cakap.ui.dashboard.activity.ActivityFragment;
import co.id.cakap.ui.dashboard.activity.ActivityPresenter;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillFragment;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillPresenter;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbFragment;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbPresenter;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbFragment;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbPresenter;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbFragment;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbPresenter;
import co.id.cakap.ui.dashboard.home.HomeFragment;
import co.id.cakap.ui.dashboard.home.HomePresenter;
import co.id.cakap.ui.dashboard.inbox.InboxFragment;
import co.id.cakap.ui.dashboard.inbox.InboxPresenter;
import co.id.cakap.ui.dashboard.restock.RestockFragment;
import co.id.cakap.ui.dashboard.restock.RestockPresenter;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoicePresenter;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockFragment;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockPresenter;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoicePresenter;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.ui.homeWebView.HomeWebViewPresenter;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.login.LoginPresenter;
import co.id.cakap.ui.splash_screen.SplashScreenActivity;
import co.id.cakap.ui.splash_screen.SplashScreenPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Module
public class MainActivityModule {
    private SplashScreenActivity splashScreenActivity;
    private LoginActivity loginActivity;
    private HomeWebViewActivity homeWebViewActivity;
    private HomeFragment homeFragment;
    private ActivityFragment activityFragment;
    private InboxFragment inboxFragment;
    private RestockFragment restockFragment;
    private AccountFragment accountFragment;
    private ActivityCashbillFragment activityCashbillFragment;
    private ActivityInvToMbFragment activityInvToMbFragment;
    private ActivityRekapBnsBcmbFragment activityRekapBnsBcmbFragment;
    private ActivityReqInvMbFragment activityReqInvMbFragment;
    private RestockInvoiceFragment restockInvoiceFragment;
    private RestockReceiveStockFragment restockReceiveStockFragment;
    private RestockReqInvoiceFragment restockReqInvoiceFragment;

    public MainActivityModule(SplashScreenActivity splashScreenActivity) {
        this.splashScreenActivity = splashScreenActivity;
    }

    public MainActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public MainActivityModule(HomeWebViewActivity homeWebViewActivity) {
        this.homeWebViewActivity = homeWebViewActivity;
    }

    public MainActivityModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public MainActivityModule(ActivityFragment activityFragment) {
        this.activityFragment = activityFragment;
    }

    public MainActivityModule(RestockFragment restockFragment) {
        this.restockFragment = restockFragment;
    }

    public MainActivityModule(InboxFragment inboxFragment) {
        this.inboxFragment = inboxFragment;
    }

    public MainActivityModule(AccountFragment accountFragment) {
        this.accountFragment = accountFragment;
    }

    public MainActivityModule(ActivityCashbillFragment activityCashbillFragment) {
        this.activityCashbillFragment = activityCashbillFragment;
    }

    public MainActivityModule(ActivityInvToMbFragment activityInvToMbFragment) {
        this.activityInvToMbFragment = activityInvToMbFragment;
    }

    public MainActivityModule(ActivityRekapBnsBcmbFragment activityRekapBnsBcmbFragment) {
        this.activityRekapBnsBcmbFragment = activityRekapBnsBcmbFragment;
    }

    public MainActivityModule(ActivityReqInvMbFragment activityReqInvMbFragment) {
        this.activityReqInvMbFragment = activityReqInvMbFragment;
    }

    public MainActivityModule(RestockInvoiceFragment restockInvoiceFragment) {
        this.restockInvoiceFragment = restockInvoiceFragment;
    }

    public MainActivityModule(RestockReceiveStockFragment restockReceiveStockFragment) {
        this.restockReceiveStockFragment = restockReceiveStockFragment;
    }

    public MainActivityModule(RestockReqInvoiceFragment restockReqInvoiceFragment) {
        this.restockReqInvoiceFragment = restockReqInvoiceFragment;
    }

    @Provides
    @ActivityScope
    SplashScreenActivity provideSplashScreenActivity() {
        return splashScreenActivity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    HomeWebViewActivity provideHomeActivity() {
        return homeWebViewActivity;
    }

    @Provides
    @ActivityScope
    MainRepository provideMainRepository(NetworkService networkService) {
        return new MainRepository(networkService);
    }

    @Provides
    @ActivityScope
    DaoSession provideDaoSession() {
        String DbName = Constant.DATABASE_NAME;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(CoreApp.get(), DbName);
        Log.d("New DB Name: ", DbName);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        Log.d("DB PATH", db.getPath());
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


    @Provides
    @ActivityScope
    DataModel provideDataModel(DaoSession daoSession){
        return new DataModel(daoSession);
    }

    @Provides
    @ActivityScope
    LoginPresenter provideLoginPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new LoginPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    SplashScreenPresenter provideSplashScreenPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new SplashScreenPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    HomeWebViewPresenter provideHomeScreenPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new HomeWebViewPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    HomePresenter provideHomePresenter(MainRepository mainRepository, DataModel dataModel) {
        return new HomePresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ActivityPresenter provideActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RestockPresenter provideRestockPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RestockPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    InboxPresenter provideInboxPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new InboxPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    AccountPresenter provideAccountPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new AccountPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ActivityCashbillPresenter provideActivityCashbillPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ActivityCashbillPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ActivityInvToMbPresenter provideActivityInvToMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ActivityInvToMbPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ActivityRekapBnsBcmbPresenter provideActivityRekapBnsBcmbPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ActivityRekapBnsBcmbPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ActivityReqInvMbPresenter provideActivityReqInvMbPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ActivityReqInvMbPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RestockReqInvoicePresenter provideRestockReqInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RestockReqInvoicePresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RestockReceiveStockPresenter provideRestockReceiveStockPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RestockReceiveStockPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RestockInvoicePresenter provideRestockInvoicePresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RestockInvoicePresenter(mainRepository, dataModel);
    }
}
