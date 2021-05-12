package com.example.veles_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText usernameInput, phoneInput, passwordInput;
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button) findViewById(R.id.login_btn);
        phoneInput = (EditText) findViewById(R.id.login_phone_input);
        passwordInput = (EditText) findViewById(R.id.login_password_input);
        checkBoxRememberMe = (CheckBox) findViewById(R.id.login_checkbox);
try {
    loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent login_btnIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(login_btnIntent);
        }
    });
} catch (Exception e) {
    System.out.println("Произошла ошибка");
}
    }
}