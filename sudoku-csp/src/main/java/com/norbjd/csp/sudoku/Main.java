package com.norbjd.csp.sudoku;

import com.norbjd.csp.AbstractMain;
import com.norbjd.csp.Arguments;
import com.norbjd.csp.sudoku.choco.SudokuCSP;
import com.norbjd.csp.sudoku.exception.SudokuInitializationException;
import com.norbjd.csp.sudoku.representation.SudokuFileRepresentation;
import com.norbjd.csp.sudoku.representation.SudokuRepresentation;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;

import java.io.File;

public class Main extends AbstractMain {

	public static void main(String[] args) throws SudokuInitializationException {
		Arguments parsedArgs = parseArguments(args);
		String fileName = parsedArgs.getFileName();
		boolean debug = parsedArgs.getDebug();

		File sudokuFile = new File(fileName);
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

		SudokuCSP sudokuCSP = new SudokuCSP(sudoku, debug);
		Sudoku solution = sudokuCSP.solve();
		printer.printSudoku(solution);
	}

}
