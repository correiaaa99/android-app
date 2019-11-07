package com.example.artdecora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private AppCompatImageView ivFotoPerfil;
    private AppCompatTextView tvEmail, tvNome, tvApelido, tvUsername, tvDataNasc, tvTelemovel, tvLogout;
    private AppCompatButton btnPerfil;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializeComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
        }

        if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            getDataUser(email);
        } else {

        }

        sets();

        setNavigationViewListner();

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnPerfil.getText().toString().equals("Login")) {
                    Intent intent = new Intent(Perfil.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Perfil.this, "Ta logado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializeComponents() {
        ivFotoPerfil = findViewById(R.id.ivFotoPerfil);
        tvEmail = findViewById(R.id.tvEmail);
        tvNome = findViewById(R.id.tvNome);
        tvApelido = findViewById(R.id.tvApelido)
;        tvUsername = findViewById(R.id.tvUsername);
        tvDataNasc = findViewById(R.id.tvDataNasc);
        tvTelemovel = findViewById(R.id.tvTelemovel);
        btnPerfil = findViewById(R.id.btnPerfil);
        tvLogout = findViewById(R.id.tvLogout);
    }

    public void sets() {
        if (btnPerfil != null) {
            if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                btnPerfil.setText("Editar Perfil");
            } else {
                btnPerfil.setText("Login");
            }
        }

        if (tvEmail != null) {
            if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                tvEmail.setText(email);
            } else {
                tvEmail.setText("Email");
            }
        }

        if (tvLogout != null) {
            if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                tvLogout.setText("Logout");
            } else {
                tvLogout.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_chat) {
            if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                Intent intent = new Intent(Perfil.this,Registar.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(Perfil.this, Login.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_notifications) {
            if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                Intent intent = new Intent(Perfil.this,Registar.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(Perfil.this, Login.class);
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
                Intent intent = new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.nav_ideias: {
                if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                    Intent intent = new Intent(Perfil.this, Livro_Ideias.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(Perfil.this, Login.class);
                    startActivity(intent);
                }

                break;
            }

            case R.id.nav_perfil: {
                finish();
                startActivity(getIntent());
                break;
            }
        }
        return true;
    }

    public void getDataUser(String email) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = endpoints.URL_getUser+email;

// Request a string response from the provided URL.
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("users");

                                String username = jsonObject1.getString("username");
                                String nome = jsonObject1.getString("name");
                                String apelido = jsonObject1.getString("surname");

                                tvUsername.append(username);
                                tvNome.append(nome);
                                tvApelido.append(apelido);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Perfil.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });


        queue.add(jsonObject);
    }

    public void Logout(View view)
    {
        SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

        finish();
        startActivity(getIntent());
    }
}
