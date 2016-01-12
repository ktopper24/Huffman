package com.company;

import java.io.*;

/**
 * Created by Kayla on 12/30/2015.
 */
public class decompressUtil {


    public static void translate(String file) throws IOException {
        Node tree = compressionUtil.treeRoot;
        Node root = tree;
        FileInputStream in = null;
        FileOutputStream out = null;
        StringBuilder text = new StringBuilder();
        String fullText = "";


        try {
            in = new FileInputStream(file);
            out = new FileOutputStream("C://Programming/decompressedFile.txt");
            int c;

            while ((c = in.read()) != -1) {
                if (Integer.toBinaryString(c).length() == 8) {
                    text.append(Integer.toBinaryString(c));
                }
                else {
                    text.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
                    }
            }
            fullText = text.toString();
            char[] chars = new char[fullText.length()];
            chars = fullText.toCharArray();
            int counter = 0;


            while (counter < text.length()) {

                if (chars[counter] == '0') {
                    tree = tree.left;
                }
                else {
                    tree = tree.right;
                }

                if (tree.key != null) {
                    if (tree.key == 'µ') {
                        break;
                    } else {
                        out.write(tree.key);
                        tree = root;
                    }
                }
                counter++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
