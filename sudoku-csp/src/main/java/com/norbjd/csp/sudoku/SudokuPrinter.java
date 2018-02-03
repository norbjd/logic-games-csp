package com.norbjd.csp.sudoku;

import java.io.PrintStream;

public class SudokuPrinter {

	PrintStream out;

	SudokuPrinter(PrintStream out) {
		this.out = out;
	}

	private String toString(Sudoku sudoku) {
		int[] cellsValues = sudoku.getCellsValues();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < Sudoku.SUDOKU_SIDE_LENGTH; i++) {
			for (int j = 0; j < Sudoku.SUDOKU_SIDE_LENGTH; j++) {
				int cellValue = cellsValues[i * Sudoku.SUDOKU_SIDE_LENGTH + j];

				if (cellValue == Sudoku.NO_VALUE) {
					sb.append("_");
				} else {
					sb.append(cellValue);
				}

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
