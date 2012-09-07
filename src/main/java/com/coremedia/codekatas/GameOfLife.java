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

    for (int i = 0; i < numRows; ++i) {
      field[i] = Arrays.copyOf(initialState[i], rowLength);
    }
  }

  public void step() {
    int [][] targetField = new int[field.length][field[0].length];
    // scan the file row by row
    // and set the new state of each cell
    for (int row = 0; row < field.length; row++) {
      for (int col = 0; col < field[row].length; col++) {
        int currentCell = field[row][col];
        int liveNeighbours = countLivingNeighbours(row, col);
        // result
        if (liveNeighbours < 2 && currentCell == 1) {
          // die!
          targetField[row][col] = 0;
        }
      }
    }
    field = targetField;
  }

  private int countLivingNeighbours(int row, int col) {
    int liveNeighbours = 0;
    // max eight neighbours
    // check row by row
    if (row - 1 > 0) {
      // check neighbours in first row
      if (col - 1 > 0 && cellAt(row - 1, col - 1) == 1) {
        liveNeighbours++;
      }
      if (cellAt(row - 1, col) == 1) {
        liveNeighbours++;
      }
      if (col + 1 < field[0].length && cellAt(row - 1, col + 1) == 1) {
        liveNeighbours++;
      }
    }

    // check neighbours in same row

    if (row + 1 < field.length - 1) {
      // check neighbours of row below
       // check neighbours in first row
      if (col - 1 > 0 && cellAt(row + 1, col - 1) == 1) {
        liveNeighbours++;
      }
      if (cellAt(row + 1, col) == 1) {
        liveNeighbours++;
      }
      if (col + 1 < field[0].length && cellAt(row + 1, col + 1) == 1) {
        liveNeighbours++;
      }
    }
    return liveNeighbours;
  }

  public int cellAt(int row, int col) {
    // Argument check ommited: It's a CodeKata!
    return field[row][col];
  }
}
