package com.mad.charmingcharlotte.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.charmingcharlotte.Models.User;
import com.mad.charmingcharlotte.R;
import com.mad.charmingcharlotte.SharedPrefUtility;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView newUserTxtView;
    private EditText emailEditTxt, passwordEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String loggedInEmail = SharedPrefUtility.read(SharedPrefUtility.EMAIL, null);

        //Check if User is Already Logged In
        if (loggedInEmail != null) {
            finish();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        loginButton = findViewById(R.id.login_btn);
        newUserTxtView = findViewById(R.id.new_user_txtView);
        emailEditTxt = findViewById(R.id.email_edTxt);
        passwordEditTxt = findViewById(R.id.password_edTxt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the email & password
                String email = emailEditTxt.getText().toString();
                String password = passwordEditTxt.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill in All the Fields", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //fetch the password from the database for the respective email
                    List<User> users = User.findWithQuery(User.class, "Select * from User where email = ? ", email);
                    for (User user : users) {
                        if (user.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            SharedPrefUtility.write(SharedPrefUtility.EMAIL, email);
                            finish();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

        newUserTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}