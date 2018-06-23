package com.vorlock;

class Rose extends Flowers {

    @Override
    String getColor() {
        return super.getColor();
    }

    @Override
    int getCost() {
        return 5;
    }

    @Override
    String getFlower() {
        return "Rose";
    }

    @Override
    String getCountry() {
        return DENMARK;
    }
}
