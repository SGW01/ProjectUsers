package sgw.projectusers.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import sgw.projectusers.dagger.App;
import sgw.projectusers.dagger.modules.AppModule;
import sgw.projectusers.dagger.modules.NetworkModule;
import sgw.projectusers.dagger.modules.RestModule;
import sgw.projectusers.dagger.modules.SharedPreferenceModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        RestModule.class,
        SharedPreferenceModule.class
})
/**
 * Application component. Source component for all sub-components.
 */

public interface AppComponent {

    void inject(App app);
    ActivityComponent getActivityComponent();
    final class Initializer {
        private Initializer() {}

        public static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule())
                    .restModule(new RestModule())
                    .sharedPreferenceModule(new SharedPreferenceModule())
                    .build();
        }
    }
}
