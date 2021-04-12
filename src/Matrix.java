import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

    public static double[][] multiplySquareMatrix(double[][] a, double[][] b) {
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

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for(int i = 0; i < a.length; i++){
            for (int j = 0; j < b[0].length; j++){
                for (int k = 0; k < b[0].length; k++){
                    result[i][j] = a[i][k] * b[k][j];
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

    public static double[][] getUserMatrix(){
        Scanner scanner = new Scanner(System.in);

        String s = scanner.next();
        String[] ss = s.split(",");
        int r = (int) Math.sqrt(ss.length);
        double[][] first = new double[r][r];
        for(int i = 0; i < ss.length; i++){
            first[i / r][i % r] = parseDouble(ss[i]);
        }
        return first;
    }

    public static void startSequence(){
        double[][] arr = getUserMatrix();
        double[][] identityMatrix = getIdentityMatrix(arr.length);
        Scanner scanner = new Scanner(System.in);
        String line = "";
        System.out.println(Arrays.deepToString(identityMatrix));
        System.out.println(Arrays.deepToString(arr));
        while (!(line = scanner.nextLine()).equals("-1")) {
            if (line.length() == 0) continue;
            try {
                applyOperation(identityMatrix, line);
                applyOperation(arr, line);
            }catch (Exception e){
                continue;
            }
            printMatrix(identityMatrix);
            printMatrix(arr);
        }
       printMatrix(multiplySquareMatrix(arr, identityMatrix));
    }

    public static double[][] getIdentityMatrix(int length){
        double[][] result = new double[length][length];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                if (j == i){
                    result[i][j] = 1;
                    continue;
                }
                result[i][j] = 0;
            }
        }
        return result;
    }

    private static double[][] applyOperation(double[][] arr, String operation){
        String[] s = operation.split(" ");
        int affectedRow = (int) parseDouble(s[0].charAt(1) + "") - 1;
        if (s[1].equals("<>")){
            swipeRowsOperation(arr, affectedRow, (int) parseDouble(s[2].charAt(1) + "") - 1);
        }else{// else if (s[1].equals("+=")){
            if (s.length == 3){
                multiplyRowByConstantOperation(arr, affectedRow, parseDouble(s[2]));
            }else{
                if (s[2].matches("[a-zA-Z].*")){
                    multiplyRowByConstantAndAddToAnotherRowOperation(arr, affectedRow, (int) parseDouble(s[2].charAt(1) + "") - 1, 1);
                }else{
                    double constant = parseDouble(s[2]);
                    multiplyRowByConstantAndAddToAnotherRowOperation(arr, affectedRow, (int) parseDouble(s[3].charAt(1) + "") - 1, constant);
                }
            }
        }
        return arr;
    }

    private static double[][] multiplyRowByConstantOperation(double[][] arr, int row, double constant){
        for (int i = 0; i<arr[row].length; i++){
            arr[row][i] *= constant;
        }
        return arr;
    }

    private static double[][] swipeRowsOperation(double[][] arr, int firstRow, int secondRow){
        for (int i = 0; i<arr[firstRow].length; i++){
            double tmp = arr[firstRow][i];
            arr[firstRow][i] = arr[secondRow][i];
            arr[secondRow][i] = tmp;
        }
        return arr;
    }

    private static double[][] multiplyRowByConstantAndAddToAnotherRowOperation(double[][] arr, int firstRow, int secondRow, double constant){
        double[] tmp = Arrays.copyOf(arr[secondRow], arr[firstRow].length);
        for (int i = 0; i<arr[firstRow].length; i++){
            arr[firstRow][i] += tmp[i] * constant;
        }
        return arr;
    }

//    public static double[][] multiplyRowByConstantOperation(double[][] arr, int row, double constant){
//        for (int i = 0; i<arr[row].length; i++){
//            arr[row][i] *= constant;
//        }
//        return arr;
//    }

    private static double parseDouble(String num){
        String[] numArray = num.split("\\.");
        if (numArray.length > 1){
            return num.charAt(0) == '-' ? Integer.parseInt(numArray[0]) + (Integer.parseInt(numArray[1]) / Math.pow(10.0, numArray[1].length())) * -1 :
                    Integer.parseInt(numArray[0]) + (Integer.parseInt(numArray[1]) /  Math.pow(10.0, numArray[1].length()));
        }
        return Integer.parseInt(numArray[0]);
    }

    public static void printMatrix(double[][] matrix){
        for (double[] row: matrix){
            System.out.print("| ");;
            for (double num: row){
                System.out.print(num + " ");
            }
            System.out.println("|");
        }
    }
}

