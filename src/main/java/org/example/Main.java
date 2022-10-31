package org.example;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;


public class Main {
    public static void main(String[] args) {
        new LwjglApplication(new Screen(),new Config());
    }
}