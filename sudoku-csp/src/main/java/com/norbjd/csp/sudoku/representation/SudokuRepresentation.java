package com.norbjd.csp.sudoku.representation;

import com.norbjd.csp.sudoku.Sudoku;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;

public interface SudokuRepresentation {
	Sudoku get() throws SudokuInvalidRepresentationException;
}
