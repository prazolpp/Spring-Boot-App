package edu.sjsu.android.swipingsammies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SwipeScreen extends AppCompatActivity {
    TextView name;
    TextView bio;
    Button yesButton;
    Button noButton;
    String myEmail;
    Match potentialMatch;
    ImageView bioScreenButton;
    ImageView matchScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_screen);

        myEmail = getIntent().getExtras().getString("email");
        name = findViewById(R.id.potentialMatchName);
        bio = findViewById(R.id.potentialMatchBio);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        potentialMatch = null;
        bioScreenButton = findViewById(R.id.myAccountButton);
        matchScreenButton = findViewById(R.id.messagesButton);

        getPotentialMatch();

        yesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                RequestHelper.sendLike(myEmail, potentialMatch.getEmail());
                getPotentialMatch();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getPotentialMatch();
            }
        });

        bioScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwipeScreen.this, ProfileScreen.class);
                intent.putExtra("email", myEmail);
                startActivity(intent);
            }
        });

        matchScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwipeScreen.this, MatchesScreen.class);
                intent.putExtra("email", myEmail);
                startActivity(intent);
            }
        });
    }

    public void getPotentialMatch()
    {
        potentialMatch = RequestHelper.getPotentialMatch(myEmail);
        name.setText(potentialMatch.getName());
        bio.setText(potentialMatch.getBio());
    }
}
