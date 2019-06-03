package com.inveitix.a06_internet.data.remote;

public class WeatherModel {

    Coordinates coord;
    MainData main;



    @Override
    public String toString() {
        return "WeatherModel{" +
                "coord=" + coord.toString() +
                ", main=" + main.toString() +
                '}';
    }
}
