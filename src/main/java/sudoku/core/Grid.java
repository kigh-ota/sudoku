package sudoku.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

  static final int SIZE = 9;

  private Table<Integer, Integer, Integer> squares;

  Grid(String str) {
    ImmutableTable.Builder builder = new ImmutableTable.Builder();

    String[] split = str.split("\n", SIZE);
    Preconditions.checkArgument(split.length == SIZE);
    for (int row = 1; row <= SIZE; row++) {
      String rowStr = split[row - 1];
      Preconditions.checkArgument(rowStr.length() == SIZE);
      for (int col = 1; col <= SIZE; col++) {
        char ch = rowStr.charAt(col - 1);
        if (ch == '.') {
          builder.put(row, col, 0);
        } else if (ch >= Character.forDigit(1, 10) && ch <= Character.forDigit(SIZE, 10)) {
          builder.put(row, col, Character.getNumericValue(ch));
        } else {
          throw new IllegalArgumentException();
        }
      }
    }

    squares = builder.build();
  }

  int get(int row, int col) {
    Preconditions.checkArgument(row >= 1 && row <= SIZE);
    Preconditions.checkArgument(col >= 1 && col <= SIZE);
    return squares.get(row, col);
  }

  // TODO
  @Override
  public String toString() {
    return "Grid{" + "squares=" + squares + '}';
  }

  @Override
  public boolean equals(Object obj) {
    return this.toString().equals(obj.toString());
  }

  public Collection<Integer> getRow(int row) {
    return IntStream.rangeClosed(1, SIZE)
        .map((col) -> squares.get(row, col))
        .boxed()
        .collect(Collectors.toList());
  }

  public Collection<Integer> getCol(int col) {
    return IntStream.rangeClosed(1, SIZE)
        .map((row) -> squares.get(row, col))
        .boxed()
        .collect(Collectors.toList());
  }

  public Collection<Integer> getBlock(int blockRow, int blockCol) {
    ImmutableList.Builder<Integer> builder = ImmutableList.builder();
    int rowOffset = 1 + (blockRow - 1) * 3;
    for (int r = 0; r < 3; r++) {
      int row = rowOffset + r;
      int colOffset = 1 + (blockCol - 1) * 3;
      for (int c = 0; c < 3; c++) {
        int col = colOffset + c;
        builder.add(squares.get(row, col));
      }
    }
    return builder.build();
  }

  public Set<Position> getUnfilledSquares() {
    return squares
        .cellSet()
        .stream()
        .filter(cell -> cell.getValue() == 0)
        .map(cell -> Position.of(cell.getRowKey(), cell.getColumnKey()))
        .collect(Collectors.toSet());
  }

  public static Grid empty() {
    return new Grid(
        ".........\n"
            + ".........\n"
            + ".........\n"
            + ".........\n"
            + ".........\n"
            + ".........\n"
            + ".........\n"
            + ".........\n"
            + ".........");
  }

  public Grid set(Table<Integer, Integer, Integer> setter) {
    Grid newGrid = empty();
    ImmutableTable.Builder<Integer, Integer, Integer> builder = ImmutableTable.builder();
    for (int row = 1; row <= SIZE; row++) {
      for (int col = 1; col <= SIZE; col++) {
        Integer digit = setter.get(row, col);
        if (digit != null) {
          builder.put(row, col, digit);
        } else {
          builder.put(row, col, this.squares.get(row, col));
        }
      }
    }
    newGrid.squares = builder.build();
    return newGrid;
  }
}
