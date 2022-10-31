package org.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

enum  Axis { X, Y, Z }
enum Transformation {
    TRANSFER, ROTATION, SCALING
}
public class Screen extends ApplicationAdapter {

    // Для уведичение объекта
    private final int SCALE = 20;

    // Для смещения начала координат из левого нижнего угла в центр экрана
    private final int OFFSET_X = Config.WIDTH_SCREEN / 2;
    private final int OFFSET_Y = Config.HEIGHT_SCREEN / 2;
    private Transformation currentTransformation = Transformation.TRANSFER;
    Figure figure =  new Figure();

    @Override
    public void create() {
        super.create();
        figure.toMatrix();
    }

    @Override
    public void render() {
        super.render();
        handleClicks();
        draw();
    }

    public void draw(){
        Gdx.gl.glClearColor(0, 50, 50, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        ShapeRenderer render = new ShapeRenderer();
        render.setAutoShapeType(true);
        render.begin();


        render.setColor(Color.LIGHT_GRAY);

        render.line(Config.WIDTH_SCREEN / 2, 0f, Config.WIDTH_SCREEN / 2, Config.HEIGHT_SCREEN); //y
        render.line(0f,  Config.HEIGHT_SCREEN / 2, Config.WIDTH_SCREEN,  Config.HEIGHT_SCREEN / 2); //x

        figure.vertexList = figure.to2D();

        Gdx.gl.glLineWidth(2);
        render.setColor(Color.ORANGE);

        figure.edgeList.forEach(edge -> render.line(
                (float) figure.vertexList.get(edge.firstPoint).x *      SCALE +    OFFSET_X,
                (float) figure.vertexList.get(edge.firstPoint).y *      SCALE +    OFFSET_Y,
                (float) figure.vertexList.get(edge.secondPoint).x *     SCALE +    OFFSET_X,
                (float) figure.vertexList.get(edge.secondPoint).y *     SCALE +    OFFSET_Y
        ));

        render.end();
    }

    private void handleClicks(){
        if(currentTransformation == Transformation.TRANSFER){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))    figure.transfer(-0.1, 0.0, 0.0);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))   figure.transfer(0.1, 0.0, 0.0);
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))    figure.transfer(0.0, -0.1, 0.0);
            if (Gdx.input.isKeyPressed(Input.Keys.UP))      figure.transfer(0.0, 0.1, 0.0);
            if (Gdx.input.isKeyPressed(Input.Keys.EQUALS))  figure.transfer(0.0, 0.0, 0.1);
            if (Gdx.input.isKeyPressed(Input.Keys.MINUS))   figure.transfer(0.0, 0.0, -0.1);
        }

        if(currentTransformation == Transformation.ROTATION){
            if (Gdx.input.isKeyPressed(Input.Keys.UP))      figure.rotation(Axis.X, Math.toRadians(1.0));
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))    figure.rotation(Axis.X, Math.toRadians(-1.0));
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))    figure.rotation(Axis.Y, Math.toRadians(1.0));
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))   figure.rotation(Axis.Y, Math.toRadians(-1.0));
            if (Gdx.input.isKeyPressed(Input.Keys.MINUS))   figure.rotation(Axis.Z, Math.toRadians(1.0));
            if (Gdx.input.isKeyPressed(Input.Keys.EQUALS))  figure.rotation(Axis.Z, Math.toRadians(-1.0));
        }

        if(currentTransformation == Transformation.SCALING){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))    figure.scaling(0.9, 1.0, 1.0);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))   figure.scaling(1.1, 1.0, 1.0);
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))    figure.scaling(1.0, 0.9, 1.0);
            if (Gdx.input.isKeyPressed(Input.Keys.UP))      figure.scaling(1.0, 1.1, 1.0);
            if (Gdx.input.isKeyPressed(Input.Keys.MINUS))   figure.scaling(1.0, 1.0, 0.9);
            if (Gdx.input.isKeyPressed(Input.Keys.EQUALS))  figure.scaling(1.0, 1.0, 1.1);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) currentTransformation = Transformation.TRANSFER;
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) currentTransformation = Transformation.ROTATION;
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) currentTransformation = Transformation.SCALING;

    }
}
