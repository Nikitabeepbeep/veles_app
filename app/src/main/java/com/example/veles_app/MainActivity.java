package com.example.veles_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.veles_app.Model.Users;
import com.example.veles_app.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Button in, log_in;
    private Button login_btn;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in = (Button) findViewById(R.id.in);
        log_in = (Button) findViewById(R.id.log_in);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

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

        String Username = Paper.book().read(Prevalent.UserKey);
        String Password = Paper.book().read(Prevalent.Userpassword);
        //если активные записи существуют
        if (Username != "" && Password != "") {
        if (!TextUtils.isEmpty(Username) && !TextUtils.isEmpty(Password) ) {
        ValidateUser(Username,Password);
        }
        }
        }

    private void ValidateUser(final String user,final String pass) {
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
            loadingBar.setTitle("Вход в приложение");
            //для того что на него нельзя было нажать и показывалось
            loadingBar.setMessage("Пожалуйста, подождите!");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
        }
    }
    private void ValidateLogin( final String user, final String pass) {
        //запись данных в память телефона
//        if (checkBoxRememberMe.isChecked()){
//            Paper.book().write(Prevalent.UserKey, user);
//            Paper.book().write(Prevalent.Userpassword, pass);
//        }
        //создание объектов в Database Reference
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        //проверка на то, существует ли такая запись
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.child("Users").child(user).exists())

                {
                    Users usersData = snapshot.child("Users").child(user).getValue(Users.class);

                    //проверка совпадают ли данные с параметрами в Firebase
                    if (usersData.getUser().equals(user)) {

                        if (usersData.getPassword().equals(pass)){

                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(homeIntent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Неверный пароль!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    loadingBar.dismiss();
                    Toast.makeText(MainActivity.this, "Аккаунта с таким" + user + "именем не существует", Toast.LENGTH_SHORT).show();
                    // перенаправление на форму регистрации
                    Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(registerIntent);
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }
}