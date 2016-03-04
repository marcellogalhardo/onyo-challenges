package br.com.mgalhardo.onyo.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import br.com.mgalhardo.onyo.R;
import br.com.mgalhardo.onyo.helper.BundleHelper;
import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.ui.base.BaseActivity;
import br.com.mgalhardo.onyo.ui.base.ViewPagerStateAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.tablayout)
    TabLayout tabLayout;

    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initialize(savedInstanceState);
    }

    private void initialize(Bundle savedInstanceState) {
        loadArguments(getIntent().getExtras());
        setupViewPager();
    }

    private void loadArguments(Bundle b) {
        company = BundleHelper.getSerializable(b, Company.KEY);
    }

    private void setupViewPager() {
        ViewPagerStateAdapter adapter = new ViewPagerStateAdapter(getSupportFragmentManager());
        adapter.addFragment(EmptyFragment.newInstance(), getString(R.string.tab_home));
        adapter.addFragment(CategoryListFragment.newInstance(company), getString(R.string.tab_menu));
        adapter.addFragment(EmptyFragment.newInstance(), getString(R.string.tab_redeem));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
