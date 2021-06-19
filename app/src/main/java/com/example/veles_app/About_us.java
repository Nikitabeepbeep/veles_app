package com.example.veles_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About_us extends AppCompatActivity {
Button exit;
AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Button btn_help = (Button) findViewById(R.id.help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/eexxtazzy"));
                startActivity(browserIntent);
            }
        });
    }
}