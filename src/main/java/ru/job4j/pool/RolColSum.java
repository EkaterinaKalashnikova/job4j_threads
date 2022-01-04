package ru.job4j.pool;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[2 * n];
        for (int row = 0; row < matrix.length; row++) {
            int rowSum = 0;
            for (int col = 0; col < matrix[row].length; col++) {
                  rowSum += matrix[row][col];
            }
            sums[rowSum].rowSum = rowSum;
        }

        for (int col = 0; col < matrix[0].length; col++) {
            int colSum = 0;
            for (int row = 0; row < matrix.length; row++) {
                colSum += matrix[row][col];
            }
            sums[colSum].colSum = colSum;
        }

        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[2 * n];
        return sums;
    }
}

/*
1. Дан каркас класса. Ваша задача подсчитать суммы по строкам и
 столбцам квадратной матрицы
        - sums[i].rowSum - сумма элементов по i строке
        - sums[i].colSum  - сумма элементов по i столбцу
2. Реализовать последовательную версию программы
3. Реализовать асинхронную версию программы. i - я задача считает сумму по i столбцу и i строке
4. Написать тесты
 */