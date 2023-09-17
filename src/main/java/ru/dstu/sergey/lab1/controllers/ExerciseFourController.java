package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import ru.dstu.sergey.lab1.Main;


public class ExerciseFourController {
    public TextField widthA;
    public TextField minA;
    public TextField maxA;
    @FXML
    private ToggleGroup visibilityToggleGroup;
    @FXML
    private RadioButton showButton;

    @FXML
    private RadioButton hideButton;
    public MenuButton choiseFunction;
    @FXML
    private LineChart<Number, Number> lineChart;
    XYChart.Series<Number, Number> cosMinus2SinSeries = new XYChart.Series<>();
    XYChart.Series<Number, Number> sinXSquareSeries = new XYChart.Series<>();
    XYChart.Series<Number, Number> cosSeries = new XYChart.Series<>();

    Function function1 = new Function("-6", "6", "1", false);
    Function function2 = new Function("-6", "6", "1", false);

    Function function3 = new Function("-6", "6", "1", false);

    private XYChart.Series<Number, Number> selectedSeries;

    @FXML
    private void initialize() {
        widthA.setText("1");
        maxA.setText("6");
        minA.setText("-6");

        visibilityToggleGroup = new ToggleGroup();
        showButton.setToggleGroup(visibilityToggleGroup);
        hideButton.setToggleGroup(visibilityToggleGroup);
        hideButton.setSelected(true);

        // Configure the line chart
        lineChart.getXAxis().setLabel("x");
        lineChart.getYAxis().setLabel("y");

        // Create a series for the function y(x) = cos(x) - 2*sin(x)
        cosMinus2SinSeries.setName("y(x) = cos(x) - 2*sin(x)");

        // Create a series for the function y(x) = sin(x^2)
        sinXSquareSeries.setName("y(x) = sin(x^2)");
        // Create a series for the function y(x) = cos(x)
        cosSeries.setName("y(x) = cos(x)");

        // Add data points to the series
        for (double x = -2 * Math.PI; x <= 2 * Math.PI; x += 0.1) {
            double y1 = Math.cos(x) - 2 * Math.sin(x);
            double y2 = Math.sin(x * x);
            double y3 = Math.cos(x);
            cosMinus2SinSeries.getData().add(new XYChart.Data<>(x, y1));
            sinXSquareSeries.getData().add(new XYChart.Data<>(x, y2));
            cosSeries.getData().add(new XYChart.Data<>(x, y3));
        }

        lineChart.setCreateSymbols(false);
        lineChart.getData().addAll(cosMinus2SinSeries, sinXSquareSeries, cosSeries);
        selectedSeries = cosSeries;


    }

    public void function1Clicked(ActionEvent actionEvent) {
        choiseFunction.setText("y(x) = sin(x ^ 2)");
        minA.setText(function1.getMin());
        maxA.setText(function1.getMax());
        widthA.setText(function1.getWidth());
        showButton.setSelected(function1.isVisible());
        selectedSeries = sinXSquareSeries;
    }

    public void function2Clicked(ActionEvent actionEvent) {
        choiseFunction.setText("y(x) = cos(x) - 2*sin(x)");
        minA.setText(function2.getMin());
        maxA.setText(function2.getMax());
        widthA.setText(function2.getWidth());
        showButton.setSelected(function2.isVisible());
        selectedSeries = cosMinus2SinSeries;
    }

    public void function3Clicked(ActionEvent actionEvent) {
        choiseFunction.setText("y(x) = cos(x)");
        minA.setText(function3.getMin());
        maxA.setText(function3.getMax());
        widthA.setText(function3.getWidth());
        showButton.setSelected(function3.isVisible());
        selectedSeries = cosSeries;
    }

    @FXML
    private void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }

    public void updateMinValue(ActionEvent inputMethodEvent) {
        if (selectedSeries.equals(cosSeries)) {
            function3.setMin(minA.getText());
        } else if(selectedSeries.equals(cosMinus2SinSeries)) {
            function2.setMin(minA.getText());
        }
        else if(selectedSeries.equals(sinXSquareSeries)) {
            function1.setMin((minA.getText()));
        }
    }

    public void updateMaxValue(ActionEvent inputMethodEvent) {

        if (selectedSeries.equals(cosSeries)) {
            function3.setMax(maxA.getText());
        } else if (selectedSeries.equals(cosMinus2SinSeries)) {
            function2.setMax(maxA.getText());
        } else if (selectedSeries.equals(sinXSquareSeries)) {
            function1.setMax(maxA.getText());
        }
    }

    public void updateWidthValue(ActionEvent inputMethodEvent) {
        if (selectedSeries.equals(cosSeries)) {
            function3.setWidth(widthA.getText());
        } else if (selectedSeries.equals(cosMinus2SinSeries)) {
            function2.setWidth(widthA.getText());
        } else if (selectedSeries.equals(sinXSquareSeries)) {
            function1.setWidth(widthA.getText());
        }
    }

    public void updateVisible(ActionEvent actionEvent) {
        if (showButton.isVisible()) {
            if (selectedSeries.equals(cosSeries)) {
                System.out.println("cos");
                function3.setVisible(true);
            } else if (selectedSeries.equals(cosMinus2SinSeries)) {
                System.out.println("cosMinus2ssin");
                function2.setVisible(true);
            } else if (selectedSeries.equals(sinXSquareSeries)) {
                System.out.println("sin(x ^ 2)");
                function1.setVisible(true);
            }
        } else {
            if (selectedSeries.equals(cosSeries)) {
                System.out.println("cos");
                function3.setVisible(false);
            } else if (selectedSeries.equals(cosMinus2SinSeries)) {
                function2.setVisible(false);
            } else if (selectedSeries.equals(sinXSquareSeries)) {
                function1.setVisible(false);
            }
        }
    }
}
