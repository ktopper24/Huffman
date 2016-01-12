package com.company;

/**
 * Created by Kayla on 12/27/2015.
 */
public class Node {
    Character key = null;
    Integer value = null;
    Node left = null;
    Node right = null;

    public Node(Character key, Integer value) {
        this.key = key;
        this.value = value;
    }
    public int getValue(){
        return value;
    }

    public String toString(){
        return this.key + ":" + this.value;
    }
}

