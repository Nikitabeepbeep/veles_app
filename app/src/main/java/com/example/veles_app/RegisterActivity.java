package com.example.veles_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button registerBtn;
    private EditText usernameInput, passwordInput, passwordInput1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerBtn = (Button) findViewById(R.id.register_btn);
        usernameInput = (EditText) findViewById(R.id.register_username_input);
        passwordInput = (EditText) findViewById(R.id.register_password_input);
        passwordInput1 = (EditText) findViewById(R.id.register_password_input1);
        DB = new DBHelper(this);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String password1 = passwordInput1.getText().toString();
                if (user.equals("") || password.equals("") || password1.equals(""))
                    Toast.makeText(RegisterActivity.this, "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(password1)) {
                        Boolean checkuser = DB.check_username(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, password);
                            if (insert == true) {
                                Toast.makeText(RegisterActivity.this, "Успешная регистрация!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Ошибка регистрации!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Пользователь с таким именем уже существует,пожалуйста,выберите другое имя!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}