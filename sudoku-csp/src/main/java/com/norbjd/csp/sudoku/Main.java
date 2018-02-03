package com.norbjd.csp.sudoku;

import com.norbjd.csp.sudoku.choco.SudokuCSP;
import com.norbjd.csp.sudoku.exception.SudokuInitializationException;
import com.norbjd.csp.sudoku.representation.exception.SudokuInvalidRepresentationException;
import com.norbjd.csp.sudoku.representation.SudokuFileRepresentation;
import com.norbjd.csp.sudoku.representation.SudokuRepresentation;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

public class Main {

	private static Pair<String, Boolean> parseArguments(String[] args) {
		if(args.length == 0) {
			System.err.println("Usage : Main <file> [<debug>]");
			System.exit(0);
		}

		String fileName = args[0];
		boolean debug = false;

		if(args.length >= 2) {
			debug = Boolean.parseBoolean(args[1]);
		}

		return new ImmutablePair<>(fileName, debug);
	}

	public static void main(String[] args) throws SudokuInitializationException {
		Pair<String, Boolean> parsedArgs = parseArguments(args);
		String fileName = parsedArgs.getLeft();
		boolean debug = parsedArgs.getRight();

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
