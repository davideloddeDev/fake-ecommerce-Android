package com.nuboo.fakeecommerce;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nuboo.fakeecommerce.ProductAdapter;
import com.nuboo.fakeecommerce.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://fakestoreapi.com/";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.product_list);
        progressBar = findViewById(R.id.progress_bar);

        // Configura il layout manager per il RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Mostra la ProgressBar durante il caricamento dei dati
        progressBar.setVisibility(View.VISIBLE);

        // Crea un'istanza di Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea un'istanza dell'interfaccia API
        FakeStoreApi fakeStoreApi = retrofit.create(FakeStoreApi.class);

        // Effettua la chiamata API per recuperare i prodotti
        Call<List<Product>> call = fakeStoreApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    // Crea un'istanza dell'adapter e impostalo nel RecyclerView
                    ProductAdapter adapter = new ProductAdapter(products);
                    recyclerView.setAdapter(adapter);
                } else {
                    showError();
                }
                // Nascondi la ProgressBar dopo aver caricato i dati
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                showError();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showError() {
        Toast.makeText(this, "Si è verificato un errore durante il caricamento dei prodotti", Toast.LENGTH_SHORT).show();
    }
}
