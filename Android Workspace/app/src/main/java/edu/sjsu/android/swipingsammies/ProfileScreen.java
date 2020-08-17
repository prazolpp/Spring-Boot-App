package edu.sjsu.android.swipingsammies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileScreen extends AppCompatActivity
{
    TextView name;
    TextView bio;
    Button editButton;
    String myEmail;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        name = findViewById(R.id.profileName);
        bio = findViewById(R.id.profileBio);
        editButton = findViewById(R.id.editProfileButton);
        backButton = findViewById(R.id.returnToSwipeScreen);

        myEmail = getIntent().getExtras().getString("email");

        getAccountInformation();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreen.this, SwipeScreen.class);
                intent.putExtra("email", myEmail);
                startActivity(intent);
            }
        });
    }

    private void getAccountInformation()
    {
        Match myProfile = RequestHelper.getProfile(myEmail);
        name.setText(myProfile.getName());
        bio.setText(myProfile.getBio());
    }

    private void editProfile()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Profile");

        final View customLayout = getLayoutInflater().inflate(R.layout.edit_profile_layout, null);
        builder.setView(customLayout);

        builder.setPositiveButton("Save!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editName = findViewById(R.id.editName);
                EditText editBio = findViewById(R.id.editBio);
                RequestHelper.changeProfile(myEmail, editName.toString(), editBio.toString());
                getAccountInformation();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editName = findViewById(R.id.editName);
                EditText editBio = findViewById(R.id.editBio);

                editName.setText("");
                editBio.setText("");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
