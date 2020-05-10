package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    protected Car() {
    }

    public static Car create(int type, int numberOfPassengers){
        if (type==2){
            return new Cabriolet(numberOfPassengers);
        }
        else if (type==1){
            return new Sedan(numberOfPassengers);
        }
        else if (type==0){
            return new Truck(numberOfPassengers);
        }
        else return null;
    }

    public boolean isSummer(Date date , Date summerStart, Date summerEnd){
        if (date.after(summerStart)&&date.before(summerEnd)){
            return true;
        }
        else {return false;}
    }

    public double getWinterConsumption(int length){
        return length*winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length){
        return length*summerFuelConsumption;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0) throw new Exception();
        fuel += numberOfLiters; }

    private boolean canPassengersBeTransferred(){
        if (fuel>0 && isDriverAvailable()) {
            return true;
        }
        else return false;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date, SummerStart, SummerEnd)) {
            consumption = getSummerConsumption(length);
        } else {
            consumption = getWinterConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred()) return numberOfPassengers;
        return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
}