package com.vorlock;

class Chrysanthemum extends Flowers {

    @Override
    String getColor() {
        return super.getColor();
    }

    @Override
    int getCost() {
        return 2;
    }

    @Override
    String getFlower() {
        return "Chrysanthemum";
    }

    @Override
    String getCountry() {
        return Country.MOLDOVA;
    }
}
