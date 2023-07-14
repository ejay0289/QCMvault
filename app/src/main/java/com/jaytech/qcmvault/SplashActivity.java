package com.jaytech.qcmvault;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends Activity {
Window window;
    private static final int SPLASH_DELAY = 2000; // Delay in milliseconds
    private Handler handler;
    private Runnable runnable;
	ImageView offline;
	ActionBar action;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
		
		action = getActionBar();
		action.hide();
		if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.brightBlue));
        }
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (!isConnected()) {
					offline = findViewById(R.id.offline);
					offline.setVisibility(View.VISIBLE);
                    Toast.makeText(SplashActivity.this, "No internet access", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this, SPLASH_DELAY); // Check again after a delay
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        handler.postDelayed(runnable, SPLASH_DELAY); // Start checking after a delay
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the runnable callbacks to prevent memory leaks
        handler.removeCallbacks(runnable);
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}

