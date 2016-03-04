package br.com.mgalhardo.onyo.data.repository;

import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import rx.Observable;

public interface CompanyRepository {

    Observable<CompanyAggregation> getCompanyAggregation();
    Observable<CompanyAggregation> synchronize();

}
