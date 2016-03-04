package br.com.mgalhardo.onyo.ui.companylist;

import br.com.mgalhardo.onyo.data.repository.CompanyRepository;
import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import rx.Subscriber;

public class CompanyListPresenter implements CompanyListContract.Presenter {

    private CompanyListContract.View view;
    private CompanyRepository repository;
    private CompanyAggregation data;

    public CompanyListPresenter(CompanyListContract.View view, CompanyRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public CompanyAggregation onSaveInstanceState() {
        return data;
    }

    @Override
    public void onLoadInstanceState(CompanyAggregation aggregation) {
        data = aggregation;
    }

    @Override
    public void loadCompanies() {
        view.showLoadingLayout();
        repository.synchronize().subscribe(new Subscriber<CompanyAggregation>() {
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
        if (data != null && data.companies.isEmpty()) {
            view.showEmptyLayout();
        } else {
            view.showSuccessLayout();
            view.refreshUi(data.companies);
        }
    }

    @Override
    public void retryButtonClick() {
        view.showLoadingLayout();
        loadCompanies();
    }
}
