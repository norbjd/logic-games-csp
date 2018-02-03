package com.norbjd.csp.sudoku.representation;

import com.norbjd.csp.sudoku.Sudoku;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;

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

	private void setFile(File file) {
		this.file = file;
	}

	public Sudoku get() throws SudokuInvalidRepresentationException {
		try {
			SudokuStringRepresentation sudokuStringRepresentation = new SudokuStringRepresentation(getFileContent());
			return sudokuStringRepresentation.get();
		} catch (IOException ioe) {
			throw new SudokuInvalidRepresentationException(ioe.getMessage());
		}
	}

}
