package com.coremedia.codekatas;

import java.util.Arrays;

/**
 * Implementation of Conway's Game of Life as specified by <a href="http://codingdojo.org/cgi-bin/wiki.pl?KataGameOfLife">Coding Dojo</a>.
 */
public class GameOfLife {
  private int[][] field;

  public GameOfLife(int[][] initialState) {
    int numRows = initialState[0].length;
    int rowLength = numRows; // Aussumption: We have a square field!

    field = new int[numRows][rowLength];

    for (int i=0; i<numRows; ++i) {
      field[i] = Arrays.copyOf(initialState[i], rowLength);
    }
  }

  public int cellAt(int xCoord, int yCoord) {
    // Argument check ommited: It's a CodeKata!
    return field[xCoord][yCoord];
  }
}
