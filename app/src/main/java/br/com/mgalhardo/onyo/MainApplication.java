package br.com.mgalhardo.onyo;

import android.app.Application;

import io.paperdb.Paper;

public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initialize();
    }

    private void initialize() {
        Paper.init(this);
    }

}
