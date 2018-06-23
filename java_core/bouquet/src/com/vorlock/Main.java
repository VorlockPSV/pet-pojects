package com.vorlock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        exec();
    }

    private static void exec() {
        int countFlowers = 0;
        System.out.print("Enter the size of the bouquet: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            countFlowers = Integer.parseInt(reader.readLine());
            if (countFlowers > 0) {
                System.out.println("~~~");
            }
        } catch (Exception e) {
            System.out.println("ERROR! " + e.getMessage());
            System.out.println("~~~");
        }

        Flowers flowers;
        Random r = new Random();
        ArrayList<Flowers> flowersArrayList = new ArrayList<>();
        int totalCost = 0;
        for (int i = 0; i < countFlowers; i++) {
            int rand = r.nextInt(4);
            if (rand == 0) {
                flowers = new Rose();
                flowersArrayList.add(flowers);
                totalCost += flowers.getCost();
            } else if (rand == 1) {
                flowers = new Carnation();
                flowersArrayList.add(flowers);
                totalCost += flowers.getCost();
            } else if (rand == 2) {
                flowers = new Tulip();
                flowersArrayList.add(flowers);
                totalCost += flowers.getCost();
            } else if (rand == 3) {
                flowers = new Chrysanthemum();
                flowersArrayList.add(flowers);
                totalCost += flowers.getCost();
            }
        }

        System.out.println("The bouquet consists of: " + countFlowers + " flowers");
        System.out.println();
        for (Flowers f : flowersArrayList) {
            System.out.println(f);
        }

        System.out.println("~~~");
        System.out.println("The total cost of the bouquet: " + totalCost + "$");
    }
}
