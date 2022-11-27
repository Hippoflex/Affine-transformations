package org.example;

import javax.swing.*;
import java.util.*;

public class Figure {

    Matrix matrix = new Matrix();

    List<Vertex> vertexList = Arrays.asList(
            new Vertex(3, 3, 0),
            new Vertex(5, 4, 2),
            new Vertex(9, 4, 2),
            new Vertex(7, 3, 0),
            new Vertex(3, 7, 0),
            new Vertex(5, 8, 2),
            new Vertex(9, 8, 2),
            new Vertex(7, 7, 0)
    );

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    List<Edge> edgeList = Arrays.asList(
            new Edge(0, 1),
            new Edge(1, 2),
            new Edge(2, 3),
            new Edge(3, 0),
            new Edge(4, 5),
            new Edge(5, 6),
            new Edge(6, 7),
            new Edge(7, 4),
            new Edge(0, 4),
            new Edge(1, 5),
            new Edge(2, 6),
            new Edge(3, 7)
    );

    List<Plane> planeList = Arrays.asList(
            new Plane(0, 1, 2, 3),
            new Plane(4, 5, 6, 7),
            new Plane(0, 1, 5, 4),
            new Plane(2, 3, 7, 6),
            new Plane(1, 2, 6, 5),
            new Plane(0, 3, 7, 4)
    );

    public List<Plane> getPlaneList() {
        return planeList;
    }

    List<double[]> currentPoints = new ArrayList<>();

    public void toMatrix(){
        List<double[]> points = new ArrayList<>();

        vertexList.forEach(vertex ->
                points.add(new double[]{vertex.x, vertex.y, vertex.z, 1.0f}));
        currentPoints = points;
    }

    public void reset() {
        toMatrix();
    }

    public List<Vertex> to2D(){
        List<Vertex> projection = new ArrayList<>();
        double [] res;
        double c;

        for (double[] points: currentPoints) {
            res = points;
            c = res[3];
            res = matrix.multipleWithVector(res, Matrix.projection());

        projection.add(new Vertex(res[0]/c, res[1]/c, res[2]/c));
        }

        return projection;
    }

    public void transfer(double x, double y, double z){
        List<double[]> newPoints = new ArrayList<>();
        double [] res;

        for (double[] points: currentPoints){
            res = matrix.multipleWithVector(points, Matrix.transfer(x, y, z));
            newPoints.add(res);
        }
        currentPoints = newPoints;
    }


    public void rotation(Axis axis, double toRadians) {
        List<double[]> newPoints = new ArrayList<>();
        double [] res;

        for (double[] points: currentPoints){
            res = matrix.multipleWithVector(points, Matrix.rotation(axis, toRadians));
            newPoints.add(res);
        }
        currentPoints = newPoints;
    }

    public void scaling(double x, double y, double z) {
        List<double[]> newPoints = new ArrayList<>();
        double [] res;

        for (double[] points: currentPoints){
            res = matrix.multipleWithVector(points, Matrix.scaling(x, y, z));
            newPoints.add(res);
        }
        currentPoints = newPoints;
    }

    public Vertex getCenter(){
        double x = 0;
        double y = 0;
        double z = 0;

        for (int i = 0; i< vertexList.size(); i++){
            x += vertexList.get(i).x;
            y += vertexList.get(i).y;
            z += vertexList.get(i).z;
        }

        return new Vertex(x / vertexList.size(), y / vertexList.size(), z / vertexList.size());
    }

}
