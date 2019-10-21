package tech.coderhub.android.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import java.lang.ref.WeakReference;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;
import tech.coderhub.android.helper.CacheHelper;

public abstract class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject public CacheHelper cache;
    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject public Application applicationContext;
    @Inject public ViewModelProvider.Factory viewModelFactory;

    protected CompositeDisposable disposable = new CompositeDisposable();
    protected Activity activityContext;
    protected WeakReference<Activity> activityWeakReferenceContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        activityContext = this;
        activityWeakReferenceContext = new WeakReference<>(this);
    }

    @LayoutRes
    protected abstract int layoutRes();

    public void addFragment(int fragmentId,Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentId,fragment);
        transaction.commit();
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
        activityContext = null;
        activityWeakReferenceContext = null;
    }
}
