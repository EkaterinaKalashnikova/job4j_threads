package ru.job4j.pool;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RolColSumTest {

  private int[][] counter(int length) {
      int[][] matrix = new int[length][length];
      int count = 1;
      for (int i = 0; i < length; i++) {
          for (int j = 0; j < length; j++) {
              matrix[i][j] = count++;
          }
      }
      return matrix;
  }

    @Test
    public void thenSumMatrix4x4() throws ExecutionException, InterruptedException {
        int[][] matrix = counter(4);
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        RolColSum.Sums[] asyncSums = RolColSum.asyncSum(matrix);
        Assert.assertEquals(sums, asyncSums);
        Assert.assertThat(sums[0].getRowSum(), Is.is(10));
        Assert.assertThat(sums[1].getRowSum(), Is.is(26));
        Assert.assertThat(sums[2].getRowSum(), Is.is(42));
        Assert.assertThat(sums[3].getRowSum(), Is.is(58));
        Assert.assertThat(sums[0].getColSum(), Is.is(28));
        Assert.assertThat(sums[1].getColSum(), Is.is(32));
        Assert.assertThat(sums[2].getColSum(), Is.is(36));
        Assert.assertThat(sums[3].getColSum(), Is.is(40));
    }

    @Test
    public void thenSumMatrix3x3() throws ExecutionException, InterruptedException {
        int[][] matrix = counter(3);
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        RolColSum.Sums[] asyncSums = RolColSum.asyncSum(matrix);
        Assert.assertEquals(sums, asyncSums);
        Assert.assertThat(sums[0].getRowSum(), Is.is(6));
        Assert.assertThat(sums[1].getRowSum(), Is.is(15));
        Assert.assertThat(sums[2].getRowSum(), Is.is(24));
        Assert.assertThat(sums[0].getColSum(), Is.is(12));
        Assert.assertThat(sums[1].getColSum(), Is.is(15));
        Assert.assertThat(sums[2].getColSum(), Is.is(18));
    }

    @Test
    public void thenSumMatrix5x5() throws ExecutionException, InterruptedException {
        int[][] matrix = counter(5);
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        RolColSum.Sums[] asyncSums = RolColSum.asyncSum(matrix);
        Assert.assertEquals(sums, asyncSums);
        Assert.assertThat(sums[0].getRowSum(), Is.is(15));
        Assert.assertThat(sums[1].getRowSum(), Is.is(40));
        Assert.assertThat(sums[2].getRowSum(), Is.is(65));
        Assert.assertThat(sums[3].getRowSum(), Is.is(90));
        Assert.assertThat(sums[4].getRowSum(), Is.is(115));
        Assert.assertThat(sums[0].getColSum(), Is.is(55));
        Assert.assertThat(sums[1].getColSum(), Is.is(60));
        Assert.assertThat(sums[2].getColSum(), Is.is(65));
        Assert.assertThat(sums[3].getColSum(), Is.is(70));
        Assert.assertThat(sums[4].getColSum(), Is.is(75));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = counter(3);
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        RolColSum.Sums[] asyncSums = RolColSum.asyncSum(matrix);
        Assert.assertEquals(sums, asyncSums);
        Assert.assertThat(sums[0].getRowSum(), Is.is(6));
        Assert.assertThat(sums[1].getRowSum(), Is.is(15));
        Assert.assertThat(sums[2].getRowSum(), Is.is(24));
        Assert.assertThat(sums[0].getColSum(), Is.is(12));
        Assert.assertThat(sums[1].getColSum(), Is.is(15));
        Assert.assertThat(sums[2].getColSum(), Is.is(18));
    }
}