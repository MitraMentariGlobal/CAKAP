package co.id.cakap.di.component;

import co.id.cakap.di.scope.ActivityScope;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.dashboard.account.AccountFragment;
import co.id.cakap.ui.dashboard.activity.ActivityFragment;
import co.id.cakap.ui.dashboard.activity.activityCashbill.ActivityCashbillFragment;
import co.id.cakap.ui.dashboard.activity.activityInvToMb.ActivityInvToMbFragment;
import co.id.cakap.ui.dashboard.activity.activityRekapBnsBcmb.ActivityRekapBnsBcmbFragment;
import co.id.cakap.ui.dashboard.activity.activityReqInvMb.ActivityReqInvMbFragment;
import co.id.cakap.ui.dashboard.home.HomeFragment;
import co.id.cakap.ui.dashboard.inbox.InboxFragment;
import co.id.cakap.ui.dashboard.restock.RestockFragment;
import co.id.cakap.ui.dashboard.restock.restockInvoice.RestockInvoiceFragment;
import co.id.cakap.ui.dashboard.restock.restockReceiveStock.RestockReceiveStockFragment;
import co.id.cakap.ui.dashboard.restock.restockReqInvoice.RestockReqInvoiceFragment;
import co.id.cakap.ui.homeWebView.HomeWebViewActivity;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.splash_screen.SplashScreenActivity;
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
    HomeFragment inject(HomeFragment homeFragment);
    AccountFragment inject(AccountFragment accountFragment);
    ActivityFragment inject(ActivityFragment activityFragment);
    InboxFragment inject(InboxFragment inboxFragment);
    RestockFragment inject(RestockFragment restockFragment);
    ActivityCashbillFragment inject(ActivityCashbillFragment activityCashbillFragment);
    ActivityInvToMbFragment inject(ActivityInvToMbFragment activityInvToMbFragment);
    ActivityRekapBnsBcmbFragment inject(ActivityRekapBnsBcmbFragment activityRekapBnsBcmbFragment);
    ActivityReqInvMbFragment inject(ActivityReqInvMbFragment activityReqInvMbFragment);
    RestockInvoiceFragment inject(RestockInvoiceFragment restockInvoiceFragment);
    RestockReceiveStockFragment inject(RestockReceiveStockFragment restockReceiveStockFragment);
    RestockReqInvoiceFragment inject(RestockReqInvoiceFragment restockReqInvoiceFragment);
}
