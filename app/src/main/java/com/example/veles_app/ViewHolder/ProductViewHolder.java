package com.example.veles_app.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.veles_app.Interface.ItemClickListner;
import com.example.veles_app.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;


    public ProductViewHolder(View itemView)
    {
        super(itemView);


//        imageView = itemView.findViewById(R.id.product);
//        txtProductName = itemView.findViewById(R.id.product_name);
//        txtProductDescription = itemView.findViewById(R.id.product_description);
//        txtProductPrice = itemView.findViewById(R.id.product_price);
    }

    public void setItemClickListner() {
        setItemClickListner();
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
