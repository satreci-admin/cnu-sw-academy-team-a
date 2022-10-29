package com.example.simplerpa.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToShFile {

    public void toFile(int statementId, String contents){
        try {
            File file = new File("./SimpleRpa/src/main/resources/file/" + statementId + ".sh");


            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(contents);
            bw.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
