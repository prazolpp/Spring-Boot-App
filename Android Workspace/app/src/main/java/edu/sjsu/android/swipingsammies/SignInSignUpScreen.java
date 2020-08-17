package edu.sjsu.android.swipingsammies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInSignUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Button signInButton;
        Button signUpButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up_screen);

        signInButton = findViewById(R.id.sign_in_button);
        signUpButton = findViewById(R.id.sign_up_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInSignUpScreen.this, SignInScreen.class);
                SignInSignUpScreen.this.startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInSignUpScreen.this, SignUpScreen.class);
                SignInSignUpScreen.this.startActivity(intent);
            }
        });
    }
}
