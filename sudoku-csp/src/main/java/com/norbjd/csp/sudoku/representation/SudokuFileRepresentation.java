package com.norbjd.csp.sudoku.representation;

import com.norbjd.csp.sudoku.Sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SudokuFileRepresentation implements SudokuRepresentation {

    private File file;

    public SudokuFileRepresentation(File file) {
        setFile(file);
    }

    public File getFile() {
        return file;
    }

    private String getFileContent() throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Sudoku get() throws InvalidSudokuRepresentationException {
        try {
            SudokuStringRepresentation sudokuStringRepresentation = new SudokuStringRepresentation(getFileContent());
            return sudokuStringRepresentation.get();
        } catch (IOException ioe) {
            throw new InvalidSudokuRepresentationException(ioe.getMessage());
        }
    }

}
