package tech.coderhub.android.helper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class CacheHelper {

    private final Application application;
    private final SharedPreferences sharedPreferences;
    public CacheHelper(Application application) {
        this.application = application;
        sharedPreferences = application.getSharedPreferences("Cache", Context.MODE_PRIVATE);
    }

    public void putValue(String key,String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getValue(String key){
        return sharedPreferences.getString(key,"");
    }

    public void clearAllCache(){
        sharedPreferences.edit().clear().apply();
    }
}
