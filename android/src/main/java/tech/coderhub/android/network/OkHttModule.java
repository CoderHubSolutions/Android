package tech.coderhub.android.network;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import tech.coderhub.android.helper.CacheHelper;
import tech.coderhub.android.helper.Constants;
import timber.log.Timber;

@Module
public class OkHttModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor, CacheHelper helper){

        int REQUEST_TIMEOUT = 60;
//        File cacheDir = new File(new MyApplication().getCacheDir(), UUID.randomUUID().toString());
//        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
//                .cache(cache)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain ->{
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("X-Requested-With","XMLHttpRequest")
                    .addHeader("Authorization","Bearer "+helper.getValue(Constants.ACCESS_TOKEN));
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        });
        return httpClient.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
