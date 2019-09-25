package co.id.cakap.di.component;

import co.id.cakap.di.scope.ActivityScope;
import co.id.cakap.di.module.MainActivityModule;
import co.id.cakap.ui.home.HomeActivity;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.main.MainActivity;
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
    MainActivity inject(MainActivity mainActivity);
    LoginActivity inject(LoginActivity loginActivity);
    HomeActivity inject(HomeActivity homeActivity);
}
