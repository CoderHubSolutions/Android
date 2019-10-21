package tech.coderhub.android.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class ApiModule {
    @Singleton
    @Provides
    Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }
}
