package org.example.models;

public class Calculator {
    private Long id;
    private double x;
    private double y;
    private double result;

    public Calculator(Long id, double x, double y, double result) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.result = result;
    }

    public Calculator() {

    }

    @Override
    public String toString() {
        return "Calculator{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", result=" + result +
                '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
