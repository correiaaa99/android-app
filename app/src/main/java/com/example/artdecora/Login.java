package com.example.artdecora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class Login extends AppCompatActivity {

    AppCompatTextView ola;
    AppCompatEditText txtEmail,txtPassword;
    Button btnEntrar;
    ConstraintLayout loginForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setStatusBarTransparent();
        inicializeComponents();

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            loginForm.setVisibility(View.VISIBLE);
        }

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnline())
                {
                    if (txtPassword.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        login(txtEmail.getText().toString(), txtPassword.getText().toString());
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "Não existe conecção a internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void login(final String email, String password){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = endpoints.URL_login+email+"&password="+password;
        // Request a string response from the provided URL.
        StringRequest putRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if(String.valueOf(response).equals("login efetuado com sucesso"))
                        {
                            SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(Login.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
        };
        queue.add(putRequest);

    }


    private void setStatusBarTransparent()
    {
        if(Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //registo normal
    public void registar(View view)
    {
        Intent intent = new Intent(this,Registar.class);
        startActivity(intent);
    }

    private void inicializeComponents() {
        ola = findViewById(R.id.ola);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        loginForm = findViewById(R.id.loginForm);
    }

    public boolean isOnline()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
