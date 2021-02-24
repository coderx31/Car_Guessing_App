package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
    private TextView txtMessage, txtTimer;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private String clue;
    private int letterCount = 0;
    private int wrongGuess = 0;
    private int count = 20;
    private Image car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        cars = new ArrayList<>();
        cars = ApplicationUtils.settingImages(carsList); // set all Images into the arrayList with their make

        // calling the Timer
        timer();

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
        txtTimer = findViewById(R.id.txtTimer);
    }

    private void submitLetter(){
        Log.d(TAG, "submitLetter: method started");

        // getting the generated image to
        car = ApplicationUtils.randomImageGenerator((ArrayList<Image>) cars);
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
                if (input.equals("") && letterCount == 0){
                  Toast.makeText(HintActivity.this, "Please enter a Letter", Toast.LENGTH_SHORT).show();
               }else {
                  updateClue(car.getCarMake().toUpperCase());
              }
                //updateClue(car.getCarMake().toUpperCase());
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


    private void timer(){
        Log.d(TAG, "Timer: Started");
        // get the boolean value, bundle with intent
        Bundle bundle = getIntent().getExtras();
        boolean isChecked = false;
        if (bundle != null) {
            isChecked = bundle.getBoolean("isChecked");
        }
        // setting up the countdown timer
        if (isChecked){
            new CountDownTimer(21000,1000){

                @Override
                public void onTick(long l) {
                    // if count less than 10, then change the color of text and add a 0
                    if (count < 10){
                        String counter = "0"+count;
                        txtTimer.setText(Html.fromHtml(ApplicationUtils.multiColorText(counter,"#FF0000")));
                    }else{
                        txtTimer.setText(String.valueOf(count));
                    }
                    count--;
                }

                @Override
                public void onFinish() {
                    count = 20; // after the finishing timer, count will set to 20
                   // if user did not suppose to give correct answer, then wrong
                    timerEnd();


                }
            }.start(); // starting the timer

            // after the timer finished button will click automatically
            setAutoClick();
        }
    }

    private void setAutoClick(){
        Log.d(TAG, "setAutoClick: btnIdentify clicked");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSubmit.performClick();
            }
        },20000);
    }


    private void timerEnd(){
        Log.d(TAG, "timerEnd: checks where user input correct or not");
        if (letterCount != car.getCarMake().length()){
            letterCount = 0;
            wrongGuess = 0;
            String answer = ApplicationUtils.multiColorText("WRONG!","#FF0000");
            String carModel = ApplicationUtils.multiColorText(car.getCarMake(), "#F6FF00");
            // display the message
            txtMessage.setText(Html.fromHtml(answer+" "+carModel));
            btnSubmit.setText(R.string.next); // change the label to Next
            txtTimer.setText("");
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
