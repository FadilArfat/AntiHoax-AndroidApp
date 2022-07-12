package com.fadil.antihoaxsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Cari extends Fragment {
    CardView cvCarib, cvCarig, cvLapor, cvCara;
    Activity actCari;
    InterstitialAd adIntersial;
    private  AdRequest adRequest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cari,container,false);
        cvCarib = (CardView) view.findViewById(R.id.cariBerita);
        adIntersial = new InterstitialAd(getActivity());
        adIntersial.setAdUnitId("ca-app-pub-2767077821626186/6129706539");
        AdRequest adRequest = new AdRequest.Builder().build();
        adIntersial.loadAd(adRequest);

        cvCarib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkaniklan();
                Intent intent=new Intent(getActivity(),ActivityCariBerita.class);
                startActivity(intent);
            }
        });

        cvCarig = (CardView) view.findViewById(R.id.cariFB);
        cvCarig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ActivityCariFacebook.class);
                startActivity(intent);
            }
        });
        cvCara = (CardView) view.findViewById(R.id.caraPake);
        cvCara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ActivityInfo.class);
                startActivity(intent);
            }
        });
        cvLapor = (CardView) view.findViewById(R.id.laporHoax);
        cvLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"aduankonten@mail.kominfo.go.id"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Lapor Hoax");
                try {
                    startActivity(Intent.createChooser(i, "Lapor Hoax..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Tidak Ada Aplikasi Email yang Terinstall", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  view;
    }
    private void tampilkaniklan() {

        if (adIntersial.isLoaded()) {

            adIntersial.show();

        }

    }
}
