package com.coderx.assignment01;

public class Image {
    private String imgName;
    private String carMake;
    private Image cars[];

    public Image(String imgName, String carMake) {
        this.imgName = imgName;
        this.carMake = carMake;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setCars(Image[] cars){
        this.cars = cars;
    }

    public Image[] getCars(){
        return this.cars;
    }
}
