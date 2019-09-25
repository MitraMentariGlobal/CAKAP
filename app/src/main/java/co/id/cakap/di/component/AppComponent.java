package co.id.cakap.di.component;

import javax.inject.Singleton;

import co.id.cakap.di.module.AppModule;
import co.id.cakap.di.module.NetworkModule;
import co.id.cakap.di.module.MainActivityModule;
import dagger.Component;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)

public interface AppComponent {
    MainComponent plus(MainActivityModule mainActivityModule);
}
