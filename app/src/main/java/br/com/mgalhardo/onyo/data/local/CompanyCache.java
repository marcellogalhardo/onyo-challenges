package br.com.mgalhardo.onyo.data.local;

import java.util.List;

import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import br.com.mgalhardo.onyo.model.implementation.Company;
import io.paperdb.Paper;
import io.paperdb.PaperDbException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompanyCache {

    public Observable<CompanyAggregation> set(final CompanyAggregation aggregation) {
        Observable.OnSubscribe<CompanyAggregation> onSubscribe = new Observable.OnSubscribe<CompanyAggregation>() {
            @Override
            public void call(Subscriber<? super CompanyAggregation> subscriber) {
                try {
                    Paper.book().write(CompanyAggregation.KEY, aggregation);
                    subscriber.onNext(aggregation);
                    subscriber.onCompleted();
                } catch (PaperDbException e) {
                    subscriber.onError(e);
                }
            }
        };
        return Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CompanyAggregation> get() {
        Observable.OnSubscribe<CompanyAggregation> onSubscribe = new Observable.OnSubscribe<CompanyAggregation>() {
            @Override
            public void call(Subscriber<? super CompanyAggregation> subscriber) {
                CompanyAggregation aggregation = Paper.book().read(CompanyAggregation.KEY);
                if (aggregation != null) {
                    subscriber.onNext(aggregation);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NullPointerException());
                }
            }
        };
        return Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
