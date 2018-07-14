package sudoku.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuEvaluatorTest {
  @Test
  void isRowFilled_etc() {
    Grid grid =
        new Grid(
            "435269781\n"
                + "6.2571493\n"
                + "197834562\n"
                + "826195347\n"
                + "374682915\n"
                + "951743628\n"
                + "519326874\n"
                + "248957136\n"
                + "763418259");
    assertThat(new SudokuEvaluator(grid).isRowFilled(1)).isTrue();
    assertThat(new SudokuEvaluator(grid).isRowFilled(2)).isFalse();
    assertThat(new SudokuEvaluator(grid).isColFilled(1)).isTrue();
    assertThat(new SudokuEvaluator(grid).isColFilled(2)).isFalse();
    assertThat(new SudokuEvaluator(grid).isBlockFilled(1, 1)).isFalse();
    assertThat(new SudokuEvaluator(grid).isBlockFilled(1, 2)).isTrue();
  }

  @Test
  void getCandidates() {
    Grid grid =
        new Grid(
            "435269781\n"
                + "6.2571493\n"
                + "197834562\n"
                + "826195347\n"
                + "374682915\n"
                + "951743628\n"
                + "519326874\n"
                + "248957136\n"
                + "763418259");
    assertThat(new SudokuEvaluator(grid).getCandidates(2, 2)).containsExactlyInAnyOrder(8);
    assertThat(new SudokuEvaluator(grid).isStuck()).isFalse();
  }

  @Test
  void getCandidates2() {
    Grid grid =
        new Grid(
            ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........");
    assertThat(new SudokuEvaluator(grid).getCandidates(5, 5))
        .containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertThat(new SudokuEvaluator(grid).isStuck()).isFalse();
  }

  @Test
  void getCandidates3() {
    Grid grid =
        new Grid(
            "123456789\n"
                + "456789123\n"
                + ".89123456\n"
                + "73456.891\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........\n"
                + ".........");
    assertThat(new SudokuEvaluator(grid).getCandidates(3, 1)).isEmpty();
    assertThat(new SudokuEvaluator(grid).getCandidates(4, 6)).containsExactlyInAnyOrder(2);
    assertThat(new SudokuEvaluator(grid).isStuck()).isTrue();
  }
}
