package ru.dstu.sergey.lab1.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import ru.dstu.sergey.lab1.Main;

public class ExerciseFiveController {

    public TextField language;
    public TextField author;
    public TextField year;

    @FXML
    private TableView<ProgrammingLanguage> tableView;

    @FXML
    private TableColumn<ProgrammingLanguage, String> languageColumn;

    @FXML
    private TableColumn<ProgrammingLanguage, String> authorColumn;

    @FXML
    private TableColumn<ProgrammingLanguage, Integer> yearColumn;

    private ObservableList<ProgrammingLanguage> languages = FXCollections.observableArrayList(
            new ProgrammingLanguage("Си", "Деннис Ритчи", 1972),
            new ProgrammingLanguage("C++", "Бьерн Страуструп", 1983)
            // Add other languages
    );

    @FXML
    public void initialize() {
        // Set cell value factories to display the correct data in each column
        languageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLanguage()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());

        // Allow editing of cells
        tableView.setEditable(true);

        languageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        languageColumn.setOnEditCommit(this::onLanguageEditCommit);

        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setOnEditCommit(this::onAuthorEditCommit);

        yearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yearColumn.setOnEditCommit(this::onYearEditCommit);

        // Set the items for the TableView
        tableView.setItems(languages);
    }


    // Метод для добавления нового языка
    public void addLanguage() {
        languages.add(new ProgrammingLanguage(language.getText(), author.getText(), Integer.parseInt(year.getText())));
    }

    // Method to toggle the visibility of the language column
    public void toggleLanguageColumn(ActionEvent actionEvent) {
        languageColumn.setVisible(((CheckBox) actionEvent.getSource()).isSelected());
    }

    // Method to toggle the visibility of the author column
    public void toggleAuthorColumn(ActionEvent actionEvent) {
        authorColumn.setVisible(((CheckBox) actionEvent.getSource()).isSelected());
    }

    // Method to toggle the visibility of the year column
    public void toggleYearColumn(ActionEvent actionEvent) {
        yearColumn.setVisible(((CheckBox) actionEvent.getSource()).isSelected());
    }

    // Method to handle language cell edit commit
    private void onLanguageEditCommit(TableColumn.CellEditEvent<ProgrammingLanguage, String> event) {
        ProgrammingLanguage language = event.getRowValue();
        language.setLanguage(event.getNewValue());
    }

    private void onAuthorEditCommit(TableColumn.CellEditEvent<ProgrammingLanguage, String> event) {
        ProgrammingLanguage language = event.getRowValue();
        language.setAuthor(event.getNewValue());
    }

    private void onYearEditCommit(TableColumn.CellEditEvent<ProgrammingLanguage, Integer> event) {
        ProgrammingLanguage language = event.getRowValue();
        language.setYear(event.getNewValue());
    }

    public void backButton(ActionEvent actionEvent) {
        Main.switchToMain();
    }
}
