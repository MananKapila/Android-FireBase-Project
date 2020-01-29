package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button button;
    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        //startActivity(intent);
        Toast.makeText(this,"Already Logged In. Redirecting",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        button=findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onRegister(View view){

        final String myEmail = email.getText().toString();
        final String myPassword =password.getText().toString();
//        if(myEmail.isEmpty()==true)
//        {
//            Toast.makeText(getApplicationContext(),"Email cannot be left empty",Toast.LENGTH_SHORT).show();
//        }
//        if(myPassword.isEmpty()==true)
//        {
//            Toast.makeText(getApplicationContext(),"Password can't be left empty",Toast.LENGTH_SHORT).show();
//        }
        mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void onLogin(View view){
        final String myEmail= email.getText().toString();
        final String myPass= password.getText().toString();
        mAuth.signInWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("tag", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"Auth Success",Toast.LENGTH_SHORT).show();
                            Log.i("User","USER:"+user.toString());
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("tag", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void onLogout(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this,"LOGGED OUT",Toast.LENGTH_SHORT).show();
    }


}
