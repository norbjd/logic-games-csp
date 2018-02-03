package com.norbjd.csp.sudoku.exception;

import com.norbjd.csp.sudoku.Sudoku;

public class SudokuInvalidNumberOfCellsException extends SudokuInitializationException {

	public SudokuInvalidNumberOfCellsException(int nbCells) {
		super("Sudoku have " + nbCells + " cells instead of expected" + Sudoku.SUDOKU_NB_CELLS);
	}
}
