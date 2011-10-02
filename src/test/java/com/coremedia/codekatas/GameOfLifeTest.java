package com.coremedia.codekatas;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.coremedia.codekatas.GameOfLife.Rule;

import java.util.EnumSet;

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
  public void ruleOne_SingleIteration_01() {
    int[][] initial = {{0, 0, 0},
                       {0, 0, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_ONE));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleOne_SingleIteration_02() {
    int[][] initial = {{0, 0, 0},
                       {0, 1, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_ONE));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleOne_SingleIteration_03() {
    int[][] initial = {{0, 0, 0},
                       {0, 1, 1},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_ONE));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleOne_SingleIteration_04() {
    int[][] initial = {{1, 0, 1},
                       {0, 1, 0},
                       {1, 0, 1}};

    int[][] expected = {{0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_ONE));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleTwo_SingleIteration_01() {
    int[][] initial = {{1, 1, 1},
                       {1, 1, 1},
                       {1, 1, 1}};

    int[][] expected = {{1, 0, 1},
                        {0, 0, 0},
                        {1, 0, 1}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_TWO));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleTwo_SingleIteration_02() {
    int[][] initial = {{0, 0, 1},
                       {0, 0, 1},
                       {1, 1, 1}};

    int[][] expected = {{0, 0, 1},
                        {0, 0, 1},
                        {1, 1, 1}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_TWO));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleTwo_SingleIteration_03() {
    int[][] initial = {{0, 1, 1},
                       {0, 1, 1},
                       {0, 1, 0}};

    int[][] expected = {{0, 1, 1},
                        {0, 0, 0},
                        {0, 1, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_TWO));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleTwo_SingleIteration_04() {
    int[][] initial = {{0, 0, 1},
                       {0, 1, 0},
                       {1, 0, 0}};

    int[][] expected = {{0, 0, 1},
                        {0, 1, 0},
                        {1, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_TWO));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleTwo_SingleIteration_05() {
    int[][] initial = {{0, 0, 0},
                       {0, 0, 0},
                       {0, 0, 0}};

    int[][] expected = {{0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_TWO));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  @Test
  public void ruleTwo_SingleIteration_06() {
    int[][] initial = {{0, 0, 1},
                       {0, 1, 0},
                       {1, 0, 0}};

    int[][] expected = {{0, 0, 1},
                        {0, 1, 0},
                        {1, 0, 0}};

    gol = new GameOfLife(initial, EnumSet.of(Rule.RULE_TWO));
    gol.iterate();

    assertGridsAreEqual(expected, gol.getGrid());
  }

  //
  // Tests, other than "rule tests"
  //

  @Test(expected = IllegalArgumentException.class)
  public void properConstructionInvalidGrid() {
     int[][] grid = {{0, 0, 0},
                     {0, 0, 0},
                     {0, 0}};
    gol = new GameOfLife(grid, EnumSet.allOf(Rule.class));
  }

  @Test
  public void testGetGrid() {
    int[][] initial = {{1, 0, 1},
                       {0, 1, 0},
                       {1, 0, 1}};

    gol = new GameOfLife(initial, EnumSet.allOf(Rule.class));
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

    gol = new GameOfLife(initial, EnumSet.allOf(Rule.class));
    // change a value
    initial[0][0] = 0;
    // should have no effects
    assertGridsAreEqual(initialCopy, gol.getGrid());
  }
}
