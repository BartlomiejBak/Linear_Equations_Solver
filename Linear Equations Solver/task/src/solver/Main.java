package solver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(args[1]);
        Scanner scanner = new Scanner(file);

        Matrix matrix = new Matrix();
        matrix.fillMatrix(scanner);
        scanner.close();

        matrix.solveMatrix();

        File file2 = new File(args[3]);
        writeSolution(matrix.printSolution(), file2);
    }

    public static void writeSolution (double[] solution, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (double v : solution) {
            writer.write(v + "\n");
            System.out.println(v);
        }
        writer.close();
    }
}
