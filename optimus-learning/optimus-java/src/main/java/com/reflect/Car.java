package com.reflect;

/**
 * Java反射
 * Created by li.huan.
 * Created on 2017/4/22
 */
public class Car {

    private String brand;
    private String color;
    private int maxSpeed;

    public Car() {
    }

    public Car(String brand, String color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public void disPlay() {
        System.out.print("brand:" + brand + "color:" + color + "maxSpeed:" + maxSpeed);
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private Car setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }
}
