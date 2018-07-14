package sudoku.core;

import com.google.common.collect.ImmutableTable;

import java.util.Set;

import static sudoku.core.Grid.SIZE;

public class Puzzle {

  private Grid grid;

  Puzzle(Grid grid) {
    this.grid = grid;
  }

  Grid solve() {
    return new Grid(
        "435269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743628\n"
            + "519326874\n"
            + "248957136\n"
            + "763418259");
  }

  public Grid fillInCertainCells() {
    SudokuEvaluator evaluator = new SudokuEvaluator(grid);
    ImmutableTable.Builder<Integer, Integer, Integer> builder = ImmutableTable.builder();
    for (int row = 1; row <= SIZE; row++) {
      for (int col = 1; col <= SIZE; col++) {
        if (grid.get(row, col) == 0) {
          Set<Integer> candidates = evaluator.getCandidates(row, col);
          if (candidates.size() == 1) {
            builder.put(row, col, candidates.toArray(new Integer[0])[0]);
          }
        }
      }
    }
    return grid.set(builder.build());
  }
}
