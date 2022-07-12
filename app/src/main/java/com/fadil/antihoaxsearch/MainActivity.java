package com.fadil.antihoaxsearch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button logout;
    private  FirebaseAuth auth;
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List <MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Cari Berita", R.color.secondaryLightColor));
        menuItems.add(new MenuItem("Musik", R.color.secondaryLightColor));
        menuItems.add(new MenuItem("Pengaturan", R.color.secondaryLightColor));
        menuItems.add(new MenuItem("Tentang Kami", R.color.secondaryLightColor));


        sNavigationDrawer.setMenuItemList(menuItems);

        fragmentClass = Cari.class;

        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                    android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
                                                         @Override
                                                         public void onMenuItemClicked(int position) {
                                                             System.out.println("Position " + position);

                                                             switch (position) {
                                                                 case 0: {
                                                                     fragmentClass = Cari.class;
                                                                     break;
                                                                 }
                                                                 case 1: {
                                                                     fragmentClass = Musik.class;
                                                                     break;
                                                                 }
                                                                 case 2: {
                                                                     fragmentClass = Pengaturan.class;
                                                                     break;
                                                                 }
                                                                 case 3: {
                                                                     fragmentClass = Tentang.class;
                                                                     break;
                                                                 }
                                                             }

                                                             sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                                                                 @Override
                                                                 public void onDrawerOpening() {

                                                                 }

                                                                 @Override
                                                                 public void onDrawerClosing() {
                                                                     try {
                                                                         fragment = (Fragment) fragmentClass.newInstance();
                                                                     } catch (IllegalAccessException e) {
                                                                         e.printStackTrace();
                                                                     } catch (InstantiationException e) {
                                                                         e.printStackTrace();
                                                                     }
                                                                     if (fragment != null) {
                                                                         FragmentManager fragmentManager = getSupportFragmentManager();
                                                                         fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,
                                                                                 android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
                                                                     }

                                                                 }

                                                                 @Override
                                                                 public void onDrawerOpened() {

                                                                 }

                                                                 @Override
                                                                 public void onDrawerClosed() {

                                                                 }

                                                                 @Override
                                                                 public void onDrawerStateChanged(int newState) {

                                                                 }
                                                             });
                                                         }
                                                     });
    }


    private void goLoginScreen() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }




    public void panggilDO(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiodo);
        nfid.setVolume(2,2);
        nfid.start();
    }

    public void panggilRE(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiore);
        nfid.setVolume(2,2);
        nfid.start();
    }

    public void panggilMI(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiomi);
        nfid.setVolume(2,2);
        nfid.start();
    }

    public void panggilFA(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiofa);
        nfid.setVolume(2,2);
        nfid.start();
    }

    public void panggilSOL(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiosol);
        nfid.setVolume(2,2);
        nfid.start();
    }

    public void panggilLA(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiola);
        nfid.setVolume(2,2);
        nfid.start();
    }

    public void panggilSI(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiosi);
        nfid.setVolume(2,2);
        nfid.start();
    }


    public void panggilDO2(View view) {
        MediaPlayer nfid = MediaPlayer.create(this, R.raw.audiodo2);
        nfid.setVolume(2,2);
        nfid.start();
    }



}

