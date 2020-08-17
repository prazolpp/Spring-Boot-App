package edu.sjsu.android.swipingsammies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignInScreen extends AppCompatActivity
{
    EditText usernameBox;
    EditText passwordBox;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        usernameBox = findViewById(R.id.email);
        passwordBox = findViewById(R.id.password);
        loginButton = findViewById(R.id.logInButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RequestHelper.logInRequest(usernameBox.getText().toString(), passwordBox.getText().toString()))
                {
                    Intent intent =  new Intent(SignInScreen.this, SwipeScreen.class);
                    intent.putExtra("username", usernameBox.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Username or password incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
