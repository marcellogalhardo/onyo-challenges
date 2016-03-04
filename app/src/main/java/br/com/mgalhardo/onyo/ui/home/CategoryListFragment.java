package br.com.mgalhardo.onyo.ui.home;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.lucasr.twowayview.TwoWayView;

import java.util.List;

import br.com.mgalhardo.onyo.R;
import br.com.mgalhardo.onyo.data.local.CompanyCache;
import br.com.mgalhardo.onyo.data.remote.CompanyService;
import br.com.mgalhardo.onyo.data.repository.CompanyRepository;
import br.com.mgalhardo.onyo.data.repository.impl.CompanyRepositoryImpl;
import br.com.mgalhardo.onyo.helper.BundleHelper;
import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Category;
import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.ui.base.BaseFragment;
import br.com.mgalhardo.onyo.ui.base.Router;
import br.com.mgalhardo.onyo.ui.companylist.CompanyListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryListFragment extends BaseFragment implements CategoryListContract.View {

    @Bind(R.id.recyclerview)
    RecyclerView categoryList;

    @Bind(R.id.successContainer)
    LinearLayout successContainer;

    @Bind(R.id.errorContainer)
    LinearLayout errorContainer;

    @Bind(R.id.loadingContainer)
    LinearLayout loadingContainer;

    @Bind(R.id.emptyContainer)
    LinearLayout emptyContainer;

    private CategoryListContract.Presenter presenter;
    private Company company;

    public static CategoryListFragment newInstance(Company company) {
        Bundle args = new Bundle();
        args.putSerializable(Company.KEY, company);

        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_list, container, false);
        ButterKnife.bind(this, v);
        initialize(savedInstanceState);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CompanyAggregation.KEY, presenter.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private void initialize(Bundle b) {
        loadArguments(getArguments());
        dependencyInjection();
        if (b == null) {
            presenter.loadCategories();
        } else {
            CompanyAggregation aggregation = BundleHelper.getSerializable(b, CompanyAggregation.KEY);
            presenter.onLoadInstanceState(aggregation);
            presenter.refreshUi();
        }
    }

    private void loadArguments(Bundle b) {
        company = BundleHelper.getSerializable(b, Company.KEY);
    }

    private void dependencyInjection() {
        CompanyCache cache = new CompanyCache();
        CompanyService service = CompanyService.Builder.build();
        CompanyRepository repository = new CompanyRepositoryImpl(service, cache);
        presenter = new CategoryListPresenter(this, repository, company);
    }

    @Override
    public void refreshUi(List<Category> categories) {
        if (!categories.isEmpty()) {
            showSuccessLayout();
            setupCategoryList(categories);
        } else {
            showEmptyLayout();
        }
    }

    private void setupCategoryList(List<Category> categories) {
        Context context = this.getContext();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        CategoryListAdapter adapter = new CategoryListAdapter(context, categories);

        categoryList.setHasFixedSize(true);
        categoryList.setLayoutManager(manager);
        categoryList.setAdapter(adapter);
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
