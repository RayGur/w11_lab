package com.example.w11_lab;
// MyService.java
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    private Thread delayThread;

    @Override
    public void onCreate() {
        super.onCreate();
        delayThread = new Thread() {
            @Override
            public void run() {
                try {
                    // Delay for 5 seconds
                    Thread.sleep(5000);

                    // Start MainActivity2
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    // Stop the service
                    stopSelf();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!delayThread.isAlive()) {
            delayThread.start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (delayThread != null && delayThread.isAlive()) {
            delayThread.interrupt();
        }
    }
}