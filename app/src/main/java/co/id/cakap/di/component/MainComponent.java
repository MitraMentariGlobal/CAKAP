package co.id.cakap.di.component;

import co.id.cakap.di.scope.ActivityScope;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.feeBCMB.FeeBcmbActivity;
import co.id.cakap.ui.memberDetail.MemberDetailActivity;
import co.id.cakap.ui.myProfile.MyProfileActivity;
import co.id.cakap.ui.omset.OmsetActivity;
import co.id.cakap.ui.reqInvoiceToBc.ReqInvoiceToBcActivity;
import co.id.cakap.ui.reqInvoiceToCompany.ReqInvoiceToCompanyActivity;
import co.id.cakap.ui.cashbill.CashbillActivity;
import co.id.cakap.ui.dashboard.DashboardActivity;
import co.id.cakap.ui.dashboard.account.AccountFragment;
import co.id.cakap.ui.dashboard.activity.ActivityFragment;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillFragment;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbFragment;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbFragment;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbFragment;
import co.id.cakap.ui.dashboard.home.HomeFragment;
import co.id.cakap.ui.dashboard.notification.NotificationFragment;
import co.id.cakap.ui.dashboard.restock.RestockFragment;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockFragment;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceFragment;
import co.id.cakap.ui.detailTransaction.DetailTransactionActivity;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.ui.invoiceToMb.InvoiceToMbActivity;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.registration.RegistrationActivity;
import co.id.cakap.ui.pickUpDelivery.PickUpDeliveryActivity;
import co.id.cakap.ui.searchMember.SearchMemberActivity;
import co.id.cakap.ui.splashScreen.SplashScreenActivity;
import co.id.cakap.ui.stockReport.StockReportActivity;
import dagger.Subcomponent;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainComponent {
    SplashScreenActivity inject(SplashScreenActivity splashScreenActivity);
    LoginActivity inject(LoginActivity loginActivity);
    HomeWebViewActivity inject(HomeWebViewActivity homeWebViewActivity);
    DashboardActivity inject(DashboardActivity dashboardActivity);
    DetailTransactionActivity inject(DetailTransactionActivity detailTransactionActivity);
    CashbillActivity inject(CashbillActivity cashbillActivity);
    InvoiceToMbActivity inject(InvoiceToMbActivity invoiceToMbActivity);
    RegistrationActivity inject(RegistrationActivity registrationActivity);
    ReqInvoiceToBcActivity inject(ReqInvoiceToBcActivity reqInvoiceToBcActivity);
    ReqInvoiceToCompanyActivity inject(ReqInvoiceToCompanyActivity reqInvoiceToCompanyActivity);
    FeeBcmbActivity inject(FeeBcmbActivity feeBcmbActivity);
    MyProfileActivity inject(MyProfileActivity myProfileActivity);
    OmsetActivity inject(OmsetActivity omsetActivity);
    SearchMemberActivity inject(SearchMemberActivity searchMemberActivityz);
    StockReportActivity inject(StockReportActivity omsetActivity);
    MemberDetailActivity inject(MemberDetailActivity activity);
    PickUpDeliveryActivity inject(PickUpDeliveryActivity activity);

    HomeFragment inject(HomeFragment homeFragment);
    AccountFragment inject(AccountFragment accountFragment);
    ActivityFragment inject(ActivityFragment activityFragment);
    NotificationFragment inject(NotificationFragment notificationFragment);
    RestockFragment inject(RestockFragment restockFragment);
    ActivityCashbillFragment inject(ActivityCashbillFragment activityCashbillFragment);
    ActivityInvToMbFragment inject(ActivityInvToMbFragment activityInvToMbFragment);
    ActivityRekapBnsBcmbFragment inject(ActivityRekapBnsBcmbFragment activityRekapBnsBcmbFragment);
    ActivityReqInvMbFragment inject(ActivityReqInvMbFragment activityReqInvMbFragment);
    RestockInvoiceFragment inject(RestockInvoiceFragment restockInvoiceFragment);
    RestockReceiveStockFragment inject(RestockReceiveStockFragment restockReceiveStockFragment);
    RestockReqInvoiceFragment inject(RestockReqInvoiceFragment restockReqInvoiceFragment);
}
