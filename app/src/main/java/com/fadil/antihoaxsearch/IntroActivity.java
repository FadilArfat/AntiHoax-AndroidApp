package com.fadil.antihoaxsearch;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Panduan Aplikasi","satu","Baca cara penggunaan aplikasi sampai selesai sebelum menggunakan aplikasi ini","tes"
               , R.drawable.logo, ContextCompat.getColor(getApplicationContext(),R.color.primaryColor)));
        addSlide(AppIntroFragment.newInstance("Cek Fakta","satu","Sebelum mempercayai suatu berita,sebaiknya cek fakta terlebih dahulu di aplikasi ini","tes"
                , R.drawable.logo, ContextCompat.getColor(getApplicationContext(),R.color.primaryColor)));
        addSlide(AppIntroFragment.newInstance("Lapor Hoax","satu","Semua laporan hoax akan langsung dikirim ke email aduankonten@mail.kominfo.go.id","tes"
                , R.drawable.logo, ContextCompat.getColor(getApplicationContext(),R.color.primaryColor)));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent=new Intent(IntroActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent=new Intent(IntroActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

