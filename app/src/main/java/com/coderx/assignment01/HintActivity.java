package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class HintActivity extends AppCompatActivity {
    private Button btnSubmit;
    private EditText guess_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
    }

    private void initViews(){
        btnSubmit = findViewById(R.id.btnSubmit);
        guess_input = findViewById(R.id.guess_input);
    }
}
