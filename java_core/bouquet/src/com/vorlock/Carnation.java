package com.vorlock;

class Carnation extends Flowers {

    @Override
    String getColor() {
        return super.getColor();
    }

    @Override
    int getCost() {
        return 4;
    }

    @Override
    String getFlower() {
        return "Carnation";
    }

    @Override
    String getCountry() {
        return Country.UKRAINE;
    }
}
