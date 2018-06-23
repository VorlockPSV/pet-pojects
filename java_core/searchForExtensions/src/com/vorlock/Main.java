package com.vorlock;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        exec();
    }

    private static void exec() {
        Date currentTime = new Date();

        SearchExt se = new SearchExt();
        se.searchExtends();

        Date newTime = new Date();
        long msDelay = newTime.getTime() - currentTime.getTime();
        System.out.println("Elapsed time: " + msDelay + " ms (1 millisecond = 0.001 seconds)");
        se.printExtToFile("Elapsed time: " + msDelay + " ms (1 millisecond = 0.001 seconds)" + "\n");

/*        System.out.println("~~~");
        System.out.println("TEST");
        String DIRNAME = "C:\\TMP";
        String strstr = DIRNAME.substring(DIRNAME.lastIndexOf("/")+1);
        System.out.println(DIRNAME + " " + strstr);
        System.out.println("~~~");*/
    }
}
