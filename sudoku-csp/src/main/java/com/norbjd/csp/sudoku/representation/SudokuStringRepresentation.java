package com.norbjd.csp.sudoku.representation;

import com.norbjd.csp.sudoku.Sudoku;

public class SudokuStringRepresentation implements SudokuRepresentation {

	private String representation;

	public SudokuStringRepresentation(String representation) {
		setRepresentation(representation);
	}

	public String getRepresentation() {
		return representation;
	}

	public void setRepresentation(String representation) {
		this.representation = representation;
	}

	public Sudoku get() throws InvalidSudokuRepresentationException {
		try {
			String[] lines = representation.split("\n");

			int size = 9;
			int[] cellsValues = new int[size * size];

			for (int line = 0; line < size; line++) {
				String[] cellsOnLine = lines[line].split(" ");
				for (int col = 0; col < size; col++) {
					int cellIndex = line * size + col;
					String cellValue = cellsOnLine[col];
					if (cellValue.equals("X")) {
						cellsValues[cellIndex] = 0;
					} else {
						cellsValues[cellIndex] = Integer.parseInt(cellValue);
					}
				}
			}

			return new Sudoku(cellsValues);
		} catch (Exception e) {
			throw new InvalidSudokuRepresentationException(e.getMessage());
		}
	}

}
