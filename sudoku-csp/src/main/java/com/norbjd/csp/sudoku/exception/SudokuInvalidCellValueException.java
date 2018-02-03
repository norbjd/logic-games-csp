package com.norbjd.csp.sudoku.exception;

import com.norbjd.csp.sudoku.Sudoku;

public class SudokuInvalidCellValueException extends SudokuInitializationException {

	public SudokuInvalidCellValueException(int cellValue) {
		super("Cell value should be inside [1; " + Sudoku.SUDOKU_SIDE_LENGTH + "], or " + Sudoku.NO_VALUE
				+ " (if no value provided) : got " + cellValue);
	}
}
