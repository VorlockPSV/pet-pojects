# HelloMessage

Travis CI

[![Build Status](https://travis-ci.org/VorlockPSV/pet-pojects.svg?branch=master)](https://travis-ci.org/VorlockPSV/pet-pojects)

Задача:

Написать консольное приложение на java, с использованием maven. Приложение должно выводить на экран приветственное сообщение, в зависимости от текущего времени суток.

Good morning, World! в 06:00 - 09:00

Good day, World! в 09:00 - 19:00

Good evening, World! в 19:00 - 23:00

Good night, World! в 23:00 - 06:00

Код должен содержать все возможные юнит-тесты и создавать читабельный лог файл. Выводящиеся на консоль сообщения должны быть получены из message resource и зависеть от системной локали пользователя. Сделать перевод на русский язык.	

В README должен присутствовать статус сборки от Travis CI или Circle CI.

Аннотация:

сборка - Maven;

логирование - Log4J;

работа со временем - joda-time;

тестирование - TestNG;

обработка локализации - native2ascii;

статус сборки - Travis CI (сейчас CI не трекается т.к. не умеет работать с проектом в подпапке)
