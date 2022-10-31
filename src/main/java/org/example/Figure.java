package org.example;

import java.util.*;

public class Figure {

    Matrix matrix = new Matrix();

    List<Vertex> vertexList = Arrays.asList(
            new Vertex(0, 3, 3, 0),
            new Vertex(1, 3, 9, 0),
            new Vertex(2, 9 ,9, 0),
            new Vertex(3, 9, 3, 0),
            new Vertex(4, 7, 3, 0),
            new Vertex(5, 7, 7, 0),
            new Vertex(6, 5, 7, 0),
            new Vertex(7, 5, 3, 0),
            new Vertex(8, 3, 3, 3),
            new Vertex(9, 3, 9, 3),
            new Vertex(10, 9 ,9, 3),
            new Vertex(11, 9, 3, 3),
            new Vertex(12, 7, 3, 3),
            new Vertex(13, 7, 7, 3),
            new Vertex(14, 5, 7, 3),
            new Vertex(15, 5, 3, 3)
    );

    List<Edge> edgeList = Arrays.asList(
            new Edge(0, 1),
            new Edge(0, 7),
            new Edge(1, 2),
            new Edge(1, 9),
            new Edge(2, 3),
            new Edge(2, 10),
            new Edge(3, 4),
            new Edge(3, 11),
            new Edge(4, 5),
            new Edge(4, 12),
            new Edge(5, 6),
            new Edge(5, 13),
            new Edge(6, 7),
            new Edge(6, 14),
            new Edge(7, 0),
            new Edge(7, 15),
            new Edge(8, 9),
            new Edge(9, 10),
            new Edge(10, 11),
            new Edge(11, 12),
            new Edge(12, 13),
            new Edge(13, 14),
            new Edge(14, 15),
            new Edge(8, 15),
            new Edge(0, 8)
    );

    Map<Integer, double[]> currentPoints = new HashMap<>();

    public void toMatrix(){
        Map<Integer, double[]> points = new HashMap<>();

        vertexList.forEach(vertex ->
                points.put(vertex.n, new double[]{vertex.x, vertex.y, vertex.z, 1.0f}));
        currentPoints = points;
    }

    public List<Vertex> to2D(){
        List<Vertex> projection = new ArrayList<>();
        double [] res;
        double c;

        for (var entry: currentPoints.entrySet()) {
            res = entry.getValue();
            c = res[3];
            res = matrix.multipleWithVector(res, Matrix.projection());

        projection.add(new Vertex(entry.getKey(),res[0]/c, res[1]/c, res[2]/c));
        }

        return projection;
    }

    public void transfer(double x, double y, double z){
        Map<Integer, double[]> newPoints = new HashMap<>();
        double [] res;

        for (var entry: currentPoints.entrySet()){
            res = matrix.multipleWithVector(entry.getValue(), Matrix.transfer(x, y, z));
            newPoints.put(entry.getKey(), res);
        }
        currentPoints = newPoints;
    }


    public void rotation(Axis axis, double toRadians) {
        Map<Integer, double[]> newPoints = new HashMap<>();
        double [] res;

        for (var entry: currentPoints.entrySet()){
            res = matrix.multipleWithVector(entry.getValue(), Matrix.rotation(axis, toRadians));
            newPoints.put(entry.getKey(), res);
        }
        currentPoints = newPoints;
    }

    public void scaling(double x, double y, double z) {
        Map<Integer, double[]> newPoints = new HashMap<>();
        double [] res;

        for (var entry: currentPoints.entrySet()){
            res = matrix.multipleWithVector(entry.getValue(), Matrix.scaling(x, y, z));
            newPoints.put(entry.getKey(), res);
        }
        currentPoints = newPoints;
    }
}
