package tech.coderhub.android.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedHelper {

    private static SharedPreferences sharedPreferences;

    public static void putKey(Context context, String Key, String Value) {
        assert  context != null;
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, Value);
        editor.apply();

    }

    public static String getKey(Context contextGetKey, String Key) {
        sharedPreferences = contextGetKey.getSharedPreferences("Cache", Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key, "");

    }

    public static void clearSharedPreferences(Context context)
    {
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }



}
