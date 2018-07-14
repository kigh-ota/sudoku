package sudoku.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static sudoku.core.Grid.SIZE;

class GridTest {
  @Test
  void constructor_正常な入力() {
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
    Grid grid = new Grid(gridStr);
    assertThat(grid.get(1, 1)).isEqualTo(0);
    assertThat(grid.get(2, 2)).isEqualTo(8);
    assertThat(grid.get(9, 1)).isEqualTo(7);
    assertThatThrownBy(
        () -> {
          grid.get(0, 1);
        });
    assertThatThrownBy(
        () -> {
          grid.get(1, 0);
        });
    //        assertThat(grid.toString()).isEqualTo(gridStr); TODO
  }

  @Test
  void constructor_不正な入力() {
    assertThatThrownBy(
        () -> {
          new Grid("hoge");
        });
  }

  @Test
  void getRow_etc() {
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
    assertThat(new Grid(gridStr).getRow(1)).isEqualTo(ImmutableList.of(0, 0, 0, 2, 6, 0, 7, 0, 1));
    assertThat(new Grid(gridStr).getRow(2)).isEqualTo(ImmutableList.of(6, 8, 0, 0, 7, 0, 0, 9, 0));
    assertThat(new Grid(gridStr).getCol(1)).isEqualTo(ImmutableList.of(0, 6, 1, 8, 0, 0, 0, 0, 7));
    assertThat(new Grid(gridStr).getBlock(1, 1))
        .isEqualTo(ImmutableList.of(0, 0, 0, 6, 8, 0, 1, 9, 0));
  }

  @Test
  void getUnfilledSquares() {
    String gridStr =
        ".35269781\n"
            + "682571493\n"
            + "197834562\n"
            + "826195347\n"
            + "374682915\n"
            + "951743.28\n"
            + "519326874\n"
            + "248957136\n"
            + "76341825.";
    Grid grid = new Grid(gridStr);
    assertThat(grid.getUnfilledSquares())
        .containsExactlyInAnyOrder(Position.of(1, 1), Position.of(6, 7), Position.of(9, 9));
  }

  @Test
  void name() {
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
    Grid grid = new Grid(gridStr);
    SudokuEvaluator sudokuEvaluator = new SudokuEvaluator(grid);
    for (int row = 1; row <= SIZE; row++) {
      for (int col = 1; col <= SIZE; col++) {
        if (grid.get(row, col) == 0) {
          System.out.println(
              String.format("(%d,%d) => %s", row, col, sudokuEvaluator.getCandidates(row, col)));
        }
      }
    }
    Grid grid2 = new Puzzle(grid).fillInCertainCells();
    sudokuEvaluator = new SudokuEvaluator(grid2);
    for (int row = 1; row <= SIZE; row++) {
      for (int col = 1; col <= SIZE; col++) {
        if (grid2.get(row, col) == 0) {
          System.out.println(
              String.format("(%d,%d) => %s", row, col, sudokuEvaluator.getCandidates(row, col)));
        }
      }
    }
  }

  @Test
  void empty() {
    Grid emptyGrid =
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
    assertThat(Grid.empty()).isEqualTo(emptyGrid);
  }

  @Test
  void set() {
    Grid grid = Grid.empty();
    Table<Integer, Integer, Integer> setter =
        new ImmutableTable.Builder().put(1, 1, 1).put(2, 2, 2).build();
    Grid added = grid.set(setter);
    assertThat(added)
        .isEqualTo(
            new Grid(
                "1........\n"
                    + ".2.......\n"
                    + ".........\n"
                    + ".........\n"
                    + ".........\n"
                    + ".........\n"
                    + ".........\n"
                    + ".........\n"
                    + "........."));
    assertThat(added).isNotSameAs(grid);
  }
}
