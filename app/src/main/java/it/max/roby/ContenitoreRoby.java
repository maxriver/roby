package it.max.roby;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContenitoreRoby extends AppCompatActivity {

    public static DatabaseRoby dbLocale;
    public static Context context;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = this;
        dbLocale = new DatabaseRoby(getApplicationContext());

    }

}
