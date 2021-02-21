package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
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
    private TextView txtMessage;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
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
        txtClue  = findViewById(R.id.txtClue);
        txtMessage = findViewById(R.id.txtMessage);
    }

    private void submitLetter(){
        Log.d(TAG, "submitLetter: method started");

        // getting the generated image to
        final Image car = ApplicationUtils.randomImageGenerator((ArrayList<Image>) cars);
        // setting the generated image to image view
        imgCar.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(car.getImgName(),"mipmap",getApplicationContext())
        ));
        // setting the clue for each generated image
        clue = "";
        for (int i=0; i<car.getCarMake().length(); i++){
            clue += "-";
        }
        txtClue.setText(clue);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = guess_input.getText().toString();
                // for validation the input - if empty Toast message will popup
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

        boolean check = false;
            for (int i = 0; i < carMake.length(); i++) {
                if (carMake.charAt(i) == letter) {
                    clue = clue.substring(0, i) + letter + clue.substring(i + 1);// modified string
                    check = true;
                    letterCount++;
                }
            }
            txtClue.setText(clue); // updating the clue
        if (!check){
            // if the input wasn't match, then wrongGuess increased by 1
            wrongGuess++;
        }


        // if the guess correct
        if (letterCount == carMake.length()){
            wrongGuess = 0; // setting the wrong guesses to 0
            letterCount = 0; // setting the letterCount to 0
            // setting message with custom colors
            String answer = ApplicationUtils.multiColorText("CORRECT!","#3EBF9E");
            // display the message
            txtMessage.setText(Html.fromHtml(answer));
            btnSubmit.setText(R.string.next);  // change the button Label to Next
            // when user click on Next button submitLetter method will arise
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // after clicking on Next, label change to submit
                    txtMessage.setText("");
                    btnSubmit.setText(R.string.submit);
                    submitLetter();
                }
            });
        }

        // if the user guess wrong
        if (wrongGuess >= 3){
            wrongGuess = 0; // wrongGuess to 0
            letterCount = 0; // letterCount to 0
            // setting Message with custom colors
            String answer = ApplicationUtils.multiColorText("WRONG!","#FF0000");
            String carModel = ApplicationUtils.multiColorText(carMake, "#F6FF00");
            // display the message
            txtMessage.setText(Html.fromHtml(answer+" "+carModel));
            btnSubmit.setText(R.string.next); // change the label to Next
            // when user click on Next button submitLetter method will arise
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // after clicking on Next, label change to submit
                    btnSubmit.setText(R.string.submit);
                    txtMessage.setText("");
                    submitLetter();
                }
            });
        }
    }
}
