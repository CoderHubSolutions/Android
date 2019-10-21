package tech.coderhub.android.base;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import tech.coderhub.android.MyApplication;
import tech.coderhub.android.di.ActivityModule;
import tech.coderhub.android.di.AppModule;
import tech.coderhub.android.di.ViewModelModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder provideApplication(Application application);
        ApplicationComponent build();
    }

    void inject(MyApplication application);
}
