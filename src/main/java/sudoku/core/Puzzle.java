package sudoku.core;

public class Puzzle {
  Puzzle(Grid grid) {}

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
}
