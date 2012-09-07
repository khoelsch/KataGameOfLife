package com.coremedia.codekatas;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GameOfLifeTest {
  @Test
  public void properConstruction() {
    int[][] input = {
            {0, 1, 0},
            {1, 0, 0},
            {0, 1, 1}
    };

    GameOfLife gol = new GameOfLife(input);

    assertEquals("Contruction-Error!", 0, gol.cellAt(0, 0));
    assertEquals("Contruction-Error!", 1, gol.cellAt(0, 1));
    assertEquals("Contruction-Error!", 0, gol.cellAt(0, 2));

    assertEquals("Contruction-Error!", 1, gol.cellAt(1, 0));
    assertEquals("Contruction-Error!", 0, gol.cellAt(1, 1));
    assertEquals("Contruction-Error!", 0, gol.cellAt(1, 2));

    assertEquals("Contruction-Error!", 0, gol.cellAt(2, 0));
    assertEquals("Contruction-Error!", 1, gol.cellAt(2, 1));
    assertEquals("Contruction-Error!", 1, gol.cellAt(2, 2));
  }

  @Test
  public void testTrivialFieldDeadCell() {
    int[][] trivialField = new int[1][1];
    GameOfLife gol = new GameOfLife(trivialField);
    gol.step();
    // stays dead
    assertEquals(0, gol.cellAt(0,0));
  }

  @Test
    public void testTrivialFieldLiveCell() {
      int[][] trivialField = {{1}};
      GameOfLife gol = new GameOfLife(trivialField);
      gol.step();
      // stays dead
      assertEquals(0, gol.cellAt(0,0));
    }
}
