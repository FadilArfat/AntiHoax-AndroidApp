package com.fadil.antihoaxsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;


public class Pengaturan extends Fragment {
    TextView txtAkun, txtNilai, txtKebijakan;
    private AdView adBanner;
    private AdRequest adRequest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengaturan, container, false);
        txtAkun = (TextView) view.findViewById(R.id.txtAkun);
        MobileAds.initialize(getActivity(),"ca-app-pub-2767077821626186~7875044803");
        adBanner=(AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adBanner.loadAd(adRequest);
        txtAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AkunActivity.class);
                startActivity(intent);
            }
        });

        txtKebijakan = (TextView) view.findViewById(R.id.kebijakan);
        txtKebijakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intef=new Intent((getActivity()),PrivayPolicy.class);
                startActivity(intef);
            }
        });

        return view;
    }
}

