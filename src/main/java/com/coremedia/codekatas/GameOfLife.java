package com.coremedia.codekatas;

import java.util.EnumSet;

/**
 * Implementation of Conway's Game of Life as specified by <a href="http://codingdojo.org/cgi-bin/wiki.pl?KataGameOfLife">Coding Dojo</a>.
 */
public class GameOfLife {
  public enum Rule {
    // "Any live cell with fewer than two live neighbors dies, as if caused by underpopulation."
    RULE_ONE(1, 0) {
      public boolean applies(final int currentCellState, final int livingNeighbors) {
        return currentCellState == applicationCellState
                && livingNeighbors < 2;
      }
    },

    // "Any live cell with more than three live neighbours dies, as if by overcrowding."
    RULE_TWO(1, 0) {
      public boolean applies(final int currentCellState, final int livingNeighbors) {
        return currentCellState == applicationCellState
                && livingNeighbors > 3;
      }
    },

    // "Any live cell with two or three live neighbours lives on to the next generation."
    RULE_THREE(1, 1) {
      public boolean applies(final int currentCellState, int livingNeighbors) {
        return currentCellState == applicationCellState
                && (livingNeighbors == 2 || livingNeighbors == 3);
      }
    },

    // "Any dead cell with exactly three live neighbours becomes a live cell."
    RULE_FOUR(0, 1) {
      public boolean applies(final int currentCellState, int livingNeighbors) {
        return currentCellState == applicationCellState
                && livingNeighbors == 3;
      }
    };

    protected final int applicationCellState;
    private final int resultingState;

    /**
     * Constructor.
     *
     * @param applicationCellState the state that the current life cell must have, so that
     *                             the rule can be applied
     * @param resultingCellState   the state the current cell gets, if the rule can be applied
     */
    Rule(final int applicationCellState, final int resultingCellState) {
      this.applicationCellState = applicationCellState;
      this.resultingState = resultingCellState;
    }

    /**
     * @param currentCellState state of the currently inspected live cell
     * @param livingNeighbors  total number of living cells surrounding the current cell
     * @return TRUE if the rule is valid for the number of 'livingNeighbors', FALSE otherwise
     */
    public abstract boolean applies(final int currentCellState, final int livingNeighbors);

    public int getApplicationCellState() {
      return applicationCellState;
    }

    public int getResultingCellState() {
      return this.resultingState;
    }
  }

  // the edge length of the grid in X
  private final int dimX;

  // the edge length of the grid in X
  private final int dimY;

  // the grid of life cells, where an '0' represents a dead cell and a '1' living one
  private int[][] grid;

  // Set of rule applied in this Game of Life
  private final EnumSet<Rule> ruleSet;

  public GameOfLife(final int[][] initialGrid, EnumSet<Rule> rulesToBeApplied) {
    validateGrid(initialGrid);

    dimX = initialGrid.length;
    dimY = initialGrid[0].length;
    grid = copyGrid(initialGrid);
    ruleSet = EnumSet.copyOf(rulesToBeApplied);
  }

  /**
   * Performs one life cycle iteration according to rules.
   */
  public void iterate() {
    int[][] newState = copyGrid(grid);

    // walk grid and apply the rules on each cell
    for (int xCoord = 0; (xCoord < dimX); ++xCoord) {
      for (int yCoord = 0; (yCoord < dimY); ++yCoord) {
        for (final Rule rule : ruleSet) {
          final int currentCellState = grid[xCoord][yCoord];
          final int totalLivingNeighbors = countLivingNeighborCells(xCoord, yCoord);

          if (rule.applies(currentCellState, totalLivingNeighbors)) {
            newState[xCoord][yCoord] = rule.getResultingCellState();
          }
        }
      }
    }

    grid = newState;
  }

  // count the living neighbor cells of the current cell, determined by
  // 'xCoord' and 'yCoord'
  private int countLivingNeighborCells(final int xCoord, final int yCoord) {
    int livingNeigbors = 0;

    // scan 3x3 matrix around current cell
    // (from top left to bottom right)
    for (int xOffset = -1; xOffset <= 1; ++xOffset) {
      for (int yOffset = -1; yOffset <= 1; ++yOffset) {

        int xIndex = xOffset + xCoord;
        int yIndex = yOffset + yCoord;

        // Skip current cell, if
        //   a) cell lies outside the grid or
        //   b) current cell itself is to be count
        if (!validGridCoords(xIndex, yIndex)
                || (xIndex == xCoord && yIndex == yCoord)) {
          continue;
        }

        if (grid[xIndex][yIndex] == 1) {
          ++livingNeigbors;
        }
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
   * - Any edge length in X direction is legal.
   * - The length of the first Array in Y direction dictates the edge length of all following
   *
   * @param initialGrid
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
