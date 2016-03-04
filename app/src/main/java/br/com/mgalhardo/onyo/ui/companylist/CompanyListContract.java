package br.com.mgalhardo.onyo.ui.companylist;

import java.util.List;

import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Company;

public class CompanyListContract {

    public interface View {
        void refreshUi(List<Company> companies);
        void showLoadingLayout();
        void showErrorLayout();
        void showSuccessLayout();
        void showEmptyLayout();
    }

    public interface Presenter {
        CompanyAggregation onSaveInstanceState();
        void onLoadInstanceState(CompanyAggregation aggregation);
        void loadCompanies();
        void refreshUi();
        void retryButtonClick();
    }

}
