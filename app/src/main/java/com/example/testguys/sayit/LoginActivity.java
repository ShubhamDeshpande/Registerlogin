package com.example.testguys.sayit;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mLoginEmailField;
    private EditText mLoginPasswordField;
    private Button mLoginButton;
    private TextView mSignUp;
    private TextView mlog;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        mLoginEmailField = (EditText) findViewById(R.id.loginEmailField);
        mLoginPasswordField = (EditText) findViewById(R.id.loginPasswordField);
        mLoginButton = (Button) findViewById(R.id.loginbtn);

        mSignUp = (TextView) findViewById(R.id.textFieldSignUP);
        mProgress=new ProgressDialog(this);
        mlog=(TextView)findViewById(R.id.textViewU);


        mLoginButton.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
    }

    private void userLogin()
    {
        String email=mLoginEmailField.getText().toString().trim();
        String password=mLoginPasswordField.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        mProgress.setMessage("Wait .... ");
        mProgress.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgress.dismiss();

                if(task.isSuccessful())
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Error !!",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v==mLoginButton)
        {
            userLogin();
        }
        if(v==mSignUp)
        {
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }
}