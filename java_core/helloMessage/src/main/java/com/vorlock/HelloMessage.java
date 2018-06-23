package com.vorlock;

import org.apache.log4j.Logger;
import org.joda.time.LocalTime;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloMessage {

    private static final Logger LOG = Logger.getLogger(HelloMessage.class);

    //set hours
    private static final int MORNING_START = 6;
    private static final int DAY_START = 9;
    private static final int EVENING_START = 19;
    private static final int NIGHT_START = 23;

    //set keys in bundle 'message'
    private static final String BM_MORNING = "morning";
    private static final String BM_DAY = "day";
    private static final String BM_EVENING = "evening";
    private static final String BM_NIGHT = "night";

    public static void main(String[] args) {
        String greeting = new HelloMessage().getGreeting(LocalTime.now());
        LOG.info("The current Locale is: " + Locale.getDefault());
        LOG.info("The current local time is: " + LocalTime.now().toString("HH:mm"));
        LOG.info("Greeting message: " + greeting);
    }

    String getGreeting(LocalTime currentTime) {
        checkFolder();

        int currentHour = currentTime.getHourOfDay();
        if (currentHour < MORNING_START) {
            return getMessage(BM_NIGHT);
        } else if (currentHour < DAY_START) {
            return getMessage(BM_MORNING);
        } else if (currentHour < EVENING_START) {
            return getMessage(BM_DAY);
        } else if (currentHour < NIGHT_START) {
            return getMessage(BM_EVENING);
        } else {
            return getMessage(BM_NIGHT);
        }
    }

    private static String getMessage(String partOfDay) {
        ResourceBundle message = ResourceBundle.getBundle("message");
        return message.getString(partOfDay);
    }

    private static void checkFolder() {
        File logs = new File("logs");
        if (logs.exists() && !logs.isDirectory()) {
            logs.delete();
            logs.mkdir();
        } else {
            logs.mkdir();
        }
    }
}