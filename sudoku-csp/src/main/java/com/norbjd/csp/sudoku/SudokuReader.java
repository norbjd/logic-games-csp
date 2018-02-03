package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.representation.InvalidSudokuRepresentationException;
import com.norbjd.csp.sudoku.representation.SudokuRepresentation;

public class SudokuReader {

    public static Sudoku readFromRepresentation(SudokuRepresentation SudokuRepresentation)
            throws InvalidSudokuRepresentationException {
        return SudokuRepresentation.get();
    }

}
