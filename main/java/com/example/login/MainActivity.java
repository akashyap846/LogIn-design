package com.example.pubglogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    Button b1,slink;
    EditText et1,et2;
    FirebaseAuth fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent in7=new Intent(MainActivity.this,work.class);
            startActivity(in7);
            finish();
        }

        b1=(Button)findViewById(R.id.bt);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        slink=(Button)findViewById(R.id.slink);

        slink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  in1= new Intent(MainActivity.this,signup.class);
                startActivity(in1);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fa = FirebaseAuth.getInstance();

                if(TextUtils.isEmpty(et1.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(et2.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Please wait....", Toast.LENGTH_SHORT).show();
                fa.signInWithEmailAndPassword(et1.getText().toString(),et2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Intent in3=new Intent(MainActivity.this,work.class);
                       startActivity(in3);
                       finish();
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this, "Invalid Email and Password", Toast.LENGTH_SHORT).show();
                       et1.setText("");
                       et2.setText("");
                   }

                    }
                });


            }
        });

    }
}
