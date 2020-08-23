package com.example.pubglogin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class work extends AppCompatActivity {

    Dialog d;
    TextView tv1,tv2,tv3,tv4,l1,l2,l3,l4;
    Button bt01,bt02,bt03,bt04;
    DatabaseReference reff,bff,pcc;
    ProgressDialog pd1;
    String en="on";
    CountDownTimer ct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        bt01=(Button)findViewById(R.id.bt01);
        bt02=(Button)findViewById(R.id.bt02);
        bt03=(Button)findViewById(R.id.bt03);
        bt04=(Button)findViewById(R.id.bt04);
        tv1= (TextView)findViewById(R.id.tv1);
        tv2= (TextView)findViewById(R.id.tv2);
        tv3= (TextView)findViewById(R.id.tv3);
        tv4= (TextView)findViewById(R.id.tv4);
        l1= (TextView)findViewById(R.id.l1);
        l2= (TextView)findViewById(R.id.l2);
        l3= (TextView)findViewById(R.id.l3);
        l4= (TextView)findViewById(R.id.l4);
        pd1=new ProgressDialog(this);

        ct=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                pd1.setMessage("please wait...");
                pd1.show();
            }

            @Override
            public void onFinish() {
                pd1.dismiss();

            }
        };
        ct.start();




        reff= FirebaseDatabase.getInstance().getReference().child("date");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String n=dataSnapshot.child("j1").getValue().toString();
                String m=dataSnapshot.child("j2").getValue().toString();
                String o=dataSnapshot.child("j3").getValue().toString();
                String p=dataSnapshot.child("j4").getValue().toString();
                tv1.setText(n);
                tv2.setText(m);
                tv3.setText(o);
                tv4.setText(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*
        bff = FirebaseDatabase.getInstance().getReference().child("button");
        bff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String  b1=dataSnapshot.child("bt1").getValue().toString();
                String  b2=dataSnapshot.child("bt2").getValue().toString();
                String  b3=dataSnapshot.child("bt3").getValue().toString();
                String  b4=dataSnapshot.child("bt4").getValue().toString();

                if (b1.equals(en))
                {
                    bt01.setEnabled(true);
                }
                else  bt01.setEnabled(false);

                if (b2.equals(en))
                {
                    bt02.setEnabled(true);
                }
                else  bt02.setEnabled(false);

                if (b3.equals(en))
                {
                    bt03.setEnabled(true);
                }
                else  bt03.setEnabled(false);

                if (b4.equals(en))
                {
                    bt04.setEnabled(true);
                }
                else  bt04.setEnabled(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 */


        pcc= FirebaseDatabase.getInstance().getReference().child("winner");
      pcc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String n1=dataSnapshot.child("l1").getValue().toString();
                String m1=dataSnapshot.child("l2").getValue().toString();
                String o1=dataSnapshot.child("l3").getValue().toString();
                String p1=dataSnapshot.child("l4").getValue().toString();
                l1.setText(n1);
                l2.setText(m1);
                l3.setText(o1);
                l4.setText(p1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



/*
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1=new Intent(work.this,join1.class);
                in1.putExtra("fir","10");
                startActivity(in1);
            }
        });


        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(work.this,join1.class);
                in2.putExtra("sec","20");
                startActivity(in2);

            }
        });

        bt03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3=new Intent(work.this,join1.class);
                in3.putExtra("third","30");
                startActivity(in3);
            }
        });

        bt04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in4=new Intent(work.this,join1.class);
                in4.putExtra("four","40");
                startActivity(in4);
            }
        });

 */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menubar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1)
        {
            d=new Dialog(work.this);
            d.setContentView(R.layout.about);
            d.show();
        }
        if(item.getItemId()==R.id.m2)
        {
            d=new Dialog(work.this);
            d.setContentView(R.layout.terms);
            d.show();
        }
        if(item.getItemId()==R.id.m4)
        {
            d=new Dialog(work.this);
            FirebaseAuth.getInstance().signOut();
            Intent in6=new Intent(work.this,MainActivity.class);
            startActivity(in6);
            finish();
            d.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
