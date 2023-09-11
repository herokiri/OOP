package ru.dstu.sergey.lab1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.dstu.sergey.lab1.controllers.HelloController;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage primaryStage;
    private static Scene scene1;
    private static Scene scene2;
    private static Scene scene3;
    private static Scene scene4;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;

        // Загружаем fxml файлы для каждой сцены
        Parent page1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        scene1 = new Scene(page1);

        Parent page2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseOne-view.fxml")));
        scene2 = new Scene(page2);

        Parent page3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseTwo-view.fxml")));
        scene3 = new Scene(page3);

        Parent page4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseThree-view.fxml")));
        scene4 = new Scene(page4);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Лабораторная работа #1");
        primaryStage.show();
    }

    public static void switchToExerciseThree() {
        primaryStage.setScene(scene4);
        primaryStage.setTitle("Задание #3");
    }

    public static void switchToExerciseTwo() {
        primaryStage.setScene(scene3);
        primaryStage.setTitle("Задание #2");
    }
    public static void switchToExerciseOne() {
        primaryStage.setScene(scene2);
        primaryStage.setTitle("Задание #1");
    }

    public static void switchToMain() {
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Лабораторная работа #1");
    }
}