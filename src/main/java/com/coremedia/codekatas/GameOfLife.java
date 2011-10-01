package com.coremedia.codekatas;

import org.junit.Test;

/**
 * Implementation of Conway's Game of Life as specified by <a href="http://codingdojo.org/cgi-bin/wiki.pl?KataGameOfLife">Coding Dojo</a>.
 */
public class GameOfLife {
  public enum Rule {
    RULE_ONE(0) {
      // "Any live cell with fewer than two live neighbors dies, as if caused by underpopulation."
      public boolean applies(int livingNeighbors) { return (livingNeighbors < 2); };
    };

    private int resultingState;

    //
    /**
     * Constructor.
     *
     * @param resultingCellState the life state of the cell, if the rule can be applied
     */
    Rule(int resultingCellState) {
      if (!(resultingCellState == 0 || resultingCellState == 1)) {
        throw new IllegalArgumentException("Parameter 'resultingCellState' must be '0' or '1'.");
      }

      this.resultingState = resultingCellState;
    }


    /**
     * @param livingNeighbors total number of living cells surrounding the current cell
     * @return TRUE if the rule is valid for the number of 'livingNeighbors', FALSE otherwise
     */
     public abstract boolean applies(int livingNeighbors);

     public int getResultingCellState() { return this.resultingState; };
  }

  // the edge length of the grid in X
  private final int dimX;

  // the edge length of the grid in X
  private final int dimY;

  // the grid of life cells, where an '0' represents a dead cell and a '1' living one
  private int[][] grid;

  public GameOfLife(final int[][] initialGrid) {
    validateGrid(initialGrid);

    dimX = initialGrid.length;
    dimY = initialGrid[0].length;
    grid = copyGrid(initialGrid);
  }

  /**
   * Performs one life cycle iteration according to rules.
   */
  public void iterate() {
    // walk grid and apply the rules on each cell
    for (int xCoord = 0; (xCoord < dimX); ++xCoord) {
      for (int yCoord = 0; (yCoord < dimY); ++yCoord ) {
        int livingNeigbors = countLivingNeighborCells(xCoord, yCoord);

        Rule rule = Rule.RULE_ONE;
        if ( rule.applies(livingNeigbors) ) {
          grid[xCoord][yCoord] = rule.getResultingCellState();
        }
      }
    }
  }

  private int countLivingNeighborCells(final int xCoord, final int yCoord) {
    // count the living neighbor cells of the current cell
    int livingNeigbors = 0;
    // "run around cell"
    for (int xOffset = -1; xOffset<=1; ++xOffset) {
      for (int yOffset = -1; yOffset<=1; ++yOffset) {

        int xIndex = xOffset + xCoord;
        int yIndex = yOffset + yCoord;

        // ignore cells outside the grid
        if (!validGridCoords(xIndex, yIndex)) {
          continue;
        }

        if (grid[xIndex][yIndex] == 1)
        ++livingNeigbors;
      }
    }
    return livingNeigbors;
  }

  private boolean validGridCoords(final int xIndex, final int yIndex) {
    return xIndex >= 0
           && xIndex < dimX
           && yIndex >= 0
           && yIndex < dimY;
  }

  /**
   * @return a copy of the game's grid
   */
  public int[][] getGrid() {
    return copyGrid(grid);
  }

  private int[][] copyGrid(final int[][] srcGrid) {
    int[][] gridCopy = new int[dimX][dimY];

    for (int xCoord = 0; (xCoord < dimX); ++xCoord) {
      System.arraycopy(srcGrid[xCoord], 0, gridCopy[xCoord], 0, dimY);
    }

    return gridCopy;
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
  private static void validateGrid(final int[][] initialGrid) throws IllegalArgumentException {
    final int firstLengthY = initialGrid[0].length;
    for (int[] yCol : initialGrid) {
      if (yCol.length != firstLengthY) {
        throw new IllegalArgumentException("All lines of the grid must have the same length.");
      }
    }
  }
}
