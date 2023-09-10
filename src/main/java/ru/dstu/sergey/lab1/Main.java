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

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Лабораторная работа #1");
        primaryStage.show();
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