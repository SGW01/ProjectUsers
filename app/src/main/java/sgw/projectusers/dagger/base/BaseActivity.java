package sgw.projectusers.dagger.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sgw.projectusers.dagger.App;
import sgw.projectusers.dagger.components.ActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    /**
     * Injects Presenter.
     */
    private ActivityComponent injector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector = restoreInjector();
        inject(injector);
    }

    /**
     * Request to execute injection of itself.
     * @param injector injector
     */
    public abstract void inject(ActivityComponent injector);

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    /** Restores injector while configuration change. */
    public ActivityComponent restoreInjector() {
        Object o = getLastCustomNonConfigurationInstance();
        if (o == null) {
            return getApp().getAppComponent().getActivityComponent();
        } else {
            return (ActivityComponent) o;
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return injector;
    }

    public App getApp() {
        return (App) super.getApplication();
    }

}