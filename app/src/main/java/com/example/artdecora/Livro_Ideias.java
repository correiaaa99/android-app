package com.example.artdecora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class Livro_Ideias extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private Dialog dialog;
    List<Item_LivroIdeias> item_livroIdeiasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro__ideias);

        inicializeComponents();

        setNavigationViewListner();

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       item_livroIdeiasList = new ArrayList<>();
       item_livroIdeiasList.add(new Item_LivroIdeias(R.drawable.logotipo,R.drawable.registar_icon,"ola"));
       item_livroIdeiasList.add(new Item_LivroIdeias(R.drawable.logotipo,R.drawable.registar_icon,"adeus"));
       item_livroIdeiasList.add(new Item_LivroIdeias(R.drawable.logotipo,R.drawable.registar_icon,"bianca"));
       item_livroIdeiasList.add(new Item_LivroIdeias(R.drawable.logotipo,R.drawable.registar_icon,"fabiana"));
       item_livroIdeiasList.add(new Item_LivroIdeias(R.drawable.logotipo,R.drawable.registar_icon,"judite"));
       item_livroIdeiasList.add(new Item_LivroIdeias(R.drawable.logotipo,R.drawable.registar_icon,"vera"));
        Adapter_livroIdeias adapter_livroIdeias = new Adapter_livroIdeias(this,item_livroIdeiasList,dialog);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter_livroIdeias);
    }


    private void inicializeComponents() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_chat) {
            if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                Intent intent = new Intent(Livro_Ideias.this,Registar.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(Livro_Ideias.this, Login.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_notifications) {
            if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                Intent intent = new Intent(Livro_Ideias.this,Registar.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(Livro_Ideias.this, Login.class);
                startActivity(intent);
            }
        }
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_inicio: {
                if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                    Intent intent = new Intent(Livro_Ideias.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(Livro_Ideias.this, Login.class);
                    startActivity(intent);
                }
                break;
            }

            case R.id.nav_ideias: {
                finish();
                startActivity(getIntent());
                break;
            }

            case R.id.nav_perfil: {
                if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                    Intent intent = new Intent(Livro_Ideias.this,Perfil.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(Livro_Ideias.this, Login.class);
                    startActivity(intent);
                }
                break;
            }
        }
        return true;
    }
}