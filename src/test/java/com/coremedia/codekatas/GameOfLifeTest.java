package com.coremedia.codekatas;

import junit.framework.Assert;
import org.junit.Test;

public class GameOfLifeTest {
  @Test
  public void properConstruction() {
    int[][] input = {
            {0, 1, 0},
            {1, 0, 0},
            {0, 1, 1}
    };

    GameOfLife gol = new GameOfLife(input);

    Assert.assertEquals("Contruction-Error!", 0, gol.cellAt(0, 0));
    Assert.assertEquals("Contruction-Error!", 1, gol.cellAt(0, 1));
    Assert.assertEquals("Contruction-Error!", 0, gol.cellAt(0, 2));

    Assert.assertEquals("Contruction-Error!", 1, gol.cellAt(1, 0));
    Assert.assertEquals("Contruction-Error!", 0, gol.cellAt(1, 1));
    Assert.assertEquals("Contruction-Error!", 0, gol.cellAt(1, 2));

    Assert.assertEquals("Contruction-Error!", 0, gol.cellAt(2, 0));
    Assert.assertEquals("Contruction-Error!", 1, gol.cellAt(2, 1));
    Assert.assertEquals("Contruction-Error!", 1, gol.cellAt(2, 2));
  }
}
