package com.norbjd.csp.sudoku;

import com.norbjd.csp.settings.DebugSettings;
import com.norbjd.csp.settings.DefaultSettings;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.chocosolver.memory.trailing.EnvironmentTrailing;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Settings;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.explanations.ExplanationEngine;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuCSP {

	private Sudoku sudoku;
	private Model model;

	private IntVar[] cells;

	private boolean debug = true;

	public SudokuCSP(Sudoku sudoku) {
		setSudoku(sudoku);
		initModel();
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	private IntVar[] extractSudokuLine(int lineIndex) {
		return Arrays.asList(cells).subList(lineIndex * 9, (lineIndex + 1) * 9).toArray(new IntVar[9]);
	}

	private IntVar[] extractSudokuColumn(int columnIndex) {
		return IntStream.range(0, 81).mapToObj(i -> new ImmutablePair<>(i, cells[i]))
				.filter((Pair<Integer, IntVar> pair) -> pair.getLeft() % 9 == columnIndex).map(Pair::getRight)
				.toArray(IntVar[]::new);
	}

	private IntVar[] extractSudokuBlock(int blockIndex) {
		int blockLine = blockIndex / 3;
		int blockColumn = blockIndex % 3;

		List<IntVar> blockCells = new ArrayList<>(9);

		for (int i = blockLine * 3; i < (blockLine + 1) * 3; i++) {
			for (int j = blockColumn * 3; j < (blockColumn + 1) * 3; j++) {
				blockCells.add(cells[i * 9 + j]);
			}
		}

		return blockCells.toArray(new IntVar[9]);
	}

	private List<Constraint> presetValuesConstraints() {
		Stream<Pair<IntVar, Integer>> zippedCellsWithValues = IntStream.range(0, 9 * 9)
				.mapToObj(i -> new ImmutablePair<>(cells[i], sudoku.getCellsValues()[i]));

		Stream<Pair<IntVar, Integer>> zippedCellsWithPresetValues = zippedCellsWithValues
				.filter((Pair<IntVar, Integer> pair) -> pair.getRight() != 0);

		return zippedCellsWithPresetValues
				.map((Pair<IntVar, Integer> pair) -> model.arithm(pair.getLeft(), "=", pair.getRight()))
				.collect(Collectors.toList());
	}

	private List<Constraint> allDifferentsNumbersOnLinesConstraints() {
		return IntStream.range(0, 9).mapToObj(line -> model.allDifferent(extractSudokuLine(line)))
				.collect(Collectors.toList());
	}

	private List<Constraint> allDifferentsNumbersOnColumnsConstraints() {
		return IntStream.range(0, 9).mapToObj(column -> model.allDifferent(extractSudokuColumn(column)))
				.collect(Collectors.toList());
	}

	private List<Constraint> allDifferentsNumbersOnBlocksConstraints() {
		return IntStream.range(0, 9).mapToObj(block -> model.allDifferent(extractSudokuBlock(block)))
				.collect(Collectors.toList());
	}

	private Model initModel() {
		Settings settings;

		if (debug) {
			settings = new DebugSettings();
		} else {
			settings = new DefaultSettings();
		}

		model = new Model(new EnvironmentTrailing(), "Sudoku", settings);

		cells = model.intVarArray(9 * 9, 1, 9);

		presetValuesConstraints().forEach(Constraint::post);
		allDifferentsNumbersOnLinesConstraints().forEach(Constraint::post);
		allDifferentsNumbersOnColumnsConstraints().forEach(Constraint::post);
		allDifferentsNumbersOnBlocksConstraints().forEach(Constraint::post);

		return model;
	}

	public Sudoku solve() {
		Solver solver = model.getSolver();

		if (debug) {
			solver.showDecisions();
			solver.showSolutions();
			solver.showContradiction();

			solver.printStatistics();
			solver.setExplainer(new ExplanationEngine(model, true, true));
		}

		solver.solve();

		int[] cellsValues = Stream.of(cells).mapToInt(IntVar::getValue).toArray();

		Sudoku sudokuCopy = new Sudoku(sudoku);
		sudokuCopy.setCellsValues(cellsValues);

		return sudokuCopy;
	}

	public void solveAllAndPrintEachSolution() {
		Solver solver = model.getSolver();

		if (debug) {
			solver.showDecisions();
			solver.showSolutions();
			solver.showContradiction();

			solver.printStatistics();
			solver.setExplainer(new ExplanationEngine(model, true, true));
		}

		int nbSolutions = 0;

		while (solver.solve()) {
			int[] cellsValues = Stream.of(cells).mapToInt(IntVar::getValue).toArray();

			Sudoku sudokuCopy = new Sudoku(sudoku);
			sudokuCopy.setCellsValues(cellsValues);

			System.out.println("-- SOLUTION " + (++nbSolutions) + "--\n");
			System.out.println(sudokuCopy);
		}

		System.out.println("Number of solutions : " + nbSolutions);
	}

}
