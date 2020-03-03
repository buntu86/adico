package com.ap.adico.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dico {
    //TODO: the <> in a ArrayList is missing
    List<String> dico = new ArrayList();

    public Dico(){
        loadDico();
    }

    public void loadDico(){

        String strFile = "/files/dico.csv";
        try{
            //TODO: replace 'UTF8' with StandardCharsets.UTF_8
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(strFile), "UTF8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                dico.add(values[0]);
            }
            if(dico.size()<1)
                dico.add("dictionnaire");
        }
        catch (Exception e){
            //TODO: proper logging and in english ;)
            System.out.println("Dictionnaire introuvable");
            dico.add("dictionnaire");
        }

        Collections.shuffle(dico);
        //TODO: with a proper logging, you could let it as a 'debug' info
        //System.out.println("Nombre de mots : " + dico.size() + "\n" + dico);

    }

    public String get(int i){
        return dico.get(i);
    }

    public int size(){
        return dico.size();
    }
}
