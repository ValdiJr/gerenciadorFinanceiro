package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ManagerFinanceSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static ManagerFinanceSyncAdapter sManagerFinanceSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("ManagerFinanceSyncService", "onCreate - ManagerFinanceSyncService");
        synchronized (sSyncAdapterLock) {
            if (sManagerFinanceSyncAdapter == null) {
                sManagerFinanceSyncAdapter = new ManagerFinanceSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sManagerFinanceSyncAdapter.getSyncAdapterBinder();
    }
}