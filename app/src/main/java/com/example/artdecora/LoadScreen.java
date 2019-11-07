package com.example.artdecora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadScreen extends AppCompatActivity {


    private TextView tvSlogan;
    private ImageView imgLogo;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);

        inicializeComponents();
        setStatusBarTransparent();

        Animation animacao = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tvSlogan.startAnimation(animacao);
        imgLogo.startAnimation(animacao);
        final Intent intent = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
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

    private void inicializeComponents() {
        tvSlogan = findViewById(R.id.tvSlogan);
        imgLogo = findViewById(R.id.imgLogo);
    }
}
