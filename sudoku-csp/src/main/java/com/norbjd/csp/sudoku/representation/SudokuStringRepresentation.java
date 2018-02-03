package com.norbjd.csp.sudoku.representation;

import com.norbjd.csp.sudoku.Sudoku;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;

public class SudokuStringRepresentation implements SudokuRepresentation {

	private String representation;

	SudokuStringRepresentation(String representation) {
		setRepresentation(representation);
	}

	public String getRepresentation() {
		return representation;
	}

	private void setRepresentation(String representation) {
		this.representation = representation;
	}

	public Sudoku get() throws SudokuInvalidRepresentationException {
		try {
			String[] lines = representation.split("\n");

			int size = 9;
			int[] cellsValues = new int[size * size];

			for (int line = 0; line < size; line++) {
				String[] cellsOnLine = lines[line].split(" ");
				for (int col = 0; col < size; col++) {
					int cellIndex = line * size + col;
					String cellValue = cellsOnLine[col];
					if (cellValue.equals("_")) {
						cellsValues[cellIndex] = Sudoku.NO_VALUE;
					} else {
						cellsValues[cellIndex] = Integer.parseInt(cellValue);
					}
				}
			}

			return new Sudoku(cellsValues);
		} catch (Exception e) {
			throw new SudokuInvalidRepresentationException(e.getMessage());
		}
	}

}
