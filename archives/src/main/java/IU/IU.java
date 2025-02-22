package IU;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import treeExecution.AST;
import treeExecution.ClearNodeTree;

//interfaz del lenguaje con referencia de jupyter notebook

public class IU extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label title = new Label("Escriba el codigo aqui: ");
        title.setStyle("-fx-font-size: 20px; color-fx-lightgray; -fx-font-family: arial");

        Button aggLine = new Button("Agregar nueva linea!");
        Button execute = new Button("Ejecutar Linea");

        TextField entry = new TextField();
        entry.setPadding(new Insets(15));

        Label lineNumber = new Label("1");
        lineNumber.setPadding(new Insets(0, 0, 0, 100));

        // funcionality
        aggLine.setOnAction(e -> {

        });

        execute.setOnAction(e -> {
            if (!entry.getText().isEmpty()) {
                String command = entry.getText();
                // entry.setDisable(true);
                AST.filterSintax(command);

            } else {
                System.out.println("No ha escrito una instruccion!");
            }

        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        GridPane.setColumnSpan(entry, 2);

        grid.add(title, 0, 0);
        grid.add(aggLine, 1, 0);
        grid.add(execute, 2, 0);
        grid.add(entry, 1, 1);
        grid.add(lineNumber, 0, 1);

        Scene scene = new Scene(grid, 600, 400);

        primaryStage.setOnCloseRequest(event -> {
            ClearNodeTree.clearNodeTree();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("BR7SPANISH - Languaje");
        primaryStage.show();
    }
}
