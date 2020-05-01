package solver;

import java.util.Arrays;

public class Row {
    double[] row;
    int firstNonZero;

    public Row(int length) {
        this.row = new double[length];
        this.firstNonZero = length;
        this.setFirstNonZero();
    }

    public void addRow(Row firstRow, double multiplier) {
        for (int i = 0; i < this.row.length; i++) {
            this.row[i] += firstRow.row[i] * multiplier;
        }
        this.setFirstNonZero();
    }

    public void multiplyRow(double multiplier) {
        for (int i = 0; i < this.row.length; i++) {
            this.row[i] *= multiplier;
        }
        this.setFirstNonZero();
    }

    public void divideRow(double divider) {
        for (int i = 0; i < this.row.length; i++) {
            this.row[i] /= divider;
        }
        this.setFirstNonZero();
    }

    public void switchWithRow(Row secondRow) {
        double[] tempRow = this.row.clone();
        this.row = secondRow.row.clone();
        secondRow.row = tempRow.clone();

        int tempFirstNonZero = this.firstNonZero;
        this.firstNonZero = secondRow.firstNonZero;
        secondRow.firstNonZero = tempFirstNonZero;
    }

    public void setFirstNonZero() {
        for (int i = 0; i < row.length; i++) {
            this.firstNonZero = row.length;
            if (this.row[i] != 0) {
                this.firstNonZero = i;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(row);
    }

}
