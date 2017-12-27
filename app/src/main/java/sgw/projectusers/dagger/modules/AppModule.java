package sgw.projectusers.dagger.modules;

import android.content.Context;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sgw.projectusers.dagger.App;

@Module
public class AppModule {
    /** Application instance. */
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return app;
    }

    @Provides
    public App getApp() {
        return app;
    }

    @Provides
    public File getFilesDir() {
        return app.getFilesDir();
    }
}