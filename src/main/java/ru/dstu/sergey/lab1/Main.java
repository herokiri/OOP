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
    private static Scene scene5;
    private static Scene scene6;
    private static Scene scene7;
    private static Scene scene8;

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

        Parent page5 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseFour-view.fxml")));
        scene5 = new Scene(page5);

        Parent page6 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseFive-view.fxml")));
        scene6 = new Scene(page6);

        Parent page7 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseSix-view.fxml")));
        scene7 = new Scene(page7);

        Parent page8 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exerciseSeven-view.fxml")));
        scene8 = new Scene(page8);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Лабораторная работа #1");
        primaryStage.show();
    }

    public static void switchToExerciseSeven() {
        primaryStage.setScene(scene8);
        primaryStage.setTitle("Задание #7");
    }

    public static void switchToExerciseSix() {
        primaryStage.setScene(scene7);
        primaryStage.setTitle("Задание #6");
    }

    public static void switchToExerciseFive() {
        primaryStage.setScene(scene6);
        primaryStage.setTitle("Задание #5");
    }
    public static void switchToExerciseFour() {
        primaryStage.setScene(scene5);
        primaryStage.setTitle("Задание #4");
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