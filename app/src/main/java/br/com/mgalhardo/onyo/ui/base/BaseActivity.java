package br.com.mgalhardo.onyo.ui.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void initializeToolbar(@IdRes int id) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        if (toolbar != null)  {
            setSupportActionBar(toolbar);
            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayShowTitleEnabled(true);
            }
        }
    }

}
