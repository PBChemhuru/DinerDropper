package com.example.dinerdropper;

import android.app.AlertDialog;
import android.content.Context;

import com.example.dinerdropper.fragments.homeFragment;
import com.example.dinerdropper.retrofit.RetrofitApi;
import com.example.dinerdropper.retrofit.RetrofitClientInstance;

public class Utils {
    public static RetrofitApi getApi() {
        return RetrofitClientInstance.getRetrofitInstance().create(RetrofitApi.class);
    }
    public static AlertDialog showDialogMessage(Context context, String title, String message) {
    AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
    if(alertDialog.isShowing()){
        alertDialog.cancel();
    }
    return  alertDialog;
    }
}
