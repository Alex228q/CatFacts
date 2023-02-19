package com.example.catfacts;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("fact")
    Single<Cat> loadFact();
}
