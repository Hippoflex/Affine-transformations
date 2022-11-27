package org.example;

public class Plane {
    int firstPoint;
    int secondPoint;
    int thirdPoint;
    int fourthPoint;

    Figure figure = new Figure();


    public Plane(int firstPoint, int secondPoint, int thirdPoint, int fourthPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
        this.fourthPoint = fourthPoint;
    }

    public double[] SurfaceEquation(){

        double[] abcd = new double[4];

        Vertex point1 = figure.getVertexList().get(0);
        Vertex point2 = figure.getVertexList().get(1);
        Vertex point3 = figure.getVertexList().get(2);

        double a1 = point2.x - point1.x;
        double b1 = point2.y - point1.y;
        double c1 = point2.z - point1.z;

        double a2 = point3.x - point1.x;
        double b2 = point3.y - point1.y;
        double c2 = point3.z - point1.z;

        double a = b1 * c2 - b2 * c1;
        double b = a2 * c1 - a1 * c2;
        double c = a1 * b2 - b1 * a2;
        double d = (-a * point1.x - b * point1.y - c * point1.z);

        abcd[0] = a;
        abcd[1] = b;
        abcd[2] = c;
        abcd[3] = d;

        return abcd;
    }
}
