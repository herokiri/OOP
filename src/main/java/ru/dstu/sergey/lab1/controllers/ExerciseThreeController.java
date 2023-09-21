package ru.dstu.sergey.lab1.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import ru.dstu.sergey.lab1.Main;
import javafx.scene.shape.Shape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private Shape lastAddedShape;




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

        widthImg.setText("64");
        heightImg.setText("64");
        thickness.setText("1");

        
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

            square.setX(mouseEvent.getX() - (Double.parseDouble(widthImg.getText()) / 2));
            square.setY(mouseEvent.getY() - (Double.parseDouble(heightImg.getText()) / 2));
            square.setFill(fillColor.getValue());

            mainPane.getChildren().addAll((Node) square);
            mainPane.setFocusTraversable(true);
            lastAddedShape = square;

        }
        else if(Objects.equals(currentElement, "circle")) {

            Circle circle = new Circle((Double.parseDouble(widthImg.getText()) + Double.parseDouble(heightImg.getText())) / 4);


            circle.setStroke(colorContour.getValue());

            type.getValue();
            if (type.getValue() == "Пунктирная") {
                circle.getStrokeDashArray().addAll(7.0, 7.0, 7.0, 7.0);
            }

            circle.setStrokeWidth((Double.parseDouble(thickness.getText())));

            circle.setCenterX(mouseEvent.getX());
            circle.setCenterY(mouseEvent.getY());
            circle.setFill(fillColor.getValue());

            mainPane.getChildren().addAll((Node) circle);
            mainPane.setFocusTraversable(true);
            lastAddedShape =  circle;
        }
        else if(Objects.equals(currentElement, "line")) {

            Line line = new Line();


            line.setStroke(colorContour.getValue());

            type.getValue();

            line.setStrokeWidth((Double.parseDouble(thickness.getText())));

            line.setStartX(mouseEvent.getX());
            line.setStartY(mouseEvent.getY());

            line.setEndX(mouseEvent.getX() + 34);
            line.setEndY(mouseEvent.getY() + 34);

            if (type.getValue() == "Пунктирная") {
                line.getStrokeDashArray().addAll(2.0, (Double.parseDouble(thickness.getText()) + 3));
            }

            line.setFill(fillColor.getValue());

            mainPane.getChildren().addAll((Node) line);
            mainPane.setFocusTraversable(true);
            lastAddedShape =  line;
        }

    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        System.out.println(keyCode);

        switch (keyCode) {
            case EQUALS:
                increaseSize();
                break;
            case MINUS:
                decreaseSize();
                break;
            case A:
                moveLeft();
                break;
            case D:
                moveRight();
                break;
            case S:
                moveDown();
                break;
            case W:
                moveUp();
                break;
        }
    }

    public void increaseSize() {
        if (lastAddedShape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) lastAddedShape;
            double currentWidth = rectangle.getWidth();
            double currentHeight = rectangle.getHeight();
            double newWidth = currentWidth * 1.1;
            double newHeight = currentHeight * 1.1;
            rectangle.setWidth(newWidth);
            rectangle.setHeight(newHeight);
        } else if (lastAddedShape instanceof Circle) {
            Circle circle = (Circle) lastAddedShape;
            double currentRadius = circle.getRadius();
            double newRadius = currentRadius * 1.1; // Увеличиваем радиус на 10%
            circle.setRadius(newRadius);
        } else if (lastAddedShape instanceof Line) {
            Line line = (Line) lastAddedShape;
            double currentLength = Math.sqrt(Math.pow(line.getEndX() - line.getStartX(), 2) +
                    Math.pow(line.getEndY() - line.getStartY(), 2));
            double newLength = currentLength * 1.1; // Увеличиваем длину на 10%
            double scale = newLength / currentLength;
            line.setEndX(line.getStartX() + (line.getEndX() - line.getStartX()) * scale);
            line.setEndY(line.getStartY() + (line.getEndY() - line.getStartY()) * scale);
        }
    }

    public void decreaseSize() {
        if (lastAddedShape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) lastAddedShape;
            double currentWidth = rectangle.getWidth();
            double currentHeight = rectangle.getHeight();
            double newWidth = currentWidth / 1.1; // Уменьшаем размер на 10%
            double newHeight = currentHeight / 1.1;
            rectangle.setWidth(newWidth);
            rectangle.setHeight(newHeight);
        } else if (lastAddedShape instanceof Circle) {
            Circle circle = (Circle) lastAddedShape;
            double currentRadius = circle.getRadius();
            double newRadius = currentRadius / 1.1; // Уменьшаем радиус на 10%
            circle.setRadius(newRadius);
        } else  if (lastAddedShape instanceof Line) {
            Line line = (Line) lastAddedShape;
            double currentLength = Math.sqrt(Math.pow(line.getEndX() - line.getStartX(), 2) +
                    Math.pow(line.getEndY() - line.getStartY(), 2));
            double newLength = currentLength / 1.1; // Уменьшаем длину на 10%
            double scale = newLength / currentLength;
            line.setEndX(line.getStartX() + (line.getEndX() - line.getStartX()) * scale);
            line.setEndY(line.getStartY() + (line.getEndY() - line.getStartY()) * scale);
        }
    }

    public void moveLeft() {
        if (lastAddedShape != null) {
            double currentX = lastAddedShape.getLayoutX();
            lastAddedShape.setLayoutX(currentX - 10); // Смещение влево на 10 пикселей
        }
    }

    public void moveRight() {
        if (lastAddedShape != null) {
            double currentX = lastAddedShape.getLayoutX();
            lastAddedShape.setLayoutX(currentX + 10); // Смещение вправо на 10 пикселей
        }
    }

    public void moveUp() {
        if (lastAddedShape != null) {
            double currentY = lastAddedShape.getLayoutY();
            lastAddedShape.setLayoutY(currentY - 10); // Смещение вверх на 10 пикселей
        }
    }

    public void moveDown() {
        if (lastAddedShape != null) {
            double currentY = lastAddedShape.getLayoutY();
            lastAddedShape.setLayoutY(currentY + 10); // Смещение вниз на 10 пикселей
        }
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

    @FXML
    private void handleSaveButtonClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPEG files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().addAll(pngFilter, jpgFilter);

        File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());
        if (file != null) {
            try {
                int width = Integer.parseInt(widthImg.getText());
                int height = Integer.parseInt(heightImg.getText());

                // Создаем снимок содержимого mainPane с указанными размерами
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setTransform(Transform.scale(width / mainPane.getWidth(), height / mainPane.getHeight()));
                WritableImage snapshot = mainPane.snapshot(parameters, null);

                // Определяем формат изображения в зависимости от выбора пользователя
                String extension;
                if (fileChooser.getSelectedExtensionFilter() == pngFilter) {
                    extension = "png";
                } else {
                    extension = "jpg";
                }

                // Сохраняем снимок в файл с выбранным форматом и размерами
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
                ImageIO.write(bufferedImage, extension, file);

                System.out.println("Файл сохранен: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
