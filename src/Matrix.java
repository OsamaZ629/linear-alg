import java.util.Arrays;

public class Matrix {

    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i<a.length;i++){
            for (int j = 0; j<a[0].length;j++){
                for (int k = 0; k<a[0].length; k++){
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] add(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[0].length; j++){
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static double[][] subtract(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[0].length; j++){
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public static double getDeterminant(double[][] a) {
        if (a.length == 2){
            return (a[0][0] * a[1][1]) - (a[0][1] * a[1][0]);
        }else if (a.length == 3){
//            int det = 0;
//            int len = a.length;
//            for (int i = 0; i < a[0].length; i++){
//                int [][] tmp = new int[2][2];
//                for (int j = 0; j < 4; j++){
//                    tmp[j / 2][j % 2] = a[(j / 2) + 1][j % 2];
//                }
//                System.out.println(Arrays.toString(tmp[0]));
//                System.out.println(Arrays.toString(tmp[1]));
//                det += matrixDet(tmp);
//            }
//            return det;
            return (a[0][0] * getDeterminant(new double[][] {{a[1][1], a[1][2]},
                    {a[2][1], a[2][2]}})) -
                    (a[0][1] * getDeterminant(new double[][] {{a[1][0], a[1][2]},
                            {a[2][0], a[2][2]}})) +
                    (a[0][2] * getDeterminant(new double[][] {{a[1][0], a[1][1]},
                            {a[2][0], a[2][1]}}));
        }
        return -1;
    }
    public static double[][] getInverse(double[][] mat){
        double det = getDeterminant(mat);
        if (det == 0) return null;
        double detInverse = 1 / det;
        double[][] new_arr = Arrays.copyOf(mat, 2);
        if (mat.length == 2){
            double tmp = new_arr[0][0];
            new_arr[0][0] = new_arr[1][1];
            new_arr[1][1] = tmp;
            new_arr[1][0] *= -1;
            new_arr[0][1] *= -1;
            for(int i = 0; i < mat.length; i++){
                for (int j = 0; j < mat[0].length; j++){
                    new_arr[i][j] = new_arr[i][j] * detInverse;
                }
            }
        }
        return new_arr;
    }
}

