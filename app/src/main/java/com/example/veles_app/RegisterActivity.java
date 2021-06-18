package com.example.veles_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button registerBtn;
    private EditText usernameInput, passwordInput, passwordInput1;
    //создание процесс диалога
    private ProgressDialog loadinBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        registerBtn = (Button) findViewById(R.id.register_btn);
        usernameInput = (EditText) findViewById(R.id.register_username_input);
        passwordInput = (EditText) findViewById(R.id.register_password_input);
        passwordInput1 = (EditText) findViewById(R.id.register_password_input1);
        loadinBar = new ProgressDialog(this);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccaunt();
            }

            //вызов метода регистрации
            private void CreateAccaunt() {
                String user = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String password1 = passwordInput1.getText().toString();
                //проверка на пустые поля
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(RegisterActivity.this, "Введите имя пользователя!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Введите пароль!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(RegisterActivity.this, "Введите пароль!", Toast.LENGTH_SHORT).show();
                } else {
                    loadinBar.setTitle("Создание аккаунта");
                    //для того что на него нельзя было нажать и показывалось
                    loadinBar.setMessage("Пожалуйста, подождите!");
                    loadinBar.setCanceledOnTouchOutside(false);
                    loadinBar.show();
                    //проверка существует ли уже пользователь
                    Validateuser(user, password, password1);

                }
            }

            private void Validateuser(String user, String password, String password1) {
                //создание объектов в Database Reference
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!(dataSnapshot.child("Users").child(user).exists())) {
                            HashMap<String, Object> userDataMap = new HashMap<>();
                            userDataMap.put("user", user);
                            userDataMap.put("password", password);
                            userDataMap.put("password1", password1);

                            RootRef.child("Users").child(user).updateChildren(userDataMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                loadinBar.dismiss();
                                                Toast.makeText(RegisterActivity.this, "Регистрация прошла успешно.", Toast.LENGTH_SHORT).show();

                                                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                startActivity(loginIntent);
                                            } else {
                                                loadinBar.dismiss();
                                                Toast.makeText(RegisterActivity.this, "Ошибка.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            loadinBar.dismiss();
                            Toast.makeText(RegisterActivity.this, "Имя пользователя" + user + "уже зарегистрировано", Toast.LENGTH_SHORT).show();

                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}


