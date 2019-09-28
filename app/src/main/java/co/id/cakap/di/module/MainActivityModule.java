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
import co.id.cakap.ui.home.HomeActivity;
import co.id.cakap.ui.login.LoginActivity;
import co.id.cakap.ui.login.LoginPresenter;
import co.id.cakap.ui.main.MainActivity;
import co.id.cakap.ui.main.MainPresenter;
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
    private MainActivity mainActivity;
    private LoginActivity loginActivity;
    private HomeActivity homeActivity;

    public MainActivityModule(SplashScreenActivity splashScreenActivity) {
        this.splashScreenActivity = splashScreenActivity;
    }

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public MainActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @ActivityScope
    SplashScreenActivity provideSplashScreenActivity() {
        return splashScreenActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    HomeActivity provideHomeActivity() {
        return homeActivity;
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
    MainPresenter provideMainPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new MainPresenter(mainRepository, dataModel);
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
}
