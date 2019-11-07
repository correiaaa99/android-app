package com.example.artdecora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;




import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            email = bundle.getString("email");
        }

        inicializeComponents();

        setNavigationViewListner();

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void inicializeComponents() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.nav_chat)
        {
            if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                Intent intent = new Intent(MainActivity.this,Registar.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        }
        else if (id==R.id.nav_notifications)
        {
            if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                Intent intent = new Intent(MainActivity.this,Registar.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        }
        if (mToggle.onOptionsItemSelected(item))
        {
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
                finish();
                startActivity(getIntent());
                break;
            }

            case R.id.nav_ideias:{

                if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                    Intent intent = new Intent(MainActivity.this, Livro_Ideias.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                break;
            }

            case R.id.nav_perfil:{

                    Intent intent = new Intent(MainActivity.this, Perfil.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                break;
            }
        }
        return true;
    }

    private void Logout() {
        SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
