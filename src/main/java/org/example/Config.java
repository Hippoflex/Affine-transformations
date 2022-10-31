package org.example;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Config extends LwjglApplicationConfiguration {
    static int WIDTH_SCREEN = 1280;
    static int HEIGHT_SCREEN = 860;

    public Config(){
        width = WIDTH_SCREEN;
        height = HEIGHT_SCREEN;
    }

}
