package sgw.projectusers.dagger.modules;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sgw.projectusers.model.rest.RestAPI;

@Module
public class RestModule {
    @Inject
    AppModule appModule;

    @Provides
    public RestAPI getRestApi(Retrofit retrofit) {
        return retrofit.create(RestAPI.class);
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient client, String baseUrl, CallAdapter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(factory)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public CallAdapter.Factory getRxCallFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public static String getBaseUrl() {
        return "https://api.github.com";
    }

}