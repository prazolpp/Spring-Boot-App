package edu.sjsu.android.swipingsammies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MatchesScreen extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    String myEmail;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_screen);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(getMatches());
        recyclerView.setAdapter(mAdapter);
        backButton = findViewById(R.id.backButton);
        myEmail = getIntent().getExtras().getString("email");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatchesScreen.this, SwipeScreen.class);
                intent.putExtra("email", myEmail);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Match> getMatches()
    {
        return RequestHelper.getMatches(myEmail);
    }
}
