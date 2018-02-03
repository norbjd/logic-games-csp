package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;
import com.norbjd.csp.sudoku.representation.SudokuRepresentation;

public class SudokuReader {

	public static Sudoku readFromRepresentation(SudokuRepresentation SudokuRepresentation)
			throws SudokuInvalidRepresentationException {
		return SudokuRepresentation.get();
	}

}
