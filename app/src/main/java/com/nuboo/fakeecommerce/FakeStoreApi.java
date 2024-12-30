package com.nuboo.fakeecommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FakeStoreApi {

    @GET("products")
    Call<List<Product>> getProducts();

    // Puoi aggiungere altri metodi per altre rotte dell'API qui
}

