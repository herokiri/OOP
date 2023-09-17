package ru.dstu.sergey.lab1.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;



public class ProgrammingLanguage {
    private SimpleStringProperty language;
    private SimpleStringProperty author;
    private SimpleIntegerProperty year;

    public ProgrammingLanguage(String language, String author, int year) {
        this.language = new SimpleStringProperty(language);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleIntegerProperty(year);
    }

    public String getDisplayString() {
        return language.get() + " - " + author.get() + " (" + year.get() + ")";
    }

    public String getLanguage() {
        return language.get();
    }

    public SimpleStringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }
    @Override
    public String toString() {
        return "ProgrammingLanguage{" +
                "language='" + language.get() + '\'' +
                ", author='" + author.get() + '\'' +
                ", year=" + year.get() +
                '}';
    }
}
