package org.example;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix {

    public double[] multipleWithVector(double[] vector, double[][] matrix){
        double[] result = new double[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            double num = 0;
            for (int j = 0; j < matrix[i].length; j++){
                num += vector[j] * matrix[j][i];
            }
            result[i] = (float) num;
        }
        return result;
    }

    public static double[][] projection(){
        return new double[][] {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 1.0, 0.0, 0.0},
                {cos(45), sin(45), 0, 0},
                {0.0, 0.0, 0.0, 1.0}
        };
    }



    public static double[][] rotation(Axis axis, double angle){
                if(axis == Axis.X){
                    return new double[][] {
                            {1.0, 0.0, 0.0, 0.0},
                            {0.0, cos(angle), sin(angle), 0.0},
                            {0.0, -sin(angle), cos(angle), 0.0},
                            {0.0, 0.0, 0.0, 1.0}
                    };
                }

                if (axis == Axis.Y){
                    return new double[][] {
                            {cos(angle), 0.0, -sin(angle), 0.0},
                            {0.0, 1.0, 0.0, 0.0},
                            {sin(angle), 0.0, cos(angle), 0.0},
                            {0.0, 0.0, 0.0, 1.0}
                    };
                }

                if (axis == Axis.Z){
                    return new double[][] {
                            {cos(angle), sin(angle), 0.0, 0.0},
                            {-sin(angle), cos(angle), 0.0, 0.0},
                            {0.0, 0.0, 1.0, 0.0},
                            {0.0, 0.0, 0.0, 1.0}
                    };
                }

        return new double[0][];
    };

    public static double[][] transfer(double x, double y, double z){
        return new double[][] {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 1.0, 0.0, 0.0},
                {0.0, 0.0, 1.0, 0.0},
                {x, y, z, 1.0}
        };
    }

    public static double[][] scaling(double x, double y, double z) {
        return new double[][]{
                {x, 0.0, 0.0, 0.0},
                {0.0, y, 0.0, 0.0},
                {0.0, 0.0, z, 0.0},
                {0.0, 0.0, 0.0, 1.0}
        };
    }
}
