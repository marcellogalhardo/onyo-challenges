package br.com.mgalhardo.onyo.ui.home;

import br.com.mgalhardo.onyo.data.repository.CompanyRepository;
import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Company;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CategoryListPresenter implements CategoryListContract.Presenter {

    private CategoryListContract.View view;
    private CompanyRepository repository;
    private Company company;
    private CompanyAggregation data;

    public CategoryListPresenter(CategoryListContract.View view, CompanyRepository repository, Company company) {
        this.view = view;
        this.repository = repository;
        this.company = company;
    }

    @Override
    public CompanyAggregation onSaveInstanceState() {
        return data;
    }

    @Override
    public void onLoadInstanceState(CompanyAggregation aggregation) {
        this.data = aggregation;
    }

    @Override
    public void loadCategories() {
        view.showLoadingLayout();
        repository.getCompanyAggregation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CompanyAggregation>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorLayout();
                    }

                    @Override
                    public void onNext(CompanyAggregation aggregation) {
                        data = aggregation;
                        refreshUi();
                    }
                });
    }

    @Override
    public void refreshUi() {
        if (data != null && data.categories.isEmpty()) {
            view.showEmptyLayout();
        } else {
            view.showSuccessLayout();
            view.refreshUi(data.categories);
        }
    }
}
