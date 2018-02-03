package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.exception.SudokuInitializationException;
import com.norbjd.csp.sudoku.exception.SudokuInvalidCellValueException;
import com.norbjd.csp.sudoku.exception.SudokuInvalidNumberOfCellsException;
import com.norbjd.csp.sudoku.exception.SudokuUninitializedCellsValuesException;

public class Sudoku {

	public static final int SUDOKU_SIDE_LENGTH = 9;
	public static final int SUDOKU_NB_CELLS = SUDOKU_SIDE_LENGTH * SUDOKU_SIDE_LENGTH;
	public static final int SUDOKU_BLOCK_SIDE_LENGTH = 3;

	public static final int NO_VALUE = -1;

	private int[] cellsValues;

	public Sudoku(int[] cellsValues) throws SudokuInitializationException {
		setCellsValues(cellsValues);
	}

	public int[] getCellsValues() {
		return cellsValues;
	}

	private boolean sudokuCellValueOutOfBounds(int cellValue) {
		return cellValue != NO_VALUE && (cellValue <= 0 || cellValue > SUDOKU_SIDE_LENGTH);
	}

	private boolean correctCellsValues(int[] cellsValues) throws SudokuInitializationException {
		if (cellsValues == null) {
			throw new SudokuUninitializedCellsValuesException();
		}
		if (cellsValues.length != SUDOKU_NB_CELLS) {
			throw new SudokuInvalidNumberOfCellsException(cellsValues.length);
		}
		for (int i = 0; i < SUDOKU_NB_CELLS; i++) {
			int cellValue = cellsValues[i];
			if (sudokuCellValueOutOfBounds(cellValue)) {
				throw new SudokuInvalidCellValueException(cellValue);
			}
		}
		return true;
	}

	private void setCellsValues(int[] cellsValues) throws SudokuInitializationException {
		if (correctCellsValues(cellsValues)) {
			this.cellsValues = cellsValues;
		}
	}
}
