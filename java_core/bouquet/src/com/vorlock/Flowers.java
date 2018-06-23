package com.vorlock;

import java.util.Random;

abstract class Flowers implements Country {

    abstract String getFlower();

    abstract String getCountry();

    abstract int getCost();

    String getColor() {
        String[] color = {"Red", "Yellow", "Blue", "White", "Pink"};
        Random r = new Random();

        return color[r.nextInt(color.length)];
    }

    @Override
    public String toString() {
        return "Flower: " + getFlower() + ". Color: " + getColor() + ". Country: " + getCountry() + ". Price for one: " + getCost() + "$";
    }
}
