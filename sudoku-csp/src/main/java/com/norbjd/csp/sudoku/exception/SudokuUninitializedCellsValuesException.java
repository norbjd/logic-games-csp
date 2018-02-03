package com.norbjd.csp.sudoku.exception;

public class SudokuUninitializedCellsValuesException extends SudokuInitializationException {

	public SudokuUninitializedCellsValuesException() {
		super("Cells values cannot be null to initialize a sudoku");
	}
}
