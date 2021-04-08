import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        double[][] a = new double[][]{{-6, 6},
                {-7, 4}};
        double[][] b = new double[][]{{2, 1},
                {3, 2}};
        double[][] c = new double[][]{{1, 0},
                {4, 1}};
        System.out.println((Matrix.getDeterminant(a)));
        System.out.println(Arrays.deepToString(Matrix.multiply(a, Matrix.getInverse(a))));

    }
}