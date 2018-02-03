package com.norbjd.csp.sudoku;

import java.io.PrintStream;

public class SudokuPrinter {

	private PrintStream out;

	SudokuPrinter(PrintStream out) {
		this.out = out;
	}

	private String cellValueToString(int cellValue) {
		if (cellValue == Sudoku.NO_VALUE) {
			return "_";
		} else {
			return String.valueOf(cellValue);
		}
	}

	private String toString(Sudoku sudoku) {
		int[] cellsValues = sudoku.getCellsValues();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < Sudoku.SUDOKU_SIDE_LENGTH; i++) {
			for (int j = 0; j < Sudoku.SUDOKU_SIDE_LENGTH; j++) {
				int cellValue = cellsValues[i * Sudoku.SUDOKU_SIDE_LENGTH + j];

				sb.append(cellValueToString(cellValue));
				sb.append(" ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public void printSudoku(Sudoku sudoku) {
		out.println(toString(sudoku));
	}

}
