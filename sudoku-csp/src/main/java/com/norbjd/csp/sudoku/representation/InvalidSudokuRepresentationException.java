package com.norbjd.csp.sudoku.representation;

public class InvalidSudokuRepresentationException extends Exception {

    public InvalidSudokuRepresentationException() {

    }

    public InvalidSudokuRepresentationException(String message) {
        super(message);
    }
}
