package com.example.veles_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.veles_app.Model.Products;
import com.example.veles_app.R;
import com.example.veles_app.LoginActivity;
import com.example.veles_app.RegisterActivity;
import com.example.veles_app.LoginActivity;
import com.example.veles_app.R;
import com.example.veles_app.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    AlertDialog.Builder builder;
    Button btn;
    DatabaseReference ProductsRef;
    ArrayList<State> states = new ArrayList<State>();
    //RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_menu)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this, states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){

        states.add(new State ("Рис розовый Девзира", "Пищевая ценность на 100 грамм продукта: белки - 7,0 г., жиры - 9,0 г., углеводы - 70,0 г. Энергетическая ценность: 323 Ккал","Цена: 150 рублей", R.drawable.devzira));
        states.add(new State ("Аргентина", "Буэнос-Айрес","150 рублей", R.drawable.applogo));
        states.add(new State ("Колумбия", "Богота","150 рублей", R.drawable.applogo));
        states.add(new State ("Уругвай", "Монтевидео","150 рублей", R.drawable.applogo));
        states.add(new State ("Чили", "Сантьяго","150 рублей", R.drawable.applogo));

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Меню");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Здесь будет переход в корзину", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_cart){

        } else if(id == R.id.nav_orders){

        } else if(id == R.id.about_us){

        } else if(id == R.id.settings){

        } else if(id == R.id.exit){

        }
        switch (id) {
            case R.id.nav_cart:
                Toast.makeText(this, "Тут будет переход в корзину", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_orders:
                Toast.makeText(this, "Тут будет переход к заказам", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_us:
                Intent s = new Intent(HomeActivity.this, About_us.class);
                startActivity(s);
                break;
            case R.id.settings:
                Intent x = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(x);
                break;
            case R.id.exit:
                this.finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
//        {
//            Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(loginIntent);
//        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}