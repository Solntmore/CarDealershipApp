package com.lada.carDealershipApp;

public class StaticMethodsAndConstantsForTests {

    public static final String CREATE_CARS = "INSERT INTO cars (brand, model, equipment, price) " +
            "VALUES ('Лада', 'Веста', 'Люкс', 1000000), " +
            "       ('Лада', 'Веста', 'Премиум', 900000), " +
            "       ('Лада', 'Веста', 'Люкс', 1000000), " +
            "       ('Лада', 'Гранта', 'Актив', 650000), " +
            "       ('Лада', 'Икс-рей', 'Люкс', 1100000), " +
            "       ('Лада', 'Гранта', 'Премиум', 800000)";

    public static final String RESET_IDS = "alter table cars alter column id restart with 1";
}
