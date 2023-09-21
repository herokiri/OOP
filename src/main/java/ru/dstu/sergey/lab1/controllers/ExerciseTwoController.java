package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import ru.dstu.sergey.lab1.Main;

public class ExerciseTwoController {
    @FXML
    private Pane pane;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Circle circle;

    @FXML
    private Ellipse ellipse;

    @FXML
    private Line line;

    private Shape currentShape;
    private Rectangle border;

    @FXML
    private void initialize() {
        currentShape = getRandomShape();
        border = createBorder();
        pane.getChildren().addAll((Node) currentShape, border);
        pane.setFocusTraversable(true);
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        System.out.println(keyCode);

        switch (keyCode) {
            case W:
                currentShape.moveUp();
                break;
            case S:
                currentShape.moveDown();
                break;
            case A:
                currentShape.moveLeft();
                break;
            case D:
                currentShape.moveRight();
                break;
            case EQUALS:
                currentShape.increaseSize();
                break;
            case MINUS:
                currentShape.decreaseSize();
                break;
            default:
                return;
        }

        updateBorder();
    }

    private Shape getRandomShape() {
        Shape[] allShapes = {new RectangleController(), new CircleController(), new EllipseController(), new LineController()};
        int randomIndex = (int) (Math.random() * allShapes.length);
        return allShapes[randomIndex];
    }

    private Rectangle createBorder() {
        Rectangle border = new Rectangle();
        border.setFill(null);
        border.setStroke(Color.BLACK);
        border.getStrokeDashArray().addAll(5d, 5d);
        border.setStrokeWidth(1);
        return border;
    }

    private void updateBorder() {
        double shapeMinX = 0;
        double shapeMinY = 0;
        double shapeMaxX = 0;
        double shapeMaxY = 0;

        if (currentShape instanceof Rectangle) {
            shapeMinX = ((Rectangle) currentShape).getX();
            shapeMinY = ((Rectangle) currentShape).getY();
            shapeMaxX = shapeMinX + ((Rectangle) currentShape).getWidth();
            shapeMaxY = shapeMinY + ((Rectangle) currentShape).getHeight();
        } else if (currentShape instanceof Circle) {
            double radius = ((Circle) currentShape).getRadius();
            shapeMinX = ((Circle) currentShape).getCenterX() - radius;
            shapeMinY = ((Circle) currentShape).getCenterY() - radius;
            shapeMaxX = ((Circle) currentShape).getCenterX() + radius;
            shapeMaxY = ((Circle) currentShape).getCenterY() + radius;
        } else if (currentShape instanceof Ellipse) {
            double radiusX = ((Ellipse) currentShape).getRadiusX();
            double radiusY = ((Ellipse) currentShape).getRadiusY();
            shapeMinX = ((Ellipse) currentShape).getCenterX() - radiusX;
            shapeMinY = ((Ellipse) currentShape).getCenterY() - radiusY;
            shapeMaxX = ((Ellipse) currentShape).getCenterX() + radiusX;
            shapeMaxY = ((Ellipse) currentShape).getCenterY() + radiusY;
        } else if (currentShape instanceof Line) {
            shapeMinX = Math.min(((Line) currentShape).getStartX(), ((Line) currentShape).getEndX());
            shapeMinY = Math.min(((Line) currentShape).getStartY(), ((Line) currentShape).getEndY());
            shapeMaxX = Math.max(((Line) currentShape).getStartX(), ((Line) currentShape).getEndX());
            shapeMaxY = Math.max(((Line) currentShape).getStartY(), ((Line) currentShape).getEndY());
        }

        double centerX = (shapeMaxX + shapeMinX) / 2;
        double centerY = (shapeMaxY + shapeMinY) / 2;
        double width = shapeMaxX - shapeMinX;
        double height = shapeMaxY - shapeMinY;

        border.setX(centerX - (width / 2));
        border.setY(centerY - (height / 2));
        border.setWidth(width);
        border.setHeight(height);
        border.setOpacity(0.5);
    }

    public void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }

    private interface Shape {
        void moveUp();

        void moveDown();

        void moveLeft();

        void moveRight();

        void increaseSize();

        void decreaseSize();
    }

    private class RectangleController extends Rectangle implements Shape {
        public RectangleController() {
            setX(100);
            setY(100);
            setWidth(100);
            setHeight(100);
            setFill(Color.BLUE);
        }

        @Override
        public void moveUp() {
            setY(getY() - 10);
        }

        @Override
        public void moveDown() {
            setY(getY() + 10);
        }

        @Override
        public void moveLeft() {
            setX(getX() - 10);
        }

        @Override
        public void moveRight() {
            setX(getX() + 10);
        }

        @Override
        public void increaseSize() {
            setHeight(getHeight() + 10);
        }

        @Override
        public void decreaseSize() {
            if (getHeight() >= 10) {
                setHeight(getHeight() - 10);
            }
        }
    }

    private class CircleController extends Circle implements Shape {
        public CircleController() {
            setCenterX(200);
            setCenterY(200);
            setRadius(50);
            setFill(Color.RED);
        }

        @Override
        public void moveUp() {
            setCenterY(getCenterY() - 10);
        }

        @Override
        public void moveDown() {
            setCenterY(getCenterY() + 10);
        }

        @Override
        public void moveLeft() {
            setCenterX(getCenterX() - 10);
        }

        @Override
        public void moveRight() {
            setCenterX(getCenterX() + 10);
        }

        @Override
        public void increaseSize() {
            setRadius(getRadius() + 10);
        }

        @Override
        public void decreaseSize() {
            if (getRadius() >= 10) {
                setRadius(getRadius() - 10);
            }
        }
    }

    private class EllipseController extends Ellipse implements Shape {
        public EllipseController() {
            setCenterX(300);
            setCenterY(300);
            setRadiusX(50);
            setRadiusY(30);
            setFill(Color.GREEN);
        }

        @Override
        public void moveUp() {
            setCenterY(getCenterY() - 10);
        }

        @Override
        public void moveDown() {
            setCenterY(getCenterY() + 10);
        }

        @Override
        public void moveLeft() {
            setCenterX(getCenterX() - 10);
        }

        @Override
        public void moveRight() {
            setCenterX(getCenterX() + 10);
        }

        @Override
        public void increaseSize() {
            setRadiusX(getRadiusX() + 10);
            setRadiusY(getRadiusY() + 10);
        }

        @Override
        public void decreaseSize() {
            if (getRadiusX() >= 10 && getRadiusY() >= 10) {
                setRadiusX(getRadiusX() - 10);
                setRadiusY(getRadiusY() - 10);
            }
        }
    }

    private class LineController extends Line implements Shape {
        public LineController() {
            setStartX(100);
            setStartY(100);
            setEndX(200);
            setEndY(200);
            setStroke(Color.PURPLE);
            setStrokeWidth(5);
        }

        @Override
        public void moveUp() {
            setStartY(getStartY() - 10);
            setEndY(getEndY() - 10);
        }

        @Override
        public void moveDown() {
            setStartY(getStartY() + 10);
            setEndY(getEndY() + 10);
        }

        @Override
        public void moveLeft() {
            setStartX(getStartX() - 10);
            setEndX(getEndX() - 10);
        }

        @Override
        public void moveRight() {
            setStartX(getStartX() + 10);
            setEndX(getEndX() + 10);
        }

        @Override
        public void increaseSize() {
            setStartX(getStartX() - 10);
            setStartY(getStartY() - 10);
            setEndX(getEndX() + 10);
            setEndY(getEndY() + 10);
        }

        @Override
        public void decreaseSize() {
            setStartX(getStartX() + 10);
            setStartY(getStartY() + 10);
            setEndX(getEndX() - 10);
            setEndY(getEndY() - 10);
        }
    }
}