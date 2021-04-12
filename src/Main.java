import java.util.Arrays;
import java.util.Scanner;

public class Main {
// 1,3,1,2,2,1,2,3,1 -0.25,0.37,-0.50,0.37,0.12,0.50,-1,1.50,2
    public static void main(String[] args) {
        double[][] a = new double[][]{{1, 3, 1},
                {2, 2, 1},
                {2, 3, 1}};
        double[][] b = new double[][]{{1, -6, 7},
                                    {0, 1, -1},
                                      {2, 3, -4}};
        double[][] c = new double[][]{{1, 0},
                {4, 1}};

        Matrix.startSequence();
//        Matrix.printMatrix(Matrix.multiplySquareMatrix(Matrix.getUserMatrix(), Matrix.getUserMatrix()));
        double[][] s = Matrix.getUserMatrix();
        double[][] d = Matrix.getUserMatrix();
        Matrix.printMatrix(s);
        Matrix.printMatrix(d);
        System.out.println("det s: " + Matrix.getDeterminant(s));
        System.out.println("det d: " + Matrix.getDeterminant(d));
        System.out.println("det d + det s: " + (Matrix.getDeterminant(d) + Matrix.getDeterminant(s)));

        for(int i = 0; i < s.length; i++){
             s[2][i] += d[2][i];
        }
        Matrix.printMatrix(s);
        System.out.println("det (d + s): " + Matrix.getDeterminant(s));

    }
}