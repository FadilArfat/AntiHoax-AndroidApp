package com.fadil.antihoaxsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class AkunActivity extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView tvname,tvemail,tvid;
    ImageView profpic;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        btnLogout = (Button) findViewById(R.id.logOut);
        profpic = (ImageView) findViewById(R.id.profpic);
        tvname = (TextView) findViewById(R.id.tv_name) ;
        tvemail = (TextView) findViewById(R.id.email);
        tvid = (TextView) findViewById(R.id.tv_id);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(AkunActivity.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                updateUI();
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser == null){

        }
        else if(currentUser != null){
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            String id = currentUser.getUid();
            String img_url = currentUser.getPhotoUrl().toString();
            tvname.setText(name);
            tvemail.setText(email);
            tvid.setText(id);
            Glide.with(this).load(img_url).apply(RequestOptions.circleCropTransform()).into(profpic);

        }

        }

    private void updateUI(){
        Toast.makeText(AkunActivity.this,"Berhasil Logout!", Toast.LENGTH_LONG).show();
        Intent intenkuy = new Intent(AkunActivity.this,Login.class);
        startActivity(intenkuy);
        finish();
    }
}
