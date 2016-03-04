package br.com.mgalhardo.onyo.ui.companylist;

import java.util.List;

import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Company;

public interface CompanyListContract {

    interface View {
        void refreshUi(List<Company> companies);
        void showLoadingLayout();
        void showErrorLayout();
        void showSuccessLayout();
        void showEmptyLayout();
    }

    interface Presenter {
        CompanyAggregation onSaveInstanceState();
        void onLoadInstanceState(CompanyAggregation aggregation);
        void loadCompanies();
        void refreshUi();
        void retryButtonClick();
    }

}
