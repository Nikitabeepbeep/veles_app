package com.example.veles_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Zakaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakaz);
        Button btn = (Button)findViewById(R.id.oplata);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Zakaz.this, "Возврат в каталог", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Zakaz.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}