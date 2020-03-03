package com.ap.adico.model;

import javafx.stage.Stage;

//TODO: what has 'stage' to do with 'config'? The class name is maybe not well chosen
public class Config {
    private static Stage primaryStage = new Stage();

    public static void setPrimaryStage(Stage stage){
        Config.primaryStage = stage;
    }
    public static Stage getPrimaryStage(){
        return Config.primaryStage;
    }
}
