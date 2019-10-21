package tech.coderhub.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

//import tech.coderhub.android.base.DaggerApplicationComponent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import tech.coderhub.android.helper.LocaleUtils;
import tech.coderhub.android.helper.SharedHelper;
import timber.log.Timber;

//import androidx.multidex.MultiDex;
//import androidx.multidex.MultiDexApplication;

public class MyApplication extends Application implements HasActivityInjector {

    private static MyApplication mInstance;

    @Inject DispatchingAndroidInjector<Activity> androidInjector;

    @Override
    protected void attachBaseContext(Context base) {
        String lan= SharedHelper.getKey(base, "language");
        if(lan.isEmpty() || lan.trim().length()<=0){
            super.attachBaseContext(LocaleUtils.onAttach(base, "en"));
        }else if(lan.equalsIgnoreCase("bn")){
            super.attachBaseContext(LocaleUtils.onAttach(base, "bn"));
        }else{
            super.attachBaseContext(LocaleUtils.onAttach(base, "en"));
        }
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        mInstance = this;
//        DaggerApplicationComponent.builder().provideApplication(this).build().inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public static String trimMessage(String json){
        StringBuilder trimmedString = new StringBuilder();

        try{
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    JSONArray value = jsonObject.getJSONArray(key);
                    for (int i = 0, size = value.length(); i < size; i++) {
                        trimmedString.append(value.getString(i));
                        if(i < size-1) {
                            trimmedString.append('\n');
                        }
                    }
                } catch (JSONException e) {

                    trimmedString.append(jsonObject.optString(key));
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return trimmedString.toString();
    }
}
