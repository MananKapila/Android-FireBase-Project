package com.example.rockpaperscissor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {



    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference gameRef= rootRef.child("game");

    TextView textView;
    Button rock,scissor,paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myRef.child("Users").child("01").child("Email").setValue("mkmk@cool.com");
      //  myRef.setValue("cool");
        rock=findViewById(R.id.rock);
        paper=findViewById(R.id.paper);
        scissor=findViewById(R.id.scissor);
        textView=findViewById(R.id.textView);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Rock");
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Paper");
            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Scissor");
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text= dataSnapshot.getValue().toString();
                textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG","something is missing here");
            }
        });
    }
}
