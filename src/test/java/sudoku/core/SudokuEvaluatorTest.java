package sudoku.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SudokuEvaluatorTest {
    @Test
    void isRowFilled() {
        Grid grid = new Grid(
                "435269781\n" +
                    "6.2571493\n" +
                    "197834562\n" +
                    "826195347\n" +
                    "374682915\n" +
                    "951743628\n" +
                    "519326874\n" +
                    "248957136\n" +
                    "763418259");
        assertThat(new SudokuEvaluator(grid).isRowFilled(1)).isTrue();
        assertThat(new SudokuEvaluator(grid).isRowFilled(2)).isFalse();
    }
}