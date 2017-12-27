package sgw.projectusers.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.class)
public class SharedPreferenceModule {

    public static final String AUTH_PREF = "auth";


    @Provides
    public SharedPreferences getMainPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Named(AUTH_PREF)
    @Provides
    public SharedPreferences getAuthPrefs(Context context) {
        return context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE);
    }

}