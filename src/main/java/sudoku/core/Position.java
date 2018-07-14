package sudoku.core;

public class Position {

  private final int row;
  private final int col;

  private Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public static Position of(int row, int col) {
    return new Position(row, col);
  }

  @Override
  public boolean equals(Object obj) {
    Position pos = (Position) obj;
    return this.row == pos.row && this.col == pos.col;
  }
}
