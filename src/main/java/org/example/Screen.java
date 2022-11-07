package org.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

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

    private double currentX = 10.0;
    private double currentY = 10.0;
    private double currentZ = 9.0;
    private double currentRotationY = 1.0;

    private double transitionSpeed = 0.01;
    private double rotationSpeed = 2;

    private boolean anim = false;

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

        if (anim) {
            if (currentY > -10) {
                currentX -= 0.02;
                currentZ -= 0.02;
                currentY -= transitionSpeed * (10 - currentX);

                //currentRotationY += rotationSpeed * currentX
                currentRotationY += rotationSpeed * currentX / 5;
            }
            else { resetAnim(); }

            figure.reset();
            figure.transfer(currentX, currentY, currentZ);
            figure.rotation(Axis.Y, Math.toRadians(currentRotationY));
        }

        List<Vertex> vertex = new ArrayList<>();
        vertex = figure.to2D();
        render.setAutoShapeType(true);
        render.begin();


        render.setColor(Color.LIGHT_GRAY);

        render.line(Config.WIDTH_SCREEN / 2, 0f, Config.WIDTH_SCREEN / 2, Config.HEIGHT_SCREEN); //y
        render.line(0f,  Config.HEIGHT_SCREEN / 2, Config.WIDTH_SCREEN,  Config.HEIGHT_SCREEN / 2); //x



        Gdx.gl.glLineWidth(2);
        render.setColor(Color.ORANGE);

        List<Vertex> finalVertex = vertex;
        figure.edgeList.forEach(edge -> render.line(
                (float) finalVertex.get(edge.firstPoint).x *      SCALE +    OFFSET_X,
                (float) finalVertex.get(edge.firstPoint).y *      SCALE +    OFFSET_Y,
                (float) finalVertex.get(edge.secondPoint).x *     SCALE +    OFFSET_X,
                (float) finalVertex.get(edge.secondPoint).y *     SCALE +    OFFSET_Y
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            resetAnim();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) figure.reset();
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) anim = !anim;

    }

    private void resetAnim() {
        anim = true;
        currentX = 10.0;
        currentY = 10.0;
        currentZ = 10.0;
        currentRotationY = 1.0;
    }
}
