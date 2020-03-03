package com.ap.adico.view;

import com.ap.adico.data.Dico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MainVBoxController implements Initializable {

    //TODO: if you can, put those fields as private for a better encapsulation
    @FXML Label lb_titre1;
    @FXML Button bt_next;
    @FXML Button bt_prev;
    @FXML HBox hbox;
    @FXML ImageView image;

    private final Dico dico = new Dico();
    private int indexActuel = 0;
    //TODO: you have forgotten the <> at the ArrayList
    private final List<TextField> listTf = new ArrayList();
    private final ImageView imageView = new ImageView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newWord();

        //TODO: In English ;) (Buttons -> Buttons)
        //Boutons
        //TODO: would it be better a method hasNext/hasPrevious?
        if(dico.size()>1) {
            bt_next.setDisable(false);
            bt_prev.setDisable(false);
        }
        else {
            bt_next.setDisable(true);
            bt_prev.setDisable(true);
        }

        bt_next.setDisable(true);
    }

    private void newWord(){
        //Reset label, hbox et listTextField
        lb_titre1.setText(dico.get(indexActuel).toUpperCase());
        hbox.getChildren().clear();
        listTf.clear();

        //Initialise listTextField with dico word selected
        for(int i=0; i<dico.get(indexActuel).length();i++)
        {
            //TODO: j is never used
            int j=i+1;
            TextField tf = new TextField();
            tf.setEditable(false);
            tf.setId(String.valueOf(UUID.randomUUID()));
            tf.setPrefWidth(25);

            tf.setOnKeyPressed(ke -> {
                if(ke.getCode().equals(KeyCode.BACK_SPACE))
                    goBack(tf);
                else if(ke.getCode().isLetterKey() | ke.getText().equals("é") | ke.getText().equals("è") | ke.getText().equals("à"))
                    goToNext(tf, ke);
                else if(ke.getCode().equals(KeyCode.ENTER))
                {
                    String tempString = "";
                    for(TextField tempTF:listTf)
                        tempString += tempTF.getText();

                    if(tempString.toUpperCase().equals(dico.get(indexActuel).toUpperCase()))
                        next();
                }
                //TODO proper logging
                else
                    System.out.println("Erreur touche ----> " + ke.getText());
            });

            tf.setPrefColumnCount(1);
            listTf.add(tf);
        }
        hbox.getChildren().addAll(listTf);
        imgErreur();
        hbox.getChildren().add(imageView);

        //Add image from actual word
        Image imgTemp = null; //TODO: you can remove the '=null';
        try {
            imgTemp = new Image(this.getClass().getResource("/files/images/z_aucun.png").openStream());
            image.setImage(imgTemp);
            imgTemp = new Image(this.getClass().getResource("/files/images/" + dico.get(indexActuel) + ".png").openStream());
            image.setImage(imgTemp);
        } catch (Exception e) {
            //TODO: proper logging
            System.out.println("image ok introuvable:" + e.getMessage());
        }

        //Select first textField on list
        listTf.get(0).requestFocus();
    }

    private void goToNext(TextField tf, KeyEvent event){
        int i = listTf.indexOf(tf);

        listTf.get(i).clear();
        listTf.get(i).setText(event.getText().toUpperCase());
        if(event.getText().charAt(0) == dico.get(indexActuel).charAt(i)){
            tf.getStyleClass().remove("error");
            tf.getStyleClass().add("correct");
        }

        else{
            tf.getStyleClass().remove("correct");
            tf.getStyleClass().add("error");
        }

        if(listTf.size()>++i){
            listTf.get(i).requestFocus();
            listTf.get(i).clear();
        }

        //Check if correct
        String tempString = "";
        for(TextField tempTF:listTf)
            tempString += tempTF.getText();

        if(tempString.toUpperCase().equals(dico.get(indexActuel).toUpperCase()))
            imgOk();
        else
            imgErreur();
    }

    private void goBack(TextField tf){
        int i = listTf.indexOf(tf);
        imgErreur();

        if(tf.getText().length()>=1)
            tf.clear();

        else{
            if(--i>=0)
            {
                listTf.get(i).requestFocus();
                listTf.get(i).clear();
            }
        }
    }


    //TODO: the convention says that a method name should have a verb, followed by a name.
    private void imgOk(){
        Image imgOk = null;
        try {
            imgOk = new Image(this.getClass().getResource("/files/icons/icon-ok.png").openStream());
        } catch (Exception e) {
            //TODO: proper logging and in english: image cannot be found
            System.out.println("image ok introuvable:" + e.getMessage());
        }

        if(imgOk!=null){
            imageView.setImage(imgOk);
        }

        bt_next.setDisable(false);
    }

    //TODO: the convention says that a method name should have a verb, followed by a name.
    private void imgErreur(){
        Image imgErreur = null;
        try {
            imgErreur = new Image(this.getClass().getResource("/files/icons/icon-faux.png").openStream());
        } catch (Exception e) {
            System.out.println("image faux introuvable:" + e.getMessage());
        }

        if(imgErreur!=null){
            imageView.setImage(imgErreur);
        }
        bt_next.setDisable(true);
    }

    //TODO: the convention says that a method name should have a verb, followed by a name.
    @FXML private void next(){
        if(dico.size()<=++indexActuel)
            indexActuel=0;
        newWord();
    }

    //TODO: the convention says that a method name should have a verb, followed by a name.
    @FXML private void prev(){
        if(indexActuel==0)
            indexActuel=dico.size()-1;
        else
            indexActuel--;
        newWord();
    }
}
