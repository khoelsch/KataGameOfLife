package com.coremedia.codekatas;

import org.junit.Test;

/**
 * Implementation of Conway's Game of Life as specified by <a href="http://codingdojo.org/cgi-bin/wiki.pl?KataGameOfLife">Coding Dojo</a>.
 */
public class GameOfLife {
  // the edge length of the grid in X
  private int dimX;

  // the edge length of the grid in X
  private int dimY;


  public GameOfLife(int[][] initialGrid) {
    validateGrid(initialGrid);

    dimX = initialGrid.length;
    dimY = initialGrid[0].length;
  }

  /**
   * any edge length in X is legal
   *
   * length of first Array dictates the edge length in Y
   *
   * @param initialGrid
   *
   * @throws IllegalArgumentException
   */
  private static void validateGrid(int[][] initialGrid) throws IllegalArgumentException {
    final int firstLengthY = initialGrid[0].length;
    for (int[] yCol : initialGrid) {
      if (yCol.length != firstLengthY) {
        throw new IllegalArgumentException("All lines of the grid must have the same length!");
      }
    }
  }
}
