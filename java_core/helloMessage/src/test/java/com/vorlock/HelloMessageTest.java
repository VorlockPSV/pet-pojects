package com.vorlock;

import org.joda.time.LocalTime;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.*;

public class HelloMessageTest {
    HelloMessage helloWorld = new HelloMessage();
    private static Locale localeDefault;

    @BeforeClass
    public static void chengeLocale() {
        localeDefault = Locale.getDefault();
    }

    @AfterClass
    public static void returnLocale() {
        Locale.setDefault(localeDefault);
    }

    @Test(timeOut = 1000)
    public void testLocaleEN() {
        Locale.setDefault(new Locale("en"));
        //base cases
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(8)), "Good morning, World!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(12)), "Good day, World!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(20)), "Good evening, World!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(1)), "Good night, World!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.minusHours(1)), "Good night, World!");

        //**** corner cases *****
        //night -> mornign
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(6).minusMillis(1)), "Good night, World!");
        //mornign -> day
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(9).minusMillis(1)), "Good morning, World!");
        //day -> evening
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(19).minusMillis(1)), "Good day, World!");
        //evening -> night
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(23).minusMillis(1)), "Good evening, World!");

        //mornign
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(6)), "Good morning, World!");
        //day
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(9)), "Good day, World!");
        //evening
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(19)), "Good evening, World!");
        //night
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(23)), "Good night, World!");

    }

    @Test(timeOut = 1000)
    public void testLocaleRU() {
        Locale.setDefault(new Locale("ru"));
        //base cases
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(8)), "Доброе утро, Мир!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(12)), "Добрый день, Мир!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(20)), "Добрый вечер, Мир!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(1)), "Доброй ночи, Мир!");
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.minusHours(1)), "Доброй ночи, Мир!");

        //**** corner cases *****
        //night -> mornign
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(6).minusMillis(1)), "Доброй ночи, Мир!");
        //mornign -> day
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(9).minusMillis(1)), "Доброе утро, Мир!");
        //day -> evening
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(19).minusMillis(1)), "Добрый день, Мир!");
        //evening -> night
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(23).minusMillis(1)), "Добрый вечер, Мир!");

        //mornign
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(6)), "Доброе утро, Мир!");
        //day
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(9)), "Добрый день, Мир!");
        //evening
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(19)), "Добрый вечер, Мир!");
        //night
        checkMessage(helloWorld.getGreeting(LocalTime.MIDNIGHT.plusHours(23)), "Доброй ночи, Мир!");
    }

    private void checkMessage(String actualMessage, String expectedMessage) {
        assertEquals(actualMessage, expectedMessage);
    }
}