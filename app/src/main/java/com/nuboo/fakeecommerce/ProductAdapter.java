package com.nuboo.fakeecommerce;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView productImage;
    private String id;
    private TextView productName;
    private TextView productDescription;
    private TextView productPrice;
    private Product product; // Aggiungi questa variabile

    ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.product_image);
        productName = itemView.findViewById(R.id.product_name);
        productDescription = itemView.findViewById(R.id.product_description);
        productPrice = itemView.findViewById(R.id.product_price);
        productImage.setOnClickListener(this);
        productName.setOnClickListener(this);
        productDescription.setOnClickListener(this);
        productPrice.setOnClickListener(this);

    }

    void bind(Product product) {
        this.product = product; // Assegna l'oggetto Product corrente
        id = String.valueOf(product.getId());
        productName.setText(product.getTitle());
        productDescription.setText(product.getDescription());
        productPrice.setText(String.format("$%.2f", product.getPrice()));
        Glide.with(itemView.getContext())
                .load(product.getImage())
                .into(productImage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.product_image) {
            // Avvia l'attività ImageViewerActivity con l'URL dell'immagine
            Intent intent = new Intent(itemView.getContext(), ImageViewerActivity.class);
            intent.putExtra("image_url", product.getImage()); // Usa l'oggetto Product corrente
            itemView.getContext().startActivity(intent);
        }
        if (v.getId() == R.id.product_name && v.getId() == R.id.product_description && v.getId() == R.id.product_price) {
            resoconto(Integer.parseInt(id));
        }
    }

        private void resoconto(int id) {
            Log.d("id", String.valueOf(id));
            Intent resocontoIntent = new Intent(itemView.getContext(), ResocontoActivity.class);
            resocontoIntent.putExtra("id", id);
            itemView.getContext().startActivity(resocontoIntent);
        }


    }

}

