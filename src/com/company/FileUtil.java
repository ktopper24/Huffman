package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Kayla on 12/27/2015.
 */
public class FileUtil {

    public static String readIn(String fileName) {
        StringBuilder fullText = new StringBuilder();

        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                fullText.append(strLine);
            }

            br.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        fullText.append('µ');
        return fullText.toString();
    }
}
