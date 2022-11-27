package org.example;

public class Roberts {


    public static Vertex ViewPoint = new Vertex(0, 0, 10000);

    public static boolean IsVisible(Figure figure, Plane plane){
        Matrix matrix = new Matrix();
        double[] equation;
        equation = plane.SurfaceEquation();

        Vertex figureCenter = figure.getCenter();

        if (Multiply(figureCenter, equation) > 0){

            equation[0] *= -1;
            equation[1] *= -1;
            equation[2] *= -1;
            equation[3] *= -1;

        }
        equation = matrix.multipleWithVector(equation, Matrix.projection());
        double scalar = Multiply(ViewPoint, equation);
        return !(scalar < 0);

    }

    public static double Multiply(Vertex v, double[] abcd)
    {
        return v.x * abcd[0] + v.y * abcd[1] + v.z * abcd[2] + abcd[3];
    }
}
