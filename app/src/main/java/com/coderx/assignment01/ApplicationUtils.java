package com.coderx.assignment01;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApplicationUtils {
    private static final String TAG = "ApplicationUtils";
//    private static int count = 20;
//    private static String strCount;

    // Random Image Generating Method
    public static Image randomImageGenerator(ArrayList<Image> cars){
        Log.d(TAG, "randomImageGenerator: Method created");

        Random random = new Random(); // import for generating random number
        int randomNum = random.nextInt(30); // 30 is the bound for generating random number

        // select car image from the arrayList using the generated random number and
        // return the randomly selected one
        return cars.get(randomNum);


    }

    // getting the resource id for image
    public static int getResourceId(String resName, String resType, Context context){
        Log.d(TAG, "getResourceId: getResourceId method created");
        int resourceId = context.getResources()
                .getIdentifier(resName, resType, context.getApplicationInfo().packageName);

        // checks if the resource is available or not: if yes return the resource id
        if (resourceId == 0){
            throw new IllegalArgumentException("No string resources found with name "+resName);
        }else{
            return resourceId;
        }
    }

    // adding all images with their names to arrayList
    public static List<Image> settingImages(List<Image> cars){
        Log.d(TAG, "settingImages: setting images started");
        /*adding 30 images to cars arrayList*/
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
        cars.add(new Image("jaguar02","Jaguar"));
        cars.add(new Image("lamborghini01", "Lamborghini"));
        cars.add(new Image("lamborghini02", "Lamborghini"));
        cars.add(new Image("mclaren01","McLaren"));
        cars.add(new Image("mercedes01", "Mercedes"));
        cars.add(new Image("mercedes02", "Mercedes"));
        cars.add(new Image("mercedes03", "Mercedes"));
        cars.add(new Image("minicooper01", "MiniCooper"));
        cars.add(new Image("mitsubishi01", "Mitsubishi"));
        cars.add(new Image("nissan01", "Nissan"));
        cars.add(new Image("nissan02", "Nissan"));
        cars.add(new Image("peugeot01", "Peugeot"));
        cars.add(new Image("porche01", "Porche"));
        cars.add(new Image("suzuki01","Suzuki"));
        cars.add(new Image("toyota01", "Toyota"));
        cars.add(new Image("toyota02", "Toyota"));


        return cars; // after adding return the all cars
    }

    // for Color Combination
    /* https://stackoverflow.com/questions/6094315/single-textview-with-multiple-colored-text */
    /* https://abhiandroid.com/ui/html */
    /* https://developer.android.com/reference/android/text/Html */
    public static String multiColorText(String text, String color){
        String combination = "<font color ="+color+">"+text+"</font>";
        return combination;
    }

    // Generate 3 Random Images
    public static Image[] advancedRandomImageGenerator(ArrayList<Image> cars){
        Log.d(TAG, "advancedRandomImageGenerator: method started");
        // generating random numbers
        Random random = new Random();
        int img1 = random.nextInt(10); // generate between 0 - 10, 10 excluded
        int img2 = random.nextInt(20-10)+10; // generate between 10 - 20, 20 excluded
        int img3 = random.nextInt(30-20)+20;  // generate between 20 - 30, 30 excluded

        // create the image array
        Image[] images = new Image[3];
        // adding generated images to images array
       images[0] = cars.get(img1);
       images[1] = cars.get(img2);
       images[2] = cars.get(img3);

       // return the array
        return images;
    }



}
