package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.representation.SudokuRepresentation;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;

public class SudokuReader {

	public static Sudoku readFromRepresentation(SudokuRepresentation SudokuRepresentation)
			throws SudokuInvalidRepresentationException {
		return SudokuRepresentation.get();
	}

}
