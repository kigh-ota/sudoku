package sudoku.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

  private static final int SIZE = 9;

  Table<Integer, Integer, Integer> squares;

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
}
