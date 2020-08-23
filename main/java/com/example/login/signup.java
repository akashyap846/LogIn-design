package com.example.pubglogin;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
EditText et11,et22,et33,et44;
Button bt1;
ProgressDialog pd;
DatabaseReference drf,cc;
FirebaseAuth mat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et11=(EditText)findViewById(R.id.et11);
        et22=(EditText)findViewById(R.id.et12);
        et33=(EditText)findViewById(R.id.et13);
        et44=(EditText)findViewById(R.id.et14);
        bt1=(Button)findViewById(R.id.bt1);
        pd=new ProgressDialog(this);
        ActivityCompat.requestPermissions(signup.this,new String[]{Manifest.permission.INTERNET},1);
        try {

            mat = FirebaseAuth.getInstance();
            drf = FirebaseDatabase.getInstance().getReference().child("sign up");
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(et11.getText().toString())) {
                        Toast.makeText(signup.this, "Enter name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(et22.getText().toString())) {
                        Toast.makeText(signup.this, "Enter number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (et22.getText().toString().length()<10)
                    {
                        Toast.makeText(signup.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(et33.getText().toString())) {
                        Toast.makeText(signup.this, "Enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(et44.getText().toString())) {
                        Toast.makeText(signup.this, "Enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (et44.getText().toString().length()<6)
                    {
                        Toast.makeText(signup.this, "Minimum Length Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    pd.setMessage("Registering user.....");
                    pd.show();

                    cc=drf.child(et11.getText().toString());
                    cc.push().setValue(et11.getText().toString());
                    cc.push().setValue(et22.getText().toString());
                    cc.push().setValue(et33.getText().toString());
                    cc.push().setValue(et44.getText().toString());

                    mat.createUserWithEmailAndPassword(et33.getText().toString(),et44.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(signup.this, "Registered...", Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(signup.this,MainActivity.class);
                                startActivity(in);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(signup.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                et33.setText("");
                                et44.setText("");
                            }
                        }
                    });



                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
