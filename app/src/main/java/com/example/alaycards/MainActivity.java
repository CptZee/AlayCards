package com.example.alaycards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.alaycards.Menus.MainFragment;
import com.example.alaycards.Menus.SplashFragment;
import com.example.alaycards.Services.EasyLevelMusicService;
import com.example.alaycards.Services.HardLevelMusicService;
import com.example.alaycards.Services.MenuMusicService;
import com.example.alaycards.Services.NormalLevelMusicService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new SplashFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (currentFragment instanceof MainFragment) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit the application?")
                    .setPositiveButton("Yes", (ignore, ignore2) -> {
                        finish();
                    })
                    .setNegativeButton("No", (ignore, ignore2) ->{

                    })
                    .create()
                    .show();
        } else
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MenuMusicService.class));
        stopService(new Intent(this, EasyLevelMusicService.class));
        stopService(new Intent(this, NormalLevelMusicService.class));
        stopService(new Intent(this, HardLevelMusicService.class));
    }
}