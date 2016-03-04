package br.com.mgalhardo.onyo.ui.companylist;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.List;

import br.com.mgalhardo.onyo.R;
import br.com.mgalhardo.onyo.data.local.CompanyCache;
import br.com.mgalhardo.onyo.data.remote.CompanyService;
import br.com.mgalhardo.onyo.data.repository.CompanyRepository;
import br.com.mgalhardo.onyo.data.repository.impl.CompanyRepositoryImpl;
import br.com.mgalhardo.onyo.helper.BundleHelper;
import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.ui.base.BaseActivity;
import br.com.mgalhardo.onyo.ui.base.Router;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyListActivity extends BaseActivity implements CompanyListContract.View {

    @Bind(R.id.recyclerview)
    RecyclerView companyList;

    @Bind(R.id.successContainer)
    LinearLayout successContainer;

    @Bind(R.id.errorContainer)
    LinearLayout errorContainer;

    @Bind(R.id.loadingContainer)
    LinearLayout loadingContainer;

    @Bind(R.id.emptyContainer)
    LinearLayout emptyContainer;

    private CompanyListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        ButterKnife.bind(this);
        initialize(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CompanyAggregation.KEY, presenter.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private void dependencyInjection() {
        CompanyCache cache = new CompanyCache();
        CompanyService service = CompanyService.Builder.build();
        CompanyRepository repository = new CompanyRepositoryImpl(service, cache);
        presenter = new CompanyListPresenter(this, repository);
    }

    private void initialize(Bundle b) {
        dependencyInjection();
        initializeToolbar(R.id.toolbar);
        if (b == null) {
            presenter.loadCompanies();
        } else {
            CompanyAggregation aggregation = BundleHelper.getSerializable(b, CompanyAggregation.KEY);
            presenter.onLoadInstanceState(aggregation);
            presenter.refreshUi();
        }
    }

    @OnClick(R.id.retryButton)
    void retryGetCompanies() {
        presenter.retryButtonClick();
    }

    @Override
    public void refreshUi(List<Company> companies) {
        if (!companies.isEmpty()) {
            showSuccessLayout();
            setupCompanyList(companies);
        } else {
            showEmptyLayout();
        }
    }

    private void setupCompanyList(List<Company> companies) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        CompanyListAdapter companyListAdapter = new CompanyListAdapter(this, companies);
        companyListAdapter.setOnItemClickListener(new CompanyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Company company) {
                Router.launchHomeActivity(CompanyListActivity.this, company);
            }
        });
        companyList.setLayoutManager(linearLayoutManager);
        companyList.setAdapter(companyListAdapter);
    }

    @Override
    public void showLoadingLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.VISIBLE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessLayout() {
        successContainer.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.VISIBLE);
    }

}
