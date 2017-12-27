package sgw.projectusers.dagger;

import android.app.Application;

import sgw.projectusers.dagger.components.AppComponent;

public class App extends Application {

    private static App app;
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        app = App.this;
        component = AppComponent.Initializer.init(this);
    }

    public static AppComponent getAppComponent() {
        return app.component;
    }

}