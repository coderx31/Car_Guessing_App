package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "MainActivity";
    private TextView txtName, txtLicence;
    private Switch switch1;
    private boolean isChecked;
    private Button btnIdentifyCarMake, btnHints, btnIdentifyCarImage, btnAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initializing the all views*/
        initViews();

        /*Click listener for each button*/
        btnIdentifyCarMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // state of switch
                switchState();
                Intent intent = new Intent(MainActivity.this, IdentifyCarMakeActivity.class);
                intent.putExtra("isChecked",isChecked);
                startActivity(intent);
            }
        });

        btnHints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // state of switch
                switchState();
                Intent intent = new Intent(MainActivity.this, HintActivity.class);
                // passing the value of isChecked with intent
                intent.putExtra("isChecked",isChecked);
                startActivity(intent);
            }
        });


        btnIdentifyCarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // state of switch
                switchState();
                Intent intent = new Intent(MainActivity.this, IdentifyImageActivity.class);
                intent.putExtra("isChecked",isChecked);
                startActivity(intent);
            }
        });


        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // state of switch
                switchState();
                Intent intent = new Intent(MainActivity.this, AdvancedActivity.class);
                intent.putExtra("isChecked",isChecked);
                startActivity(intent);
            }
        });
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");
        txtName = findViewById(R.id.txtName);
        txtLicence = findViewById(R.id.txtLicence);
        btnIdentifyCarMake = findViewById(R.id.btnIdentifyCarMake);
        btnHints = findViewById(R.id.btnHints);
        btnIdentifyCarImage = findViewById(R.id.btnIdentifyCarImage);
        btnAdvanced = findViewById(R.id.btnAdvanced);
        switch1 = findViewById(R.id.switch1);
    }

    // checking the status of switch and update the isChecked
    private void switchState(){
        Log.d(TAG, "switchState: getting the state of switch");
        isChecked = switch1.isChecked();
        System.out.println(isChecked);
    }
}
