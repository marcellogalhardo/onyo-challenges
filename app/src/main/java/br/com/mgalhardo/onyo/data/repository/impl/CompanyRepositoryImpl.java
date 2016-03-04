package br.com.mgalhardo.onyo.data.repository.impl;

import br.com.mgalhardo.onyo.data.local.CompanyCache;
import br.com.mgalhardo.onyo.data.repository.CompanyRepository;
import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.data.remote.CompanyService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CompanyRepositoryImpl implements CompanyRepository {

    private CompanyService service;
    private CompanyCache cache;

    public CompanyRepositoryImpl(CompanyService service, CompanyCache cache) {
        this.service = service;
        this.cache = cache;
    }

    @Override
    public Observable<CompanyAggregation> getCompanyAggregation() {
        return cache.get();
    }

    @Override
    public Observable<CompanyAggregation> synchronize() {
        return service.getCompanyAggregation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Func1<CompanyAggregation, Observable<CompanyAggregation>>() {
            @Override
            public Observable<CompanyAggregation> call(CompanyAggregation aggregation) {
                return cache.set(aggregation);
            }
        });
    }
}