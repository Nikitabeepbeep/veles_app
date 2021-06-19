package com.example.veles_app.Interface;

import android.view.View;

//подставляет в каждый товар уникальное значени
public interface ItemClickListner {

    void onClick(View view, int adapterPosition, boolean isLongClick);
}
