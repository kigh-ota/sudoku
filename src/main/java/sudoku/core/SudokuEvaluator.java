package sudoku.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
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

  public Set<Integer> getCandidates(int row, int col) {
    Preconditions.checkState(grid.get(row, col) == 0);
    Collection<Integer> rowSet = grid.getRow(row);
    Collection<Integer> colSet = grid.getCol(col);
    Collection<Integer> blockSet = grid.getBlock((row - 1) / 3 + 1, (col - 1) / 3 + 1);
    return IntStream.rangeClosed(1, SIZE)
        .filter(
            digit -> {
              return !rowSet.contains(digit)
                  && !colSet.contains(digit)
                  && !blockSet.contains(digit);
            })
        .boxed()
        .collect(Collectors.toSet());
  }

  public boolean isStuck() {
    for (int row = 1; row <= SIZE; row++) {
      for (int col = 1; col <= SIZE; col++) {
        if (grid.get(row, col) == 0 && getCandidates(row, col).isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }
}
