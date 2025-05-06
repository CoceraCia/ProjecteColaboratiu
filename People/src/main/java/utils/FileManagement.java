/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

/**
 *
 * @author migue
 */
public class FileManagement {
    //Default location where the CSV will be exported
    public static final String DEFAULT_CSV_LOCATION = "csv/";
    public static final String DEFAULT_CSV_NAMEFILE = "people_data_" + LocalDate.now() + ".csv";
    public static final String DEFAULT_CSV_PATH = DEFAULT_CSV_LOCATION + DEFAULT_CSV_NAMEFILE;

    public FileManagement(){
        File file = new File(DEFAULT_CSV_LOCATION);
        file.mkdirs();
    }
    public void fileWriter(String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DEFAULT_CSV_PATH, false))) {
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
