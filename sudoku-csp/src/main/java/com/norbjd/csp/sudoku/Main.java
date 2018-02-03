package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.choco.SudokuCSP;
import com.norbjd.csp.sudoku.exception.SudokuInitializationException;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;
import com.norbjd.csp.sudoku.representation.SudokuFileRepresentation;
import com.norbjd.csp.sudoku.representation.SudokuRepresentation;

import java.io.File;

public class Main {

	public static void main(String[] args) throws SudokuInitializationException {
		File sudokuFile = new File(args[0]);
		SudokuRepresentation sudokuRepresentation = new SudokuFileRepresentation(sudokuFile);

		Sudoku sudoku = null;
		SudokuPrinter printer = new SudokuPrinter(System.out);

		try {
			sudoku = SudokuReader.readFromRepresentation(sudokuRepresentation);
			printer.printSudoku(sudoku);
		} catch (SudokuInvalidRepresentationException ifre) {
			ifre.printStackTrace();
			System.exit(1);
		}

		SudokuCSP sudokuCSP = new SudokuCSP(sudoku);
		Sudoku solution = sudokuCSP.solve();
		printer.printSudoku(solution);
	}

}
