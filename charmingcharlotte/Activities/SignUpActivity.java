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

public class SignUpActivity extends AppCompatActivity {


    private Button signUpButton;
    private EditText fNameEditTxt, lNameEditTxt, emailEditTxt, phoneNoEditTxt, dobEditTxt, passwordEditTxt, confirmPswEditTxt;
    private TextView oldUserTxtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.sign_up_btn);

        fNameEditTxt = findViewById(R.id.fName_edTxt);
        lNameEditTxt = findViewById(R.id.lName_edTxt);
        emailEditTxt = findViewById(R.id.email_edTxt);
        phoneNoEditTxt = findViewById(R.id.phone_no_edTxt);
        dobEditTxt = findViewById(R.id.dob_edTxt);
        passwordEditTxt = findViewById(R.id.password_edTxt);
        confirmPswEditTxt = findViewById(R.id.confirm_psw_edTxt);

        oldUserTxtView = findViewById(R.id.old_user_txtView);

        oldUserTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = fNameEditTxt.getText().toString();
                String lName = lNameEditTxt.getText().toString();
                String phoneNo = phoneNoEditTxt.getText().toString();
                String email = emailEditTxt.getText().toString();
                String dob = dobEditTxt.getText().toString();
                String password = passwordEditTxt.getText().toString();
                String confirmPsw = confirmPswEditTxt.getText().toString();

                //check if any of the fields are vacant
                if (fName.equals("") || lName.equals("") || email.equals("") || phoneNo.equals("") || dob.equals("")
                        || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill in All the Fields", Toast.LENGTH_LONG).show();
                    return;
                } else if (!password.equals(confirmPsw)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    User user = new User(fName, lName, phoneNo, email, dob, password);
                    user.save();
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ",
                            Toast.LENGTH_LONG).show();

                    finish();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}