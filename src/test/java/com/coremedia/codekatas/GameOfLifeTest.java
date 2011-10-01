package com.coremedia.codekatas;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.coremedia.codekatas.GameOfLife;

public class GameOfLifeTest {

  private GameOfLife gol;

  @After
  public void tearDown() {
    gol = null;
  }

  @Test(expected = IllegalArgumentException.class)
  public void properConstructionInvalidGrid() {
     int[][] grid = {{0, 0, 0},
                     {0, 0, 0},
                     {0, 0}};
    gol = new GameOfLife(grid);
  }

  @Test
  public void ruleOne_01() {
    int[][] initial = {{0, 0, 0},
                       {0, 1, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial);
    gol.iterate();
    int[][] actual = gol.getGrid();

    for (int index = 0; index<actual.length; ++index) {
      Assert.assertArrayEquals("Not equal at line index [" +index+ "]", expected[index], actual[index]);
    }
  }
}
