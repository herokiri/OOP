package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.dstu.sergey.lab1.Main;

import java.io.IOException;

public class HelloController {

    public void firstPage(ActionEvent actionEvent) throws IOException {
        Main.switchToExerciseOne();
    }

    public void secondPage(ActionEvent actionEvent) {
        Main.switchToExerciseTwo();
    }

    public void thirdPage(ActionEvent actionEvent) {
        Main.switchToExerciseThree();
    }

    public void fourthPage(ActionEvent actionEvent) {
        Main.switchToExerciseFour();
    }

    public void fifthPage(ActionEvent actionEvent) {
    }
}