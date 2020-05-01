package solver;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Matrix {
    Row[] matrix;

    public Matrix() {
    }
    
    //Method converting text into matrix of doubles
    public void fillMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());
        matrix = new Row[size];

        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            String[] row = line.split(" ");
            matrix[i] = new Row(matrix.length + 1);
            for (int j = 0; j < row.length; j++) {
                matrix[i].row[j] = Double.parseDouble(row[j]);
                matrix[i].setFirstNonZero();
            }
        }
    }

    public void print() {
        for (Row row : matrix) {
            System.out.println(row.toString());
        }
        System.out.println();
    }

    //sorting rows of matrix by number of zeros in front of row
    public void sort() {
        Arrays.sort(matrix, Comparator.comparingInt(row -> row.firstNonZero));
    }

    //converting matrix into triangular form
    public void setTriangular() {
        this.sort();
        for (int i = 0; i < matrix.length; i++) {
            int columnToClear = matrix[i].firstNonZero;
            clearDownColumn(columnToClear, i);
            this.sort();
        }
    }

    //reduces the values of the rows below
    public void clearDownColumn(int columnNumber, int rowNumber) {
        matrix[rowNumber].divideRow(matrix[rowNumber].row[columnNumber]);
        for (int i = rowNumber + 1; i < matrix.length; i++) {
            if (matrix[rowNumber].firstNonZero == matrix[i].firstNonZero) {
                matrix[i].addRow(matrix[rowNumber], -1 * matrix[i].row[columnNumber]);
            } else {
                break;
            }
        }
    }

    //reduces the values of the rows above
    public void clearUpColumn(int columnNumber, int rowNumber) {
        for (int i = rowNumber - 1; i >= 0; i--) {
            matrix[i].addRow(matrix[rowNumber], -1 * matrix[i].row[columnNumber]);
        }
    }

    //converting triangular matrix into row echelon form
    public void setRowEchelon() {
        for (int i = matrix.length - 1; i > 0; i--) {
            clearUpColumn(matrix[i].firstNonZero, i);
        }
    }

    public void solveMatrix() {
        setTriangular();
        setRowEchelon();
    }

    public double[] printSolution() {
        int rows = matrix.length;
        int columns = matrix[0].row.length;
        double[] solution = new double[rows];
        if (rows + 1 == columns) {
            for (int i = 0; i < rows; i++) {
                solution[i] = matrix[i].row[columns - 1];
            }
        }
        return solution;
    }
}
