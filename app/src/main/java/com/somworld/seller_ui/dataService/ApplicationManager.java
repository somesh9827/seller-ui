package com.somworld.seller_ui.dataService;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by somesh.shrivastava on 11/01/15.
 */
public class ApplicationManager extends Application {
    private  static ApplicationManager mInstance;
    private  SharedPreferences mPreferences;


    public static  ApplicationManager getInstance() {
        if(mInstance == null)
        {
            synchronized (mInstance){
                mInstance = new ApplicationManager();
                mInstance.mPreferences = PreferenceManager.getDefaultSharedPreferences(mInstance);
            }
        }
        return mInstance;
    }

    public void  writePrefrence(String key,String value) {
        synchronized (this) {
            SharedPreferences.Editor prefEditor = mPreferences.edit();
            prefEditor.putString(key, value);
            prefEditor.commit();
        }
    }

    public String getPreference(String key,String defaultValue) {
        return mPreferences.getString(key,defaultValue);
    }

    private ApplicationManager() {
    }




}
