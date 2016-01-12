package com.company;

import java.io.IOException;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        String textString = FileUtil.readIn("C://Programming/sampleText.txt");
        compressionUtil.compress(textString);
        decompressUtil.translate("C://Programming/compressedFile");
    }
}
