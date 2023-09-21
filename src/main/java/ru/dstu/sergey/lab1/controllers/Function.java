package ru.dstu.sergey.lab1.controllers;

public class Function {
    private String min = "-6";
    private String max = "6";
    private String width = "1";
    private String shag = "0.1";
    private boolean isVisible = false;

    public Function(String min, String max, String width, boolean isVisible) {
        this.min = min;
        this.max = max;
        this.width = width;
        this.isVisible = isVisible;
    }


    public String getShag() {
        return shag;
    }

    public void setShag(String shag) {
        this.shag = shag;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
