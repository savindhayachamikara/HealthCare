package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edPassword ,edConfirm, edEmail;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUsername = findViewById(R.id.editTextBMBFullName);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edEmail = findViewById(R.id.editTextBMBAddress);
        edConfirm= findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonBMBBooking);
        tv = findViewById(R.id.textViewExitingUser);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill the Details", Toast.LENGTH_SHORT).show();
                } else{

                    if (password.compareTo(confirm)==0) {
                        if (isValid(password)) {
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    else {
                        Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters, one letter, one number and one special character",Toast.LENGTH_SHORT).show();
                    }
                    }else
                        Toast.makeText(getApplicationContext(),"Password and confirm password does not match",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public static boolean isValid(String password){
        int f1=0, f2=0, f3=0;
        if (password.length()<8){
            return false;
        }else {
            for (int p = 0; p < password.length(); p++) {
                if (Character.isLetter(password.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < password.length(); r++) {
                if (Character.isDigit(password.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s=0;s<password.length();s++){
                char c=password.charAt(s);
                if (c >= 33 && c <= 46 || c == 64){
                    f3=1;
                }

            }
            if (f1==1 && f2==1 && f3==1)
                return true;
            else
                return false;
        }
    }
}
