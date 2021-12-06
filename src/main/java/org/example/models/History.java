package org.example.models;

import java.time.LocalDateTime;


public class History {

    private Long id;
    private String operation;
    private double x;
    private double y;
    private double result;
    private LocalDateTime dateTime;

    public History(Long id, String operation, double x, double y, double result, LocalDateTime dateTime) {
        this.id = id;
        this.operation = operation;
        this.x = x;
        this.y = y;
        this.result = result;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
