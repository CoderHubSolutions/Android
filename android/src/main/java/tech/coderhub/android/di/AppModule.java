package tech.coderhub.android.di;

import android.app.Application;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tech.coderhub.android.helper.CacheHelper;
import tech.coderhub.android.network.ApiModule;

@Module(includes = ApiModule.class)
public abstract class AppModule {
    @Singleton
    @Provides
    static CacheHelper provideCache(Application application){
        return new CacheHelper(application);
    }
}
