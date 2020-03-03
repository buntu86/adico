package com.ap.adico.model;

import javafx.stage.Stage;

public class Config {
    private static Stage primaryStage = new Stage();

    public static void setPrimaryStage(Stage stage){
        Config.primaryStage = stage;
    }
    public static Stage getPrimaryStage(){
        return Config.primaryStage;
    }
}
