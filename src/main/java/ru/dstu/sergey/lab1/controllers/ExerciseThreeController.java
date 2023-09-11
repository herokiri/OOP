package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import ru.dstu.sergey.lab1.Main;

import java.awt.*;
import java.util.Objects;

public class ExerciseThreeController {

    public Pane mainPane;
    public TextField widthImg;
    public TextField heightImg;
    public TextField thickness;
    public ChoiceBox type;
    public ColorPicker fillColor;
    public ColorPicker colorContour;
    public ToggleButton squareElement;
    public ToggleButton lineElement;
    public ToggleButton circleElement;

    private static String currentElement;

    @FXML
    private void initialize() {

        squareElement.setGraphic(new Rectangle(24, 24));
        circleElement.setGraphic(new Circle(9));

        Separator separator = new Separator();
        separator.setPrefWidth(100);
        separator.setStyle("-fx-background-color: black;");
        lineElement.setGraphic(separator);


        mainPane.setStyle("-fx-background-color: #c0c0c0;");
        currentElement = "square";
        type.getItems().addAll("Сплошная", "Пунктирная");
        type.setValue("Сплошная");
        
    }

    public void mainPaneClicked(MouseEvent mouseEvent) {
        if (Objects.equals(currentElement, "square")) {

            Rectangle square = new Rectangle(Double.parseDouble(widthImg.getText()),
                    Double.parseDouble(heightImg.getText()));


            square.setStroke(colorContour.getValue());

            type.getValue();
            if (type.getValue() == "Пунктирная") {
                square.getStrokeDashArray().addAll(7.0, 7.0, 7.0, 7.0);
            }

            square.setStrokeWidth((Double.parseDouble(thickness.getText())));

            square.setX(mouseEvent.getX());
            square.setY(mouseEvent.getY());
            square.setFill(fillColor.getValue());

            mainPane.getChildren().addAll((Node) square);
            mainPane.setFocusTraversable(true);
            System.out.println(square);
            System.out.println(currentElement);

        }
//        ImageView newImageView = new ImageView(selectedImage);
//        newImageView.setLayoutX(mouseEvent.getX());
//        newImageView.setLayoutY(mouseEvent.getY());
//        mainPane.getChildren().add(newImageView);
    }

    public void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }

    public void SquareElementButton(ActionEvent actionEvent) {
        currentElement = "square";
    }

    public void LineElementButton(ActionEvent actionEvent) {
        currentElement = "line";
    }

    public void СircleElementButton(ActionEvent actionEvent) {
        currentElement = "circle";
    }
}
