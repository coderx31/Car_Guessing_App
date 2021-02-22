package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyImageActivity extends AppCompatActivity {
    private static final String TAG = "IdentifyImageActivity";
    private ImageView  imgCar1, imgCar2, imgCar3;
    private Button btnNext;
    private TextView txtName, txtMessage;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_image);

        // initialing the cars arrayList add data
        cars = new ArrayList<>();
        cars = ApplicationUtils.settingImages(carsList);

        /*calling the initViews method*/
        initViews();

        /*calling the image selection method*/
        imageSelection();
    }

    /*Initializing all views*/
    private void initViews(){
        Log.d(TAG, "initViews: initializing all views");
        imgCar1 = findViewById(R.id.imgCar1);
        imgCar2 = findViewById(R.id.imgCar2);
        imgCar3 = findViewById(R.id.imgCar3);
        btnNext = findViewById(R.id.btnNext);
        txtName = findViewById(R.id.txtName);
        txtMessage = findViewById(R.id.txtMessage);
    }


    private void imageSelection(){
        Log.d(TAG, "imageSelection: method started");
        isClicked = false; // set the isClicked false

        // clearing the previous message
        txtMessage.setText("");
        Image[] images;// initializing an array for get random images
        images = ApplicationUtils.advancedRandomImageGenerator((ArrayList<Image>) cars); // set the random images to images array

        // generating a random number 0-3 for select a carMake
        Random random = new Random();
        int answer = random.nextInt(3);
        final String carMake = images[answer].getCarMake();

        // update the textName regarding the carMake
        txtName.setText(carMake);


        // setting random images to image views
        imgCar1.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(images[0].getImgName(),"mipmap", getApplicationContext())
        ));
        imgCar2.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(images[1].getImgName(),"mipmap", getApplicationContext())
        ));
        imgCar3.setImageDrawable(getResources().getDrawable(
                ApplicationUtils.getResourceId(images[2].getImgName(),"mipmap", getApplicationContext())
        ));


        // setting the action onClick
        final Image[] finalImages = images;
        imgCar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalImages[0].getCarMake().equals(carMake) && !isClicked){
                    String message = ApplicationUtils.multiColorText("CORRECT!","#3EBF9E");
                    txtMessage.setText(Html.fromHtml(message));
                    isClicked = true; // if button clicked then is isClicked to true, therefore if user clicked again message won't change
                }else if(!isClicked){
                    String message = ApplicationUtils.multiColorText("WRONG!","#FF0000");
                    txtMessage.setText(Html.fromHtml(message));
                    isClicked = true; // same thing here for User Click
                }
            }
        });


        imgCar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalImages[1].getCarMake().equals(carMake) && !isClicked){
                    String message = ApplicationUtils.multiColorText("CORRECT!","#3EBF9E");
                    txtMessage.setText(Html.fromHtml(message));
                    isClicked = true;
                }else if (!isClicked){
                    String message = ApplicationUtils.multiColorText("WRONG!","#FF0000");
                    txtMessage.setText(Html.fromHtml(message));
                    isClicked = true;
                }
            }
        });

        imgCar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalImages[2].getCarMake().equals(carMake) && !isClicked){
                    String message = ApplicationUtils.multiColorText("CORRECT!","#3EBF9E");
                    txtMessage.setText(Html.fromHtml(message));
                    isClicked = true;
                }else if (!isClicked){
                    String message = ApplicationUtils.multiColorText("WRONG!","#FF0000");
                    txtMessage.setText(Html.fromHtml(message));
                    isClicked = true;
                }
            }
        });





        // onClick listener for Next button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate if user did not select any image
                if (!isClicked){
                    Toast.makeText(IdentifyImageActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }else{
                    imageSelection();
                }

            }
        });

    }


}
