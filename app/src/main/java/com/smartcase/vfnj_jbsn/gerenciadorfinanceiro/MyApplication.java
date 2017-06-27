package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dinho-PC on 11/06/2017.
 */

public class MyApplication extends Application {

        private static Context context;

        public void onCreate() {
            super.onCreate();
            MyApplication.context = getApplicationContext();
        }

        public static Context getAppContext() {
            return MyApplication.context;
        }
    }

