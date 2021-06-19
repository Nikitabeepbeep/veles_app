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
        states.add(new State ("Крупа кукурузная", "Крупа кукурузная №3,4,5,6. ГОСТ. Производство краснодарский край.","Цена: 55 рублей", R.drawable.kukuruza_krup));
        states.add(new State ("Рис Камолино", " Пищевая ценность на 100 грамм продукта: белки - 7,0 г., жиры - 9,0 г., углеводы - 70,0 г. Энергетическая ценность: 323 Ккал","Цена: 80 рублей", R.drawable.kamolino));
        states.add(new State ("Крупа гороховая", "Краснодарский горох(крупа). ГОСТ 1 сорт. Производство - Краснодарский край.","Цена: 40 рублей", R.drawable.krypa_gorox));
        states.add(new State ("Рис ГОСТ 1 Сорт", "Пищевая ценность в 100 г продукта: Белки - 0,7 г, жиры - 1,0 г, углеводы – 74,0 г Энергетическая ценность: 333 Ккал/1394 кДж.","Цена: 130 рублей", R.drawable.ris1sort));
        states.add(new State ("Бурый рис Здоровье", "Пищевая ценность в 100 г. продукта: белок-7,4 г.; жиры-2,2г.; углеводы-52,0г. Энергетическая ценность -285Кал/1190кДж.","Цена: 55 рублей", R.drawable.buryjis));
        states.add(new State ("Льняное масло", "Фасовка бутылки: 0,5 л.","Цена: 200 рублей", R.drawable.maslolen));
        states.add(new State ("Кукурузное масло", "Фасовка бутылки: 0,5 л.","Цена: 150 рублей", R.drawable.kukuruza));
        states.add(new State ("Тыквенное масло", "Фасовка бутылки: 0,5 л.","Цена: 450 рублей", R.drawable.tukvennoemaslo));
        states.add(new State ("Соевое масло", "Фасовка бутылки: 0,5 л.","Цена: 200 рублей", R.drawable.soyevoyemaslo));
        states.add(new State ("Подсолнечное масло", "Фасовка бутылки: 0,5 л.","Цена: 110 рублей", R.drawable.maslopodsolnechnoye));
        states.add(new State ("Рыжиковое масло", "Фасовка бутылки: 0,5 л.","Цена: 350 рублей", R.drawable.ryzhikovoyemaslo));
        states.add(new State ("Кунжутное масло", "Фасовка бутылки: 0,5 л.","Цена: 600 рублей", R.drawable.ryzhikovoyemaslo));

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Меню");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent cart = new Intent(HomeActivity.this, Cart_activity.class);
                startActivity(cart);
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

        } else if(id == R.id.exit){

        }
        switch (id) {
            case R.id.nav_cart:
                Intent cart = new Intent(HomeActivity.this, Cart_activity.class);
                startActivity(cart);
                break;
            case R.id.nav_orders:
                Toast.makeText(this, "Тут будет переход к заказам", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_us:
                Intent s = new Intent(HomeActivity.this, About_us.class);
                startActivity(s);
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