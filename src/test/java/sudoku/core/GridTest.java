package sudoku.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
