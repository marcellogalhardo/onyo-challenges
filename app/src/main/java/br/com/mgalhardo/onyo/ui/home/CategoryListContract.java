package br.com.mgalhardo.onyo.ui.home;

import java.util.List;

import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Category;
import br.com.mgalhardo.onyo.model.implementation.Company;

public class CategoryListContract {

    public interface View {
        void refreshUi(List<Category> categories);
        void showLoadingLayout();
        void showErrorLayout();
        void showSuccessLayout();
        void showEmptyLayout();
    }

    public interface Presenter {
        CompanyAggregation onSaveInstanceState();
        void onLoadInstanceState(CompanyAggregation aggregation);
        void loadCategories();
        void refreshUi();
    }

}
