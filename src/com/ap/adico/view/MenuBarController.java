package com.ap.adico.view;

import com.ap.adico.Main;
import com.ap.adico.view.dialog.aProposDeController;
import com.ap.adico.model.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {
    @Override public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML private void close(){
        System.exit(0);
    }

    //TODO: method should be in english (e.g. buildAboutDialog)
    @FXML private void dialogAProposDe(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/com/ap/adico/view/dialog/aProposDe.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage aproposStage = new Stage();
            aproposStage.setTitle("A propos de");
            aproposStage.initModality(Modality.WINDOW_MODAL);
            aproposStage.initOwner(Config.getPrimaryStage());
            Scene scene = new Scene(page);
            aproposStage.setScene(scene);
            //TODO: controller is never used
            aProposDeController controller = loader.getController();

            aproposStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
                if(t.getCode()== KeyCode.ESCAPE)
                {
                    aproposStage.close();
                }
            });

            aproposStage.showAndWait();
        } catch (IOException e) {
            //TODO: proper logging
            e.printStackTrace();
        }
    }
}
