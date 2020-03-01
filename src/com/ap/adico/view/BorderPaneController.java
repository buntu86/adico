package com.ap.adico.view;

import com.ap.adico.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BorderPaneController implements Initializable {

    //TODO: make it private if you can
    @FXML AnchorPane centerPane;
    @FXML AnchorPane topPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //Load and add MenuBar
            FXMLLoader menuLoader = new FXMLLoader();
            menuLoader.setLocation(Main.class.getResource("/com/ap/adico/view/MenuBar.fxml"));
            MenuBar menuBar = menuLoader.load();
            this.topPane.getChildren().add(menuBar);
                //Fit border right of menu to anchorpane
                AnchorPane.setRightAnchor(menuBar, 0.0);

            //Load and add MainVBox
            FXMLLoader vboxLoader = new FXMLLoader();
            vboxLoader.setLocation(Main.class.getResource("/com/ap/adico/view/MainVBox.fxml"));
            VBox mainVBox = vboxLoader.load();
            this.centerPane.getChildren().add(mainVBox);
                //Fit mainVBox borders to anchorPane
                AnchorPane.setLeftAnchor(mainVBox, 0.0);
                AnchorPane.setRightAnchor(mainVBox, 0.0);

        } catch (Exception e) {
            //TODO: proper logging
            System.out.println("borderPaneController | " + e.getMessage());
            //TODO: you can extract the exit function in a convenient class.
            System.exit(2);
        }
    }
}

