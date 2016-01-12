package com.company;

import java.io.*;
import java.util.*;

/**
 * Created by Kayla on 12/27/2015.
 */
public class compressionUtil {
    private static Hashtable<Character, String> characterTable;
    private static String codedText = "";
    private static byte[] byteArray;
    public static Node treeRoot;

    public static void compress(String textString) {
        Hashtable textHashTable = createHashtable(textString);
        PriorityQueue<Node> textPriorityQueue = createQueue(textHashTable);
        treeRoot = createTree(textPriorityQueue);
        characterTable = new Hashtable<Character, String>();
        createCharacterKey(treeRoot, "");
        codedText = "";
        coder(textString);
        System.out.println(characterTable);
        byteArray = createByteArray(codedText);
        createFile(byteArray);


    }
    public static Hashtable<Character, Integer> createHashtable(String text) {
        Hashtable<Character, Integer> textHashTable = new Hashtable<Character, Integer>();

        for (char c : text.toCharArray()) {
            if(textHashTable.get(c) != null) {
                Integer temp = textHashTable.get(c);
                textHashTable.put(c, temp + 1 );
            }
            else {
                textHashTable.put(c, new Integer(1));
            }
        }
        return textHashTable;
    }



    public static PriorityQueue<Node> createQueue(Hashtable<Character, Integer> textHashTable) {
        PriorityQueue<Node> compressionQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1,Node o2) {
                return o1.value.compareTo(o2.value);
            }
        });
        Set<Character> keys = textHashTable.keySet();
        Iterator<Character> itr = keys.iterator();
        while(itr.hasNext()) {
            Character c = itr.next();
            Node temp = new Node(c, textHashTable.get(c));
            compressionQueue.add(temp);
        }
        return compressionQueue;
    }
    public static void printQueue(PriorityQueue priorityQueue) {
        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }

    public static Node createTree(PriorityQueue<Node> priorityQueue) {
      Character c = null;
        while(priorityQueue.size() > 1) {
            Node temp1 = priorityQueue.poll();
            Node temp2 = priorityQueue.poll();
            int combinedValue = temp1.getValue() + temp2.getValue();
            Node combinedNode = new Node(c, combinedValue);
            combinedNode.left = temp1;
            combinedNode.right = temp2;
            priorityQueue.add(combinedNode);
        }
        return priorityQueue.poll();
  }
    public static void createCharacterKey(Node node, String bits) {
        if(node.left != null) {
            createCharacterKey(node.left, bits + "0");
        }
        if(node.right != null) {
            createCharacterKey(node.right, bits + "1");
        }
        if(node.key != null) {
            characterTable.put(node.key, bits);
        }

    }

    public static void coder(String textString) {
        for(char c: textString.toCharArray()) {
            codedText += characterTable.get(c);
        }
    }

    public static byte[] createByteArray(String codedText) {
        int byteArrayLength = codedText.length() / 8;
        if(codedText.length() % 8 != 0) {
            byteArrayLength++;
        }
        byte[] byteArray = new byte[byteArrayLength];
        int x = 0;
        byte numberByte;
        try {
            for (int i = 0; i < byteArrayLength; i++) {
                int end = (x + 8) > (codedText.length()) ? (codedText.length()) : x + 8;
                String temp = codedText.substring(x, end);
                int len = temp.length();
                if (len == 8) {
                    numberByte = (byte) Integer.parseInt(temp, 2); //so mode 2
                    byteArray[i] = numberByte;//Byte.parseByte(temp, 2);
                    x += 8;
                } else {
                    int zeros = 8 - len;
                    String zeroString = "";
                    for (int j = 0; j < zeros; j++) {
                        zeroString += "0";
                    }
                    temp += zeroString;
                    numberByte = (byte) Integer.parseInt(temp, 2); //so mode 2
                    byteArray[i] = numberByte;// Byte.parseByte(temp, 2);
                }
            }
        } catch(Exception e) {

        }
        return byteArray;
    }

    public static void createFile (byte[] byteArray) {
        try {

            File file = new File("C:/Programming/compressedFile");

            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream("C://Programming/compressedFile");
            fos.write(byteArray, 0, byteArray.length);
            fos.close();


            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}