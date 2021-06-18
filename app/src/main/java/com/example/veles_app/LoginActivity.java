package com.example.veles_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.veles_app.Model.Users;
import com.example.veles_app.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import io.paperdb.Paper;


public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText  phoneInput, passwordInput;
    private CheckBox checkBoxRememberMe;
    private ProgressDialog loadingBar;
    private String parentDBName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button) findViewById(R.id.login_btn);
        phoneInput = (EditText) findViewById(R.id.login_phone_input);
        passwordInput = (EditText) findViewById(R.id.login_password_input);
        checkBoxRememberMe = (CheckBox) findViewById(R.id.login_checkbox);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {

        String user = phoneInput.getText().toString();
        String pass = passwordInput.getText().toString();
//проверка на пустые поля
        if (TextUtils.isEmpty(user))
        {
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Вход в приложение");
            //для того что на него нельзя было нажать и показывалось
            loadingBar.setMessage("Пожалуйста, подождите!");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            //проверка существует ли уже пользователь
            ValidateLogin(user, pass);
        }
    }
    private void ValidateLogin( final String user, final String pass) {
        //запись данных в память телефона
        if (checkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalent.UserKey, user);
            Paper.book().write(Prevalent.Userpassword, pass);

        }
        //создание объектов в Database Reference
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        //проверка на то, существует ли такая запись
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            if (snapshot.child(parentDBName).child(user).exists())

            {
                Users usersData = snapshot.child(parentDBName).child(user).getValue(Users.class);

                //проверка совпадают ли данные с параметрами в Firebase
                if (usersData.getUser().equals(user)) {

                    if (usersData.getPassword().equals(pass)){

                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Неверный пароль!", Toast.LENGTH_SHORT).show();
                }
            }
            } else {
                loadingBar.dismiss();
                Toast.makeText(LoginActivity.this, "Аккаунта с таким" + user + "именем не существует", Toast.LENGTH_SHORT).show();
                // перенаправление на форму регистрации
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}