package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class IdentifyCarMakeActivity extends AppCompatActivity {
    private static final String TAG = "IdentifyCarMakeActivity";
    private Button btnIdentify;
    private ImageView imgCar;
    private Spinner cars_spinner;
    private TextView message;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private boolean next; // boolean value for Catch the Identify button clicks


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);

        // initialize the list to new ArrayList
        cars = new ArrayList<>();
        cars = ApplicationUtils.settingImages(carsList); // set all Images into the arrayList with their make

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
    }


    // checking the answer with user selected
    private void checkAnswer(String carMake){
        Log.d(TAG, "checkAnswer: checking the answer");
        String userAnswer = cars_spinner.getSelectedItem().toString();
        if (carMake.equals(userAnswer)){
            // create a snackbar to display to user  Correct Answer Message
            String check = ApplicationUtils.multiColorText(getString(R.string.correct),"#3EBF9E");
            message.setText(Html.fromHtml(check));
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

        }else{
            // create a snackbar to display to user Wrong Answer Message
            String check = ApplicationUtils.multiColorText(getString(R.string.wrong), "#FF0000");
            String carModel = ApplicationUtils.multiColorText(carMake, "#F6FF00");
            message.setText(Html.fromHtml(check+" "+carModel));
            Toast.makeText(this, "Wrong "+carMake, Toast.LENGTH_SHORT).show();
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
                }
                else if(next){
                    btnIdentify.setText(R.string.identify);
                    message.setText("");
                    identifyCar();
                    next = false;
                }
            }
        });
    }
}
