package com.fadil.antihoaxsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Tentang extends Fragment {
    TextView txtLapor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tentang,container,false);

        txtLapor = (TextView) view.findViewById(R.id.txtLapor);
        txtLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"nf.idmobile@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Lapor Bug ");
                try {
                    startActivity(Intent.createChooser(i, "Mengirim Laporan"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Tidak Ada Aplikasi Email yang Terinstall", Toast.LENGTH_SHORT).show();
                }
            }
        });

            return  view;
    }
}
