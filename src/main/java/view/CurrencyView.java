package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import controller.CurrencyController;
import model.Currency;

import javax.swing.*;
import java.util.Objects;

public class CurrencyView extends Application {

    // initialize components that need to be accessed from multiple methods
    private final TextField input = new TextField();
    private final TextField result = new TextField();
    private final ComboBox<Currency> source = new ComboBox<>();
    private final ComboBox<Currency> target= new ComboBox<>();
    private final Button convert = new Button("Convert");
    private final Label label = new Label();
    private final Label resulUnitLabel = new Label();
    private final Label guide = new Label();
    public  Label error =  new Label();

    

    //create data
    @Override
    public void init() {
        CurrencyController currency = new CurrencyController(this);
    }
    //create interface
    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        guide.setText("To use the converter: Enter an amount, choose the currencies, and press Convert.");
        guide.setWrapText(true);
        guide.setMaxWidth(300);
        grid.add(guide, 0, 0,2,1);
        guide.setId("guide");
        guide.getStyleClass().add("guide");


        grid.add(new Label("Amount:"), 0, 1);
        grid.add(input, 1, 1);

        grid.add(new Label("From Currency:"), 0, 2);
        grid.add(source, 1, 2);

        grid.add(new Label("To Currency:"), 0, 3);
        grid.add(target, 1, 3);

        grid.add(convert, 1, 4);

        grid.add(new Label("Result:"), 0, 5);
        grid.add(result, 1, 5);
        result.setEditable(false);
        grid.add(resulUnitLabel, 2, 5);
        resulUnitLabel.getStyleClass().add("result-label");
        //display errors
        error.setWrapText(true);
        error.setMaxWidth(300);
        grid.add(error, 0, 6, 2, 1);
        error.setId("error");
        error.getStyleClass().add("error");

        Scene scene = new Scene(grid, 450, 350);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        stage.setTitle("CURRENCY CONVERTER");
        stage.setScene(scene);
        stage.show();
    }

    // getter
    public Label getError() {
        return error;
    }
    public TextField getInput() {
        return input;
    }

    public TextField getResult() {
        return result;
    }

    public ComboBox<Currency> getSource() {
        return source;
    }

    public ComboBox<Currency> getTarget() {
        return target;
    }

    public Button getConvert() {
        return convert;
    }

    public Label getLabel() {
        return label;
    }

    public Label getResultUnitLabel() {
        return resulUnitLabel;
    }
}