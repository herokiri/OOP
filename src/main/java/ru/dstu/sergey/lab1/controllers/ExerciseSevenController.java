package ru.dstu.sergey.lab1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.dstu.sergey.lab1.Main;

import java.io.File;

public class ExerciseSevenController {

    @FXML
    private MediaView mediaView;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label fileLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label progressLabel;

    private MediaPlayer mediaPlayer;

    public void initialize() {
        volumeSlider.setValue(50);  // Устанавливаем начальное значение громкости
    }

    @FXML
    private void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    private void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("MP4 Files", "*.mp4")
        );

        Stage stage = (Stage) mediaView.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String videoFile = file.toURI().toString();
            fileLabel.setText("File: " + file.getName());

            Media media = new Media(videoFile);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.setOnReady(() -> {
                Duration duration = mediaPlayer.getTotalDuration();
                durationLabel.setText(formatDuration(duration));
            });

            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                progressLabel.setText(formatDuration(newValue));
            });

            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                mediaPlayer.setVolume(newValue.doubleValue() / 100.0);
            });
        }
    }

    private String formatDuration(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }


    public void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }
}
