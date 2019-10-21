package tech.coderhub.android.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttModule.class)
public class RetrofitModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient,GsonConverterFactory converterFactory){
        return  new Retrofit.Builder()
                .baseUrl(AccessDetails.serviceurl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();
    }



    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return  GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    Gson provideGson(){
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
