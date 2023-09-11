package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import ru.dstu.sergey.lab1.Main;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

import java.io.*;

public class ExerciseOneController {

    public Pane mainPane;
    public ToggleButton imgOneButton;
    public ToggleButton imgTwoButton;
    private static Image selectedImage;
    public ToggleButton imgThreeButton;



    Image imgOne;
    Image imgTwo;
    Image imgThree;

    @FXML
    public void initialize() throws FileNotFoundException {
        String url = "D:/OOP/lab1/lab1/src/main/resources/ru/dstu/sergey/lab1/img/";
        FileInputStream inputstream1 = new FileInputStream(url + "image1.png");
        FileInputStream inputstream2 = new FileInputStream(url + "image2.png");
        FileInputStream inputstream3 = new FileInputStream(url + "image3.png");

        imgOne = new Image(inputstream1);
        imgTwo = new Image(inputstream2);
        imgThree = new Image(inputstream3);

        selectedImage = imgOne;

        ImageView imageOneView = new ImageView(selectedImage);
        imgOneButton.setGraphic(imageOneView);

        ImageView imgTwoView = new ImageView(imgTwo);
        imgTwoButton.setGraphic(imgTwoView);

        ImageView imgThreeView = new ImageView(imgThree);
        imgThreeButton.setGraphic(imgThreeView);
    }

    public void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }

    public void mainPaneClicked(MouseEvent mouseEvent) {
        ImageView newImageView = new ImageView(selectedImage);
        newImageView.setLayoutX(mouseEvent.getX());
        newImageView.setLayoutY(mouseEvent.getY());
        mainPane.getChildren().add(newImageView);
    }

    public void imgButtonClicked(MouseEvent actionEvent) {
        ToggleButton selectedButton = (ToggleButton) actionEvent.getSource();
        if (selectedButton.isSelected()) {
            if (selectedButton == imgOneButton) {
                selectedImage = imgOne;
            } else if (selectedButton == imgTwoButton) {
                selectedImage = imgTwo;
            } else if (selectedButton == imgThreeButton) {
                selectedImage = imgThree;
            }
        }
    }
    @FXML
    private void handleSaveButtonClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());
        if (file != null) {
            try {
                // Создаем снимок содержимого mainPane
                SnapshotParameters parameters = new SnapshotParameters();
                WritableImage snapshot = mainPane.snapshot(parameters, null);

                // Сохраняем снимок в файл
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);

                System.out.println("Файл сохранен: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}