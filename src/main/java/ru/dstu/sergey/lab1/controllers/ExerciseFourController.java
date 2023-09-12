package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ru.dstu.sergey.lab1.Main;

import java.util.function.Function;

public class ExerciseFourController {

    public void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }
}
