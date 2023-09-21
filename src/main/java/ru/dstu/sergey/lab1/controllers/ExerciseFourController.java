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
    public TextField shagA;
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

    Function cosMinus = new Function("-6", "6", "1", false);
    Function sinXSquare = new Function("-6", "6", "1", false);

    Function cos = new Function("-6", "6", "1", false);

    private XYChart.Series<Number, Number> selectedSeries;

    @FXML
    private void initialize() {
        widthA.setText("1");
        maxA.setText("6");
        minA.setText("-6");
        shagA.setText("0.1");

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

        lineChart.setCreateSymbols(false);
        lineChart.getData().addAll(cosMinus2SinSeries, sinXSquareSeries, cosSeries);
        selectedSeries = cosSeries;
    }

    public void function1Clicked(ActionEvent actionEvent) {
        choiseFunction.setText("y(x) = sin(x ^ 2)");
        minA.setText(sinXSquare.getMin());
        maxA.setText(sinXSquare.getMax());
        widthA.setText(sinXSquare.getWidth());

        shagA.setText(sinXSquare.getShag());

        showButton.setSelected(sinXSquare.isVisible());
        hideButton.setSelected(!sinXSquare.isVisible());
        selectedSeries = sinXSquareSeries;
    }

    public void function2Clicked(ActionEvent actionEvent) {
        choiseFunction.setText("y(x) = cos(x) - 2*sin(x)");
        minA.setText(cosMinus.getMin());
        maxA.setText(cosMinus.getMax());
        widthA.setText(cosMinus.getWidth());

        shagA.setText(cosMinus.getShag());

        showButton.setSelected(cosMinus.isVisible());
        hideButton.setSelected(!cosMinus.isVisible());
        selectedSeries = cosMinus2SinSeries;
    }

    public void function3Clicked(ActionEvent actionEvent) {
        choiseFunction.setText("y(x) = cos(x)");
        minA.setText(cos.getMin());
        maxA.setText(cos.getMax());
        widthA.setText(cos.getWidth());
        shagA.setText(cos.getShag());

        showButton.setSelected(cos.isVisible());
        hideButton.setSelected(!cos.isVisible());
        selectedSeries = cosSeries;
    }

    @FXML
    private void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }


    public void updateVisible(ActionEvent actionEvent) {
        if (selectedSeries.equals(cosMinus2SinSeries)) {
            cosMinus.setVisible(!hideButton.isSelected());
            updateFunction();
        }
        else if(selectedSeries.equals(sinXSquareSeries)) {
            sinXSquare.setVisible(!hideButton.isSelected());
            updateFunction();
        }
        else {
            cos.setVisible((!hideButton.isSelected()));
            updateFunction();
        }

        cosMinus2SinSeries.getNode().setVisible(cosMinus.isVisible());
        sinXSquareSeries.getNode().setVisible(sinXSquare.isVisible());
        cosSeries.getNode().setVisible(cos.isVisible());
    }

    @FXML
    private void updateInf(ActionEvent actionEvent) {
        updateFunction();
    }

    private void updateFunction() {
        double minWidth = Double.parseDouble(minA.getText());
        double maxWidth = Double.parseDouble(maxA.getText());
        double width = Double.parseDouble(widthA.getText());
        double shag = Double.parseDouble(shagA.getText());

        selectedSeries.getData().clear();

        if (selectedSeries.equals(cosMinus2SinSeries)) {

            showButton.setSelected(cosMinus.isVisible());
            cosMinus.setMin(String.valueOf(minWidth));
            cosMinus.setMax(String.valueOf(maxWidth));
            cosMinus.setWidth(String.valueOf(width));
            cosMinus.setShag(String.valueOf(shag));

            //for (double x = minWidth; x <= maxWidth; x += shag)
            for (double x = minWidth; x <= maxWidth; x += shag) {
                double y = Math.cos(x) - 2 * Math.sin(x);
                selectedSeries.getData().add(new XYChart.Data<>(x, y));
            }

            cosMinus2SinSeries.getNode().setStyle("-fx-stroke-width: " + width + "px;");
        }
        else if(selectedSeries.equals(sinXSquareSeries)) {

            showButton.setSelected(sinXSquare.isVisible());
            sinXSquare.setMin(String.valueOf(minWidth));
            sinXSquare.setMax(String.valueOf(maxWidth));
            sinXSquare.setWidth(String.valueOf(width));
            sinXSquare.setShag(String.valueOf(shag));

            for (double x = minWidth; x <= maxWidth; x += shag) {
                double y = Math.sin(x * x);
                selectedSeries.getData().add(new XYChart.Data<>(x, y));
            }

            sinXSquareSeries.getNode().setStyle("-fx-stroke-width: " + width + "px;");
        }
        else {

            showButton.setSelected(cos.isVisible());
            cos.setMin(String.valueOf(minWidth));
            cos.setMax(String.valueOf(maxWidth));
            cos.setWidth(String.valueOf(width));

            cos.setShag(String.valueOf(shag));

            for (double x = minWidth; x <= maxWidth; x += shag) {
                double y = Math.cos(x);
                selectedSeries.getData().add(new XYChart.Data<>(x, y));
            } // Set your desired stroke width
            cosSeries.getNode().setStyle("-fx-stroke-width: " + width + "px;");
        }
    }
}
