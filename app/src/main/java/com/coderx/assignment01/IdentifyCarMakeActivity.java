package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class IdentifyCarMakeActivity extends AppCompatActivity {
    private static final String TAG = "IdentifyCarMakeActivity";
    private Button btnIdentify;
    private ImageView imgCar;
    private Spinner cars_spinner;
    private TextView message, txtTimer;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private boolean next; // boolean value for Catch the Identify button clicks
    private int count = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);

        // initialize the list to new ArrayList
        cars = new ArrayList<>();
        cars = ApplicationUtils.settingImages(carsList); // set all Images into the arrayList with their make

        /*setting up the timer*/
        timer();

        /*Initializing all the views in activity*/
        initViews();

        /*calling the identify method for generate a random image*/
        identifyCar();

    }

    /*all views are initialized in here*/
    private void initViews(){
        Log.d(TAG, "initViews: init views started");

        btnIdentify = findViewById(R.id.btnIdentify);
        imgCar = findViewById(R.id.imgCar);
        cars_spinner = findViewById(R.id.cars_spinner);
        message = findViewById(R.id.txt_message);
        txtTimer = findViewById(R.id.txtTimer);
    }


    // checking the answer with user selected
    private void checkAnswer(String carMake){
        Log.d(TAG, "checkAnswer: checking the answer");
        String userAnswer = cars_spinner.getSelectedItem().toString();
        if (carMake.equals(userAnswer)){
            // Generating message with specific color
            String check = ApplicationUtils.multiColorText(getString(R.string.correct),"#00FF00");
            // setting the message to text view
            message.setText(Html.fromHtml(check));

        }else{
            // Generating message with specific color
            String check = ApplicationUtils.multiColorText(getString(R.string.wrong), "#FF0000");
            String carModel = ApplicationUtils.multiColorText(carMake, "#ffff00");
            // setting the message to text view
            message.setText(Html.fromHtml(check+" "+carModel));
        }


    }

    private void identifyCar(){
        Log.d(TAG, "identifyCar: Method Started");
        final Image car = ApplicationUtils.randomImageGenerator((ArrayList<Image>) cars);
        imgCar.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(car.getImgName(),"mipmap", getApplicationContext())
        ));

        next = false;
        btnIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!next){
                    btnIdentify.setText(R.string.identify);
                    checkAnswer(car.getCarMake());
                    next = true;
                    btnIdentify.setText(R.string.next);
                    txtTimer.setText("");
                }
                else if(next){
                    btnIdentify.setText(R.string.identify);
                    message.setText("");
                    identifyCar();
                    next = false;
                    txtTimer.setText("");
                }
            }
        });
    }


    /* https://developer.android.com/reference/android/os/CountDownTimer */

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

    /* https://stackoverflow.com/questions/45836646/how-to-automatically-click-a-button-in-android-once-time */
    // handler for autoClick
    private void setAutoClick(){
        Log.d(TAG, "setAutoClick: btnIdentify clicked");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnIdentify.performClick();
            }
        },20000);
    }

}
