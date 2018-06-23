package com.vorlock;

class Tulip extends Flowers {

    @Override
    String getColor() {
        return super.getColor();
    }

    @Override
    int getCost() {
        return 3;
    }

    @Override
    String getFlower() {
        return "Tulip";
    }

    @Override
    String getCountry() {
        return BELARUS;
    }
}
