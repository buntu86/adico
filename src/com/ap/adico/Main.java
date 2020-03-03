package com.ap.adico;

import com.ap.adico.model.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //TODO: only loader.load() needs to have the try-catch. Suggestion: extract it in a method and handle the exception in that method.
        try {
            //Load border pane
            FXMLLoader loader = new FXMLLoader();
            //TODO: extract String as constant
            loader.setLocation(Main.class.getResource("/com/ap/adico/view/BorderPane.fxml"));
            BorderPane borderPane = loader.load();
            //TODO: ditto
            borderPane.getStylesheets().add(Main.class.getResource("/com/ap/adico/css/textfield.css").toString());

            //Set border to primaryStage
            primaryStage.setTitle("Adico - Jeu  d'apprentissage de mots");
            primaryStage.setScene(new Scene(borderPane, 500, 500));
            primaryStage.show();

            //Set static primaryStage to reuse on aProposDe dialog box
            Config.setPrimaryStage(primaryStage);
        }
        catch (Exception e){
            //TODO: use a proper logger (SLF4J/Log4j)
            System.out.println("main | " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
