package com.coremedia.codekatas;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.coremedia.codekatas.GameOfLife;

public class GameOfLifeTest {

  private GameOfLife gol;

  private static void assertGridsAreEqual(int[][] expected, int[][] actual) {
    for (int index = 0; index<actual.length; ++index) {
      Assert.assertArrayEquals("Not equal at X coordinate [" + index + "]", expected[index], actual[index]);
    }
  }

  @After
  public void tearDown() {
    gol = null;
  }

  @Test
  public void ruleOne_01() {
    int[][] initial = {{0, 0, 0},
                       {0, 0, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial);
    gol.iterate();
    int[][] actual = gol.getGrid();

    assertGridsAreEqual(expected, actual);
  }

  @Test
  public void ruleOne_02() {
    int[][] initial = {{0, 0, 0},
                       {0, 1, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial);
    gol.iterate();
    int[][] actual = gol.getGrid();

    assertGridsAreEqual(expected, actual);
  }

  @Test
  public void ruleOne_03() {
    int[][] initial = {{0, 0, 0},
                       {0, 1, 1},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial);
    gol.iterate();
    int[][] actual = gol.getGrid();

    assertGridsAreEqual(expected, actual);
  }

  @Test
  public void ruleOne_04() {
    int[][] initial = {{1, 0, 1},
                       {0, 1, 0},
                       {1, 0, 1}};

    int[][] expected = {{0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial);
    gol.iterate();
    int[][] actual = gol.getGrid();

    assertGridsAreEqual(expected, actual);
  }

  /*@Test
  public void ruleTwo_01() {
    int[][] initial = {{0, 0, 0},
                       {0, 1, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 1, 1},
                        {0, 0, 0}};

    gol = new GameOfLife(initial);
    gol.iterate();
    int[][] actual = gol.getGrid();

    assertGridsAreEqual(expected, actual);
  }*/

  //
  // Tests, other than "rule tests"
  //

  @Test(expected = IllegalArgumentException.class)
  public void properConstructionInvalidGrid() {
     int[][] grid = {{0, 0, 0},
                     {0, 0, 0},
                     {0, 0}};
    gol = new GameOfLife(grid);
  }

  @Test
  public void testGetGrid() {
    int[][] initial = {{1, 0, 1},
                       {0, 1, 0},
                       {1, 0, 1}};

    gol = new GameOfLife(initial);
    assertGridsAreEqual(initial, gol.getGrid());
  }

  @Test
  public void testDefensiveCopyingOnConstruction() {
    int[][] initial = {{1, 0, 0},
                       {0, 0, 0},
                       {0, 0, 0}};
    int[][] initialCopy = {{1, 0, 0},
                           {0, 0, 0},
                           {0, 0, 0}};

    gol = new GameOfLife(initial);
    // change a value
    initial[0][0] = 0;
    // should have no effects
    assertGridsAreEqual(initialCopy, gol.getGrid());
  }
}
