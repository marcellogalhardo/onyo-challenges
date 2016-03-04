package br.com.mgalhardo.onyo.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.mgalhardo.onyo.model.aggregation.CompanyAggregation;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;

public interface CompanyService {

    @GET("company")
    Observable<CompanyAggregation> getCompanyAggregation();

    class Builder {

        private static final String ENDPOINT = "http://private-2314f-onyo.apiary-mock.com";

        public static CompanyService build() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(CompanyService.class);
        }

    }
}