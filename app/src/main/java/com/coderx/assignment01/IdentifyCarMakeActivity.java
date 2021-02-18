package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyCarMakeActivity extends AppCompatActivity {
    private static final String TAG = "IdentifyCarMakeActivity";
    private Button btnIdentify;
    private ImageView imgCar;
    private Spinner cars_spinner;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();
    private String carMake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);


        cars = new ArrayList<>();
        cars = settingImages(carsList);

        /*Initializing all the views in activity*/
        initViews();

        /*calling randomImageGenerator Method for Testing purposes*/
        randomImageGenerator();
    }

    private void initViews(){
        Log.d(TAG, "initViews: init views started");

        btnIdentify = findViewById(R.id.btnIdentify);
        imgCar = findViewById(R.id.imgCar);
        cars_spinner = findViewById(R.id.cars_spinner);
    }

    private List<Image> settingImages(List<Image> cars){
        Log.d(TAG, "settingImages: setting images started");

        cars.add(new Image("audi01","Audi"));
        cars.add(new Image("audi02","Audi"));
        cars.add(new Image("bmw01", "BMW"));
        cars.add(new Image("bmw02", "BMW"));
        cars.add(new Image("bmw03","BMW"));
        cars.add(new Image("bmw04","BMW"));
        cars.add(new Image("bmw05", "BMW"));
        cars.add(new Image("chevrolet01","Chevrolet"));
        cars.add(new Image("fiat01", "Fiat"));
        cars.add(new Image("fiat02","Fiat"));
        cars.add(new Image("ford01", "Ford"));
        cars.add(new Image("ford02", "Ford"));
        cars.add(new Image("ford03", "Ford"));
        cars.add(new Image("jaguar01", "Jaguar"));
        cars.add(new Image("Jaguar02","Jaguar"));
        cars.add(new Image("lamborghini01", "Lamborghini"));
        cars.add(new Image("lamborghini02", "Lamborghini"));
        cars.add(new Image("mclaren01","McLaren"));
        cars.add(new Image("mercedes01", "Mercedes"));
        cars.add(new Image("mercedes02", "Mercedes"));
        cars.add(new Image("mercedes03", "Mercedes"));
        cars.add(new Image("minicooper01", "Mini Cooper"));
        cars.add(new Image("mitsubishi01", "Mitsubishi"));
        cars.add(new Image("nissan01", "Nissan"));
        cars.add(new Image("nissan02", "Nissan"));
        cars.add(new Image("peugeot01", "Peugeot"));
        cars.add(new Image("porche01", "Porche"));
        cars.add(new Image("suzuki01","Suzuki"));
        cars.add(new Image("toyota01", "Toyota"));
        cars.add(new Image("toyota02", "Toyota"));


        return cars;
    }

    private void randomImageGenerator(){
        Log.d(TAG, "RandomImageGenerator: Image generator started");
        Random random = new Random(); // import for generating random number
        int randomNum = random.nextInt(30); // 30 is the bound for generating random number

        // select car image from the arrayList using the generated random number
        Image carImage = cars.get(randomNum);
        // setting the image to image view
        String resName = carImage.getImgName(); // get the image name and set it to resName for setting to imageView
        imgCar.setImageDrawable(getResources().getDrawable(
                getResourceId(resName, "mipmap", getApplicationContext())
        ));

        //Testing for Button Click
        btnIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IdentifyCarMakeActivity.this, "Just Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*method to get resource id from mipmap file - Here resource is car images*/
    private static int getResourceId(String resName, String resType, Context context){
        int resourceId = context.getResources()
                .getIdentifier(resName, resType, context.getApplicationInfo().packageName);

        // checks if the resource is available or not: if yes return the resource id
        if (resourceId == 0){
            throw new IllegalArgumentException("No string resources found with name "+resName);
        }else{
            return resourceId;
        }
    }

    private void checkAnswer(String carMake){
        Log.d(TAG, "checkAnswer: checking the answer");
        String userAnswer = cars_spinner.getSelectedItem().toString();
        if (carMake.equals(userAnswer)){
            // create a snackbar to display to user  Correct Answer Message
        }else{
            // create a snackbar to display to user Wrong Answer Message
        }


    }
}
