package tech.coderhub.android.di;


import tech.coderhub.android.ui.forgotPasswordScreen.ForgotPasswordActivity;
import tech.coderhub.android.ui.loginScreen.LoginActivity;
import tech.coderhub.android.ui.profileDetailsScreen.ProfileDetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import tech.coderhub.android.MainActivity;

@Module
public abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract ForgotPasswordActivity contributeForgotPasswordActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract ProfileDetailsActivity contributeProfileDetailsActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();
}
