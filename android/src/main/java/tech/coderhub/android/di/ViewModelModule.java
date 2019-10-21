package tech.coderhub.android.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import tech.coderhub.android.ui.forgotPasswordScreen.ForgotPasswordViewModel;
import tech.coderhub.android.ui.loginScreen.LoginViewModel;
import tech.coderhub.android.ui.profileDetailsScreen.ProfileDetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import tech.coderhub.android.base.CoderHubViewModelFactory;
import tech.coderhub.android.base.CommonViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel.class)
    abstract ViewModel bindsForgotPasswordViewModel(ForgotPasswordViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileDetailsViewModel.class)
    abstract ViewModel bindsProfileDetailsViewModel(ProfileDetailsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindsLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommonViewModel.class)
    abstract ViewModel bindsCommonViewModel(CommonViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(CoderHubViewModelFactory factory);

}
