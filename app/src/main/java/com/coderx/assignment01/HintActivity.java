package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HintActivity extends AppCompatActivity {
    private static final String TAG = "HintActivity";
    private Button btnSubmit;
    private ImageView imgCar;
    private EditText guess_input;
    private TextView txtClue;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private boolean next;
    private String clue;
    private int letterCount = 0;
    private int wrongGuess = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        cars = new ArrayList<>();
        cars = ApplicationUtils.settingImages(carsList); // set all Images into the arrayList with their make
        // calling the initializing method
        initViews();

        // calling the submitLetter method for generate Image
        submitLetter();
    }

    /*all views are initialized in here*/
    private void initViews(){
        Log.d(TAG, "initViews: views are initializing");
        btnSubmit = findViewById(R.id.btnSubmit);
        imgCar = findViewById(R.id.imgCar);
        guess_input = findViewById(R.id.guess_input);
        txtClue  =findViewById(R.id.txtClue);
    }

    private void submitLetter(){
        Log.d(TAG, "submitLetter: method started");

        // getting the generated image to
        final Image car = ApplicationUtils.randomImageGenerator((ArrayList<Image>) cars);
        // setting the generated image to image view
        imgCar.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(car.getImgName(),"mipmap",getApplicationContext())
        ));
        // setting the clue
        clue = "";
        for (int i=0; i<car.getCarMake().length(); i++){
            clue += "-";
        }
        txtClue.setText(clue);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = guess_input.getText().toString();
                if (input.equals("")){
                    Toast.makeText(HintActivity.this, "Please enter a Letter", Toast.LENGTH_SHORT).show();
                }else {
                    updateClue(car.getCarMake().toUpperCase());
                }
            }
        });
    }


    private void updateClue(String carMake){
        Log.d(TAG, "updateClue: started");
        String input = guess_input.getText().toString().toUpperCase(); // get the user input
        char letter = input.charAt(0);
        //int wrongGuess = 0;

        boolean check = false;
        //int letterCount = 0;
       // while (letterCount != carMake.length()) {
            for (int i = 0; i < carMake.length(); i++) {
                if (carMake.charAt(i) == letter) {
                    clue = clue.substring(0, i) + letter + clue.substring(i + 1);// modified string
                    //txtClue.setText(clue); // update the clue
                    letterCount++;
                } else {
                    wrongGuess++;
                }
            }
            txtClue.setText(clue); // update the clue
       // }

        if (letterCount == carMake.length()){
            Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
            btnSubmit.setText(R.string.next);
            //submitLetter();
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submitLetter();
                }
            });
        }else{
            // just to test
            System.out.println(letterCount);
        }

        if (wrongGuess == 3){
            Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
            btnSubmit.setText(R.string.next);
            //submitLetter();
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submitLetter();
                }
            });
        }

    }
}
