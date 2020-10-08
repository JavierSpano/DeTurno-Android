package com.javierfspano.deturno.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.javierfspano.deturno.R;
import com.javierfspano.deturno.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View {

    @Inject
    SplashScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void goToMainActivity(String idToken) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.ID_TOKEN_EXTRA, idToken);
        startActivity(intent);
        finish();
    }

    @Override
    public void showTokenError() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Hubo un error al conectar con el servidor")
                .setNeutralButton("Reintentar", (dialogInterface, i) -> {
                    presenter.onRetry();
                    dialogInterface.dismiss();
                })
                .show();
    }
}