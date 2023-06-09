package com.example.myapplication;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button add,stat;
    TextView dates,dist,siss,bps,ndata,a,b,c,d;
    String mail;
    String ss;
    long cnt=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.add);
        stat=findViewById(R.id.stat);
        dates=findViewById(R.id.dates);
        dist=findViewById(R.id.diss);
        siss=findViewById(R.id.siss);
        bps=findViewById(R.id.bps);
        //ndata=findViewById(R.id.ndata);
        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        c=findViewById(R.id.c);
        d=findViewById(R.id.d);

       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mail = user.getEmail();
            // Use the email address as needed
        }



        String email=mail.replace(".",",");

        Date date= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat simpleTimeFormat=new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

        String dat=simpleDateFormat.format(date);
        String tim=simpleTimeFormat.format(date);


        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference(email);

      /* databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                cnt = snapshot.getChildrenCount();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



         /*   Intent intent1=getIntent();
            ss=intent1.getStringExtra("ss");

            if(ss.equals(NULL)){
                ss=String.valueOf(0);
            }


            databaseReference.child(ss).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    health hl=snapshot.getValue(health.class);

                    ndata.setVisibility(View.GONE);
                    dates.setText(hl.getDate());
                    dist.setText(hl.getDis());
                    siss.setText(hl.getSys());
                    bps.setText(hl.getBp());
                }
                else{
                    ndata.setText("No Data Added Today");
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                    dist.setVisibility(View.GONE);
                    siss.setVisibility(View.GONE);
                    bps.setVisibility(View.GONE);
                    dates.setVisibility(View.GONE);
                }
               // MainActivity.this.notify();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/

        /**
         * This method redirect user to Add to add data in database
         */

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, add.class);
                startActivity(intent);
                finish();
            }
        });

        /**
         * this class redirect to the recyclerview to show previously added data from firebase
         */
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,stat.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtra("email",email);
                startActivity(intent);
                //finish();
            }
        });
    }
}