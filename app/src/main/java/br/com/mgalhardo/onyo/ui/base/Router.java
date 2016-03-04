package br.com.mgalhardo.onyo.ui.base;

import android.content.Context;
import android.content.Intent;

import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.ui.companylist.CompanyListActivity;
import br.com.mgalhardo.onyo.ui.home.HomeActivity;

public class Router {

    public static void launchHomeActivity(Context c, Company company) {
        Intent i = new Intent(c, HomeActivity.class);
        i.putExtra(Company.KEY, company);
        c.startActivity(i);
    }

    public static void launchCompanyListActivity(Context c) {
        c.startActivity(new Intent(c, CompanyListActivity.class));
    }

}
