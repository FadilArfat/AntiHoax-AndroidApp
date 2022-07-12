package com.fadil.antihoaxsearch;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    Button btn_register;
    EditText email_et,password_et;
    String email_txt,password_txt;
    TextView tvBacktologin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvBacktologin = findViewById(R.id.btn_backtologin);
        email_et    = findViewById(R.id.emailET);
        password_et = findViewById(R.id.passwordET);
        btn_register = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();
        tvBacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_txt = email_et.getText().toString();
                password_txt = password_et.getText().toString();
                if (TextUtils.isEmpty(email_txt)) {
                    Toast.makeText(getApplicationContext(), "Masukan Email !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password_txt)) {
                    Toast.makeText(getApplicationContext(), "Masukan Password !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email_txt, password_txt)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Daftar sukses, masuk ke Main Activity
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Jika daftar gagal, memberikan pesan
                                    Toast.makeText(Register.this, "Proses Pendaftaran gagal : " +  task.getException(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}
