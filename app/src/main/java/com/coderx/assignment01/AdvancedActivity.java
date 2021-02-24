package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

public class AdvancedActivity extends AppCompatActivity {
    private static final String TAG = "AdvancedActivity";
    private ImageView imgCar1, imgCar2, imgCar3;
    private EditText car1_input, car2_input, car3_input;
    private TextView txtScore, txtAnswer1, txtAnswer2, txtAnswer3, txtMessage, txtTimer;
    private Button btnSubmit;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private boolean check1, check2, check3;
    private int wrongGuess = 0;
    private int score = 0;
    private int count = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        // setting the images to arrayList
        cars = new ArrayList<>();
        cars = ApplicationUtils.settingImages(carsList);

        /* calling the timer */
        timer();
        /*calling the initViews method*/
        initViews();

        /*submitAnswer method calling*/
        submitAnswer();


    }

    private void initViews(){
        Log.d(TAG, "initViews: all views are initialized");
        imgCar1 = findViewById(R.id.imgCar1);
        imgCar2 = findViewById(R.id.imgCar2);
        imgCar3 = findViewById(R.id.imgCar3);
        car1_input = findViewById(R.id.car1_input);
        car2_input = findViewById(R.id.car2_input);
        car3_input = findViewById(R.id.car3_input);
        txtScore = findViewById(R.id.txtScore);
        txtAnswer1 = findViewById(R.id.txtAnswer1);
        txtAnswer2 = findViewById(R.id.txtAnswer2);
        txtAnswer3 = findViewById(R.id.txtAnswer3);
        txtMessage = findViewById(R.id.txtMessage);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtTimer = findViewById(R.id.txtTimer);
    }


    private void submitAnswer(){
        Log.d(TAG, "submitAnswer: method started");
        final Image[] images = ApplicationUtils.advancedRandomImageGenerator((ArrayList<Image>) cars);


        // setting the images to views
        imgCar1.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(images[0].getImgName(),"mipmap",getApplicationContext())
        ));
        imgCar2.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(images[1].getImgName(),"mipmap",getApplicationContext())
        ));
        imgCar3.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(images[2].getImgName(),"mipmap",getApplicationContext())
        ));


        check1 = false;
        check2 = false;
        check3 = false;
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String car1 = car1_input.getText().toString().toLowerCase();
                String car2 = car2_input.getText().toString().toLowerCase();
                String car3 = car3_input.getText().toString().toLowerCase();

                if (car1.equals("") || car2.equals("") || car3.equals("")){
                    Toast.makeText(AdvancedActivity.this, "Please Fill the blanks", Toast.LENGTH_SHORT).show();
                }else{

                    if (car1.equals(images[0].getCarMake().toLowerCase())){
                        car1_input.setEnabled(false);
                        car1_input.setTextColor(Color.GREEN);
                        check1 = true;
                        score++;

                    }else{
                        car1_input.setTextColor(Color.RED);
                    }

                    if (car2.equals(images[1].getCarMake().toLowerCase())){
                        car2_input.setEnabled(false);
                        car2_input.setTextColor(Color.GREEN);
                        check2 = true;
                        score++;
                    }else{
                        car2_input.setTextColor(Color.RED);
                    }


                    if (car3.equals(images[2].getCarMake().toLowerCase())){
                        car3_input.setEnabled(false);
                        car3_input.setTextColor(Color.GREEN);
                        check3  = true;
                        score++;
                    }else{
                        car3_input.setTextColor(Color.RED);
                    }
                }

                if (check1 && check2 && check3){
                    wrongGuess = 0; // set the wrongGuess to 0
                    String message = ApplicationUtils.multiColorText("CORRECT!","#3EBF9E"); // generating the colored Text
                    txtMessage.setText(Html.fromHtml(message)); // setting to message
                    btnSubmit.setText(R.string.next);
                    txtScore.setText(String.valueOf(score));
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clearingFields();
                            submitAnswer();
                            score = 0;
                        }
                    });
                }
                else{
                    wrongGuess++;
                }


                if (wrongGuess >= 3){
                    wrongGuess = 0;
                    String message = ApplicationUtils.multiColorText("WRONG!","#FF0000");
                    txtMessage.setText(Html.fromHtml(message));
                    // setting the correct answer
                    settingCorrectAnswer(images);
                    btnSubmit.setText(R.string.next);
                    txtScore.setText(String.valueOf(score));
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           clearingFields();
                            submitAnswer();
                            score = 0;
                        }
                    });
                }



            }
        });

    }

    private void clearingFields(){
        Log.d(TAG, "clearingFields: clearing fields");
        car1_input.setText("");
        car2_input.setText("");
        car3_input.setText("");

        // enables the editable if disabled
        car1_input.setEnabled(true);
        car2_input.setEnabled(true);
        car3_input.setEnabled(true);

        car1_input.setTextColor(Color.BLACK);
        car2_input.setTextColor(Color.BLACK);
        car3_input.setTextColor(Color.BLACK);

        txtAnswer1.setText("");
        txtAnswer2.setText("");
        txtAnswer3.setText("");

        txtMessage.setText("");
    }


    private void settingCorrectAnswer(Image[] cars){
        Log.d(TAG, "settingCorrectAnswer: setting answers");
        // getting the carMakes to Strings
        String car1 = car1_input.getText().toString().toLowerCase();
        String car2 = car2_input.getText().toString().toLowerCase();
        String car3 = car3_input.getText().toString().toLowerCase();
        String answer;
        // check if user correct or not, if not set the answer
        if (!cars[0].getCarMake().toLowerCase().equals(car1)){
            answer = ApplicationUtils.multiColorText(cars[0].getCarMake(),"#F6FF00");
            txtAnswer1.setText(Html.fromHtml(answer));
        }

        if (!cars[1].getCarMake().toLowerCase().equals(car2)){
            answer = ApplicationUtils.multiColorText(cars[1].getCarMake(),"#F6FF00");
            txtAnswer2.setText(Html.fromHtml(answer));
        }

        if (!cars[2].getCarMake().toLowerCase().equals(car3)){
            answer = ApplicationUtils.multiColorText(cars[2].getCarMake(),"#F6FF00");
            txtAnswer3.setText(Html.fromHtml(answer));
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

                }
            }.start(); // starting the timer

            // after the timer finished button will click automatically
            setAutoClick();
        }
    }

    // handler for autoClick
    private void setAutoClick(){
        Log.d(TAG, "setAutoClick: btnIdentify clicked");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSubmit.performClick();
            }
        },21000);
    }

}
