package com.example.veles_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button in, log_in;
    private Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in = (Button) findViewById(R.id.in);
        log_in = (Button) findViewById(R.id.log_in);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log_inIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(log_inIntent);
            }
        });
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(inIntent);
            }
        });
    }
}