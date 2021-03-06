package sudoku.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PuzzleTest {
  @Test
  void solve_あと1マス() {
    String gridStr =
        "435269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743.28\n"
            + "519326874\n"
            + "248957136\n"
            + "763418259";
    String solutionStr =
        "435269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743628\n"
            + "519326874\n"
            + "248957136\n"
            + "763418259";
    Grid puzzle = new Grid(gridStr);
    Grid solution = new Puzzle(puzzle).solve();
    assertThat(solution).isEqualTo(new Grid(solutionStr));
  }

  @Test
  void solve() {
    String gridStr =
        "...26.7.1\n"
            + "68..7..9.\n"
            + "19...45..\n"
            + "82.1...4.\n"
            + "..46.29..\n"
            + ".5...3.28\n"
            + "..93...74\n"
            + ".4..5..36\n"
            + "7.3.18...";
    String solutionStr =
        "435269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743628\n"
            + "519326874\n"
            + "248957136\n"
            + "763418259";
    Grid puzzle = new Grid(gridStr);
    Grid solution = new Puzzle(puzzle).solve();
    assertThat(solution).isEqualTo(new Grid(solutionStr));
  }

  @Test
  void fillInCertainCells() {
    String gridStr =
        "435269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743.28\n"
            + "519326874\n"
            + "248957136\n"
            + "763418259";
    String filledStr =
        "435269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743628\n"
            + "519326874\n"
            + "248957136\n"
            + "763418259";
    Grid puzzle = new Grid(gridStr);
    Grid filled = new Puzzle(puzzle).fillInCertainCells();
    assertThat(filled).isEqualTo(new Grid(filledStr));
  }
}
