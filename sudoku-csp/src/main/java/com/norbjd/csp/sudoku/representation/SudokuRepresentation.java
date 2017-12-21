package com.norbjd.csp.sudoku.representation;

import com.norbjd.csp.sudoku.Sudoku;

public interface SudokuRepresentation {
    public Sudoku get() throws InvalidSudokuRepresentationException;
}
