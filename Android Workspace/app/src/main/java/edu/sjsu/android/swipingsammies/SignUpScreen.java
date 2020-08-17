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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUpScreen extends AppCompatActivity {

    EditText usernameBox;
    EditText passwordBox;
    EditText nameBox;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        usernameBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        nameBox = findViewById(R.id.nameBox);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if (RequestHelper.createAccountRequest(nameBox.getText().toString(), usernameBox.getText().toString(), passwordBox.getText().toString()))
                    {
                        Intent intent = new Intent(SignUpScreen.this, SwipeScreen.class);
                        intent.putExtra("username", usernameBox.getText().toString());
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "An Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
