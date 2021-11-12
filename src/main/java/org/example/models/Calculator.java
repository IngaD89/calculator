package org.example.models;

public class Calculator {
    private double x;
    private double y;
    private double result;

    public Calculator(double x, double y, double result) {
        this.x = x;
        this.y = y;
        this.result = result;
    }

    public Calculator() {

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
