package tech.coderhub.android.network;


import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import tech.coderhub.android.MyApplication;
import tech.coderhub.android.R;

public class NetworkError {
    public static String getServerResponseMessage(Throwable throwable) {
        if (throwable instanceof SocketTimeoutException) {
            return MyApplication.getInstance().getResources().getString(R.string.time_out_error);
        } else if (throwable instanceof IOException) {
            return MyApplication.getInstance().getResources().getString(R.string.oops_connect_your_internet);
        }
        return  MyApplication.getInstance().getResources().getString(R.string.unknown_error);
    }

    public static int getErrorCode(Throwable throwable) {
        return ((HttpException) throwable).code();
    }
}
