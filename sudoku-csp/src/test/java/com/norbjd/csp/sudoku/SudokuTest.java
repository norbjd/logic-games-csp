package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.exception.SudokuInitializationException;
import com.norbjd.csp.sudoku.exception.SudokuInvalidCellValueException;
import com.norbjd.csp.sudoku.exception.SudokuInvalidNumberOfCellsException;
import com.norbjd.csp.sudoku.exception.SudokuUninitializedCellsValuesException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuTest {

	@Test
	public void notEnoughCellsToInitializeASudoku() {
		int[] wrongSizedCellsValues = new int[Sudoku.SUDOKU_NB_CELLS - 1];

		try {
			new Sudoku(wrongSizedCellsValues);
		} catch (SudokuInitializationException e) {
			assertEquals(e.getClass(), SudokuInvalidNumberOfCellsException.class);
		}
	}

	@Test
	public void tooManyCellsValuesToInitializeASudoku() {
		int[] wrongSizedCellsValues = new int[Sudoku.SUDOKU_NB_CELLS + 1];

		try {
			new Sudoku(wrongSizedCellsValues);
		} catch (SudokuInitializationException e) {
			assertEquals(e.getClass(), SudokuInvalidNumberOfCellsException.class);
		}
	}

	@Test
	public void uninitializedCellsValuesToInitializeASudoku() {
		int[] wrongSizedCellsValues = null;

		try {
			new Sudoku(wrongSizedCellsValues);
		} catch (SudokuInitializationException e) {
			assertEquals(e.getClass(), SudokuUninitializedCellsValuesException.class);
		}
	}

	private int[] initCellsValuesWithRandomValues() {
		int[] cellsValues = new int[Sudoku.SUDOKU_NB_CELLS];
		for (int i = 0; i < Sudoku.SUDOKU_NB_CELLS; i++) {
			cellsValues[i] = (int) (Math.random() * Sudoku.SUDOKU_SIDE_LENGTH + 1);
		}
		return cellsValues;
	}

	@Test
	public void aNegativeCellValueIsUnexpectedToInitializeASudoku() {
		int[] cellsValues = initCellsValuesWithRandomValues();

		int badCellValueIndex = (int) (Math.random() * Sudoku.SUDOKU_NB_CELLS);
		cellsValues[badCellValueIndex] = -1;

		try {
			new Sudoku(cellsValues);
		} catch (SudokuInitializationException e) {
			assertEquals(e.getClass(), SudokuInvalidCellValueException.class);
		}
	}

	@Test
	public void anOutOfBoundsCellValueIsUnexpectedToInitializeASudoku() {
		int[] cellsValues = initCellsValuesWithRandomValues();

		int badCellValueIndex = (int) (Math.random() * Sudoku.SUDOKU_NB_CELLS);
		cellsValues[badCellValueIndex] = Sudoku.SUDOKU_SIDE_LENGTH + 1;

		try {
			new Sudoku(cellsValues);
		} catch (SudokuInitializationException e) {
			assertEquals(e.getClass(), SudokuInvalidCellValueException.class);
		}
	}
}
