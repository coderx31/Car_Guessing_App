package com.coderx.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class IdentifyCarMakeActivity extends AppCompatActivity {
    private static final String TAG = "IdentifyCarMakeActivity";
    private Button btnIdentify;
    private ImageView imgCar;
    private List<Image> cars;
    private List<Image> carsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);


        cars = new ArrayList<>();
        cars = settingImages(carsList);

        /*Initializing all the views in activity*/
        initViews();
    }

    private void initViews(){
        Log.d(TAG, "initViews: init views started");

        btnIdentify = findViewById(R.id.btnIdentify);
        imgCar = findViewById(R.id.imgCar);
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
}
