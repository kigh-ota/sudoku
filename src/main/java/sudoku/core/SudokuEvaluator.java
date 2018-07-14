package sudoku.core;

import java.util.Collection;
import java.util.stream.IntStream;

import static sudoku.core.Grid.SIZE;

public class SudokuEvaluator {

  private final Grid grid;

  public SudokuEvaluator(Grid grid) {
    this.grid = grid;
  }

  boolean isRowFilled(int row) {
    return isListFilled(grid.getRow(row));
  }

  boolean isColFilled(int col) {
    return isListFilled(grid.getCol(col));
  }

  boolean isBlockFilled(int blockRow, int blockCol) {
    return isListFilled(grid.getBlock(blockRow, blockCol));
  }

  private boolean isListFilled(Collection<Integer> list) {
    if (list.contains(0)) {
      return false;
    }
    return IntStream.rangeClosed(1, SIZE).allMatch(list::contains);
  }
}
