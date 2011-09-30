package com.coremedia.codekatas;

import org.junit.Test;

import com.coremedia.codekatas.GameOfLife;

public class GameOfLifeTest {
  @Test(expected = IllegalArgumentException.class)
  public void properConstructionInvalidGrid() {
     int[][] grid = {{0, 0, 0},
                     {0, 0, 0},
                     {0, 0}};
    final GameOfLife gol = new GameOfLife(grid);
  }
}
