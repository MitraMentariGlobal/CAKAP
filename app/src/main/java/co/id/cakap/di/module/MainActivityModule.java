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
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessActivity;
import co.id.cakap.ui.cashbill.cashbillSuccess.CashbillSuccessPresenter;
import co.id.cakap.ui.detailRegistration.DetailRegistrationActivity;
import co.id.cakap.ui.detailRegistration.DetailRegistrationPresenter;
import co.id.cakap.ui.feeBCMB.FeeBcmbActivity;
import co.id.cakap.ui.feeBCMB.FeeBcmbActivityPresenter;
import co.id.cakap.ui.invoiceToMb.invoiceToMbSuccess.InvoiceToMbSuccessActivity;
import co.id.cakap.ui.invoiceToMb.invoiceToMbSuccess.InvoiceToMbSuccessPresenter;
import co.id.cakap.ui.myProfile.MyProfileActivity;
import co.id.cakap.ui.myProfile.MyProfileActivityPresenter;
import co.id.cakap.ui.omset.OmsetActivity;
import co.id.cakap.ui.omset.OmsetActivityPresenter;
import co.id.cakap.ui.registration.registrationSuccess.RegistrationSuccessActivity;
import co.id.cakap.ui.registration.registrationSuccess.RegistrationSuccessPresenter;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivity;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivityPresenter;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessActivity;
import co.id.cakap.ui.reqInvoiceToBc.reqInvoiceToBcSuccess.ReqInvoiceToBcSuccessPresenter;
import co.id.cakap.ui.reqInvoiceToCompany.ReqInvoiceToCompanyActivity;
import co.id.cakap.ui.reqInvoiceToCompany.ReqInvoiceToCompanyActivityPresenter;
import co.id.cakap.ui.cashbill.CashbillActivity;
import co.id.cakap.ui.cashbill.CashbillActivityPresenter;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.dashboard.DashboardPresenter;
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
import co.id.cakap.ui.dashboard.notification.NotificationFragment;
import co.id.cakap.ui.dashboard.notification.NotificationPresenter;
import co.id.cakap.ui.dashboard.restock.RestockFragment;
import co.id.cakap.ui.dashboard.restock.RestockPresenter;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoicePresenter;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockFragment;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockPresenter;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoicePresenter;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.ui.detailTransaction.DetailTransactionPresenter;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.ui.homeWebView.HomeWebViewPresenter;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivity;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivityPresenter;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.login.LoginPresenter;
import co.id.cakap.ui.registration.RegistrationActivity;
import co.id.cakap.ui.registration.RegistrationActivityPresenter;
import co.id.cakap.ui.pickUpDelivery.PickUpDeliveryActivity;
import co.id.cakap.ui.pickUpDelivery.PickUpDeliveryActivityPresenter;
import co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess.ReqInvoiceToCompanySuccessActivity;
import co.id.cakap.ui.reqInvoiceToCompany.reqInvoiceToCompanySuccess.ReqInvoiceToCompanySuccessPresenter;
import co.id.cakap.ui.searchMember.SearchMemberActivity;
import co.id.cakap.ui.searchMember.SearchMemberActivityPresenter;
import co.id.cakap.ui.splashScreen.SplashScreenActivity;
import co.id.cakap.ui.splashScreen.SplashScreenPresenter;
import co.id.cakap.ui.stockReport.StockReportActivity;
import co.id.cakap.ui.stockReport.StockReportActivityPresenter;
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
    private DashboardActivity dashboardActivity;
    private DetailTransactionActivity detailTransactionActivity;
    private CashbillActivity cashbillActivity;
    private InvoiceToMbActivity invoiceToMbActivity;
    private RegistrationActivity registrationActivity;
    private ReqInvoiceToBcActivity reqInvoiceToBcActivity;
    private ReqInvoiceToCompanyActivity reqInvoiceToCompanyActivity;
    private FeeBcmbActivity feeBcmbActivity;
    private MyProfileActivity myProfileActivity;
    private OmsetActivity omsetActivity;
    private SearchMemberActivity searchMemberActivity;
    private StockReportActivity stockReportActivity;
    private PickUpDeliveryActivity pickUpDeliveryActivity;
    private CashbillSuccessActivity cashbillSuccessActivity;
    private InvoiceToMbSuccessActivity invoiceToMbSuccessActivity;
    private ReqInvoiceToBcSuccessActivity reqInvoiceToBcSuccessActivity;
    private ReqInvoiceToCompanySuccessActivity reqInvoiceToCompanySuccessActivity;
    private DetailRegistrationActivity detailRegistrationActivity;
    private RegistrationSuccessActivity registrationSuccessActivity;

    private HomeFragment homeFragment;
    private ActivityFragment activityFragment;
    private NotificationFragment notificationFragment;
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

    public MainActivityModule(DashboardActivity dashboardActivity) {
        this.dashboardActivity = dashboardActivity;
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

    public MainActivityModule(NotificationFragment notificationFragment) {
        this.notificationFragment = notificationFragment;
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

    public MainActivityModule(DetailTransactionActivity detailTransactionActivity) {
        this.detailTransactionActivity = detailTransactionActivity;
    }

    public MainActivityModule(CashbillActivity cashbillActivity) {
        this.cashbillActivity = cashbillActivity;
    }

    public MainActivityModule(InvoiceToMbActivity invoiceToMbActivity) {
        this.invoiceToMbActivity = invoiceToMbActivity;
    }

    public MainActivityModule(RegistrationActivity registrationActivity) {
        this.registrationActivity = registrationActivity;
    }

    public MainActivityModule(ReqInvoiceToBcActivity reqInvoiceToBcActivity) {
        this.reqInvoiceToBcActivity = reqInvoiceToBcActivity;
    }

    public MainActivityModule(ReqInvoiceToCompanyActivity reqInvoiceToCompanyActivity) {
        this.reqInvoiceToCompanyActivity = reqInvoiceToCompanyActivity;
    }

    public MainActivityModule(FeeBcmbActivity feeBcmbActivity) {
        this.feeBcmbActivity = feeBcmbActivity;
    }

    public MainActivityModule(MyProfileActivity myProfileActivity) {
        this.myProfileActivity = myProfileActivity;
    }

    public MainActivityModule(OmsetActivity omsetActivity) {
        this.omsetActivity = omsetActivity;
    }

    public MainActivityModule(SearchMemberActivity searchMemberActivity) {
        this.searchMemberActivity = searchMemberActivity;
    }

    public MainActivityModule(StockReportActivity stockReportActivity) {
        this.stockReportActivity = stockReportActivity;
    }

    public MainActivityModule(PickUpDeliveryActivity pickUpDeliveryActivity) {
        this.pickUpDeliveryActivity = pickUpDeliveryActivity;
    }

    public MainActivityModule(CashbillSuccessActivity cashbillSuccessActivity) {
        this.cashbillSuccessActivity = cashbillSuccessActivity;
    }

    public MainActivityModule(InvoiceToMbSuccessActivity invoiceToMbSuccessActivity) {
        this.invoiceToMbSuccessActivity = invoiceToMbSuccessActivity;
    }

    public MainActivityModule(ReqInvoiceToBcSuccessActivity reqInvoiceToBcSuccessActivity) {
        this.reqInvoiceToBcSuccessActivity = reqInvoiceToBcSuccessActivity;
    }

    public MainActivityModule(ReqInvoiceToCompanySuccessActivity reqInvoiceToCompanySuccessActivity) {
        this.reqInvoiceToCompanySuccessActivity = reqInvoiceToCompanySuccessActivity;
    }

    public MainActivityModule(DetailRegistrationActivity detailRegistrationActivity) {
        this.detailRegistrationActivity = detailRegistrationActivity;
    }

    public MainActivityModule(RegistrationSuccessActivity registrationSuccessActivity) {
        this.registrationSuccessActivity = registrationSuccessActivity;
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
    DashboardPresenter provideDashboardPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new DashboardPresenter(mainRepository, dataModel);
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
    NotificationPresenter provideInboxPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new NotificationPresenter(mainRepository, dataModel);
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

    @Provides
    @ActivityScope
    DetailTransactionPresenter provideDetailTransactionPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new DetailTransactionPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    CashbillActivityPresenter provideCashbillActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new CashbillActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    InvoiceToMbActivityPresenter provideInvoiceToMbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new InvoiceToMbActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RegistrationActivityPresenter provideRegistrationActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RegistrationActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ReqInvoiceToBcActivityPresenter provideReqInvoiceToBcActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ReqInvoiceToBcActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ReqInvoiceToCompanyActivityPresenter provideReqInvoiceToCompanyActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ReqInvoiceToCompanyActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    FeeBcmbActivityPresenter provideFeeBcmbActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new FeeBcmbActivityPresenter(mainRepository, dataModel);
    }
    @Provides
    @ActivityScope
    MyProfileActivityPresenter provideMyProfileActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new MyProfileActivityPresenter(mainRepository, dataModel);
    }
    @Provides
    @ActivityScope
    OmsetActivityPresenter provideOmsetActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new OmsetActivityPresenter(mainRepository, dataModel);
    }
    @Provides
    @ActivityScope
    SearchMemberActivityPresenter provideSearchMemberActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new SearchMemberActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    StockReportActivityPresenter provideStockReportActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new StockReportActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    PickUpDeliveryActivityPresenter providePickUpDeliveryActivityPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new PickUpDeliveryActivityPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    CashbillSuccessPresenter provideCashbillSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new CashbillSuccessPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    InvoiceToMbSuccessPresenter provideInvoiceToMbSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new InvoiceToMbSuccessPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ReqInvoiceToBcSuccessPresenter provideReqInvoiceToBcSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ReqInvoiceToBcSuccessPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    ReqInvoiceToCompanySuccessPresenter provideReqInvoiceToCompanySuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new ReqInvoiceToCompanySuccessPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    DetailRegistrationPresenter provideDetailRegistrationPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new DetailRegistrationPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RegistrationSuccessPresenter provideRegistrationSuccessPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RegistrationSuccessPresenter(mainRepository, dataModel);
    }
}
